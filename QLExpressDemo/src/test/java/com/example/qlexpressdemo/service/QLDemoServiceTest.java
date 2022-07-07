package com.example.qlexpressdemo.service;

import com.example.qlexpressdemo.context.QlExpressBeanRunner;
import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.entity.ParamInfo;
import com.example.qlexpressdemo.entity.UIndex;
import com.ql.util.express.*;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-30 13:53
 * @Version V1.0
 */
@SpringBootTest
class QLDemoServiceTest {


//    @Autowired
//    private IConditionInfoService iConditionInfoService;
//
//    @Autowired
//    private IUIndexService iuIndexService;

    @Autowired
    private QlExpressBeanRunner qlExpressBeanRunner;

    private final static Pattern R_REGEX = Pattern.compile("(ruleId:)([\\S\\d]+)");


    private final static Map<String, ConditionInfo> cMap = new HashMap<>();

    private final static Map<String, ParamInfo> pMap = new HashMap<>();

    static {
        cMap.put("001", new ConditionInfo("001", "指标.锁定次数<3", "123", "3"));
        cMap.put("002", new ConditionInfo("002", "指标.安全信用>=5", "123", "1"));
        cMap.put("003", new ConditionInfo("003", "指标.设备 = 000001", "123", ""));
        cMap.put("004", new ConditionInfo("004", "指标.访问时间==null or 指标.访问时间 < new Date()", "123", ""));
        cMap.put("005", new ConditionInfo("005", "指标.认证方式 = 1", "", "1,2,3"));


        cMap.put("011", new ConditionInfo("011", "认证方式 == 1", "", "2"));
        cMap.put("012", new ConditionInfo("012", "安全信用 == 高", "", "1"));
        cMap.put("013", new ConditionInfo("013", "锁定次数 >= 3", "", "3"));
        cMap.put("014", new ConditionInfo("014", "常用IP in ['127.0.0.1','192.168.1.1']", "", "4"));
        cMap.put("088", new ConditionInfo("088", "行为节点 == 'xingwei'", "", "5"));

/*
0	rule	规则	String
1	safe_credit	安全信用	String
2	verification	认证方式	String
3	lock_num	锁定次数	String
4	used_ip	常用IP	String
5	used_node	行为节点	Array
 */

        pMap.put("0", new ParamInfo("0", "rule", "规则"));
        pMap.put("1", new ParamInfo("1", "safe_credit", "安全信用"));
        pMap.put("2", new ParamInfo("2", "verification", "认证方式"));
        pMap.put("3", new ParamInfo("3", "lock_num", "锁定次数"));
        pMap.put("4", new ParamInfo("4", "used_ip", "常用IP"));
        pMap.put("5", new ParamInfo("5", "used_node", "行为节点"));


    }

    private ConditionInfo findById(String ruleId) {
        return cMap.get(ruleId);
    }


    private List<ParamInfo> getParamInfos(String ids) {
        List<ParamInfo> list = new ArrayList<>();

        if (StringUtils.isNotBlank(ids)) {
            final String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(pMap.get(i));
            }
        }
        return list;
    }



    @Test
    void test(){
        //三分钟之内错误次数大于3





    }



    @Test
    void verify() throws Exception {

        /**
         *if(请求类型=访问服务){
         *     if(风险等级 ==3){
         *         if(ip in 常用ip){
         *             return false
         *         }else{
         *             return true
         *         }
         *     }else{
         *         if(用户环境高风险){
         *             return true
         *         }else{
         *             return false
         *         }
         *     }
         * }
         */

        final String[] express = {"if ( ruleId:001 or ruleId:002  ) { 返回  '允许访问';  } else {  返回 '不允许访问'; }  "};

//        if ( rule.lock_num<3 or rule.safe_credit>=5  ) { run  '允许访问';  } else {  run '不允许访问'; }
        final Matcher matcher = R_REGEX.matcher(express[0]);

        Map<String, String> rMap = new HashMap<>();

        while (matcher.find()) {
            final String ruleId = matcher.group(2);
            if (StringUtils.isNotBlank(ruleId)) {

//                final ConditionInfo byId = iConditionInfoService.findById(ruleId);
                final ConditionInfo byId = findById(ruleId);
                if (null != byId) {
                    final String expression = byId.getExpression();
                    //替换英文
                    final List<ParamInfo> paramInfos = getParamInfos(byId.getParamInfoIds());
//                    final List<ParamInfo> paramInfos = byId.getParamInfos();
                    if (null != paramInfos) {
                        final Map<String, String> collect = paramInfos.stream().collect(Collectors.toMap(ParamInfo::getTitle, ParamInfo::getField));
                        final String[] replaceAll = {expression};
                        collect.forEach((k, v) -> replaceAll[0] = replaceAll[0].replaceAll(k, v).replaceAll("指标", "rule"));
                        rMap.put(matcher.group(1) + ruleId, replaceAll[0]);
                    } else {
                        rMap.put(matcher.group(1) + ruleId, expression);
                    }
                }
            }
        }
        ExpressRunner runner = new ExpressRunner();
//        ExpressRemoteCacheRunner cacheRunner = new LocalExpressCacheRunner(runner);
//        todo 1 正则 替换
        //todo 2 预加载表达式 false
        if (!CollectionUtils.isEmpty(rMap)) {
            rMap.forEach((k, v) -> express[0] = express[0].replaceAll(k, v));
//            for (Map.Entry<String, String> entry : rMap.entrySet()) {
//                runner.loadMultiExpress(entry.getKey(), entry.getValue());
////                cacheRunner.loadCache(entry.getKey(), entry.getValue());
//            }
        }


//        express[0]


        //定义操作符别名
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("否则", "else", null);
        runner.addOperatorWithAlias("大于", ">", null);
        runner.addOperatorWithAlias("返回", "return", null);
        runner.addOperatorWithAlias("run", "return", null);

        //方法

        //上下文

        //运行

        IExpressContext<String, Object> expressContext = new DefaultContext<>();
        Map<String, String> dataMap = new HashMap<>();
//        dataMap.put("锁定次数", "2");
        dataMap.put("lock_num", "2");
//        dataMap.put("安全信用", "7");
        dataMap.put("safe_credit", "7");

//        expressContext.put("指标", dataMap);
        expressContext.put("rule", dataMap);

        System.err.println("express = " + express[0]);
        /**
         *
         * if ( rule.lock_num<3 or rule.safe_credit>=5  ) { run  '允许访问';  } else {  run '不允许访问'; }
         *
         */

//        final Object execute = cacheRunner.execute(express[0], expressContext, null, false, false, null);
        final Object execute = runner.execute(express[0], expressContext, null, false, false);

        System.err.println(execute);


    }


    @Test
    void beanDemo() throws Exception {

        String statement = "uIndex = iUIndexService.findById(uId); " +
                " System.out.println('uIndex:'+uIndex.toString()); return false;";

        Map<String, Object> innerContext = new HashMap<String, Object>();

        innerContext.put("uId", "1");

        ExpressRunner runner = new ExpressRunner();

//        final IUIndexService expressBeanRunnerBean = qlExpressBeanRunner.getBean(IUIndexService.class);

//        final IExpressContext beanContext = qlExpressBeanRunner.getBeanContext(innerContext);
//        final Object iUIndexService = qlExpressBeanRunner.getBean("uIndexServiceImpl");
        //todo beanName 为 uIndexServiceImpl
//        runner.addFunctionOfServiceMethod("findById", qlExpressBeanRunner.getBean(IUIndexService.class), "findById", new Class[]{String.class}, null);
        final IExpressContext beanContext = qlExpressBeanRunner.getBeanContext(innerContext);
        final Object execute = runner.execute(statement, beanContext, null, false, false);


        System.out.println(execute);


    }

    @Test
    void likeTest() throws Exception {
//       import  java.lang.String;
        String express = "name.startsWith(String.valueOf('a'))  ";

        ExpressRunner runner = new ExpressRunner();
        IExpressContext<String, Object> expressContext = new DefaultContext<>();
        expressContext.put("name", "azzzs");
        final Object execute = runner.execute(express, expressContext, null, false, false);

        System.out.println(express);

    }


    @Test
    void demo() throws Exception {


        String[] express = {"如果 ( ruleId:088 ){\n" +
                "    如果(ruleId:011 and ruleId:012 ){\n" +
                "        返回 'true'\n" +
                "    }否则{\n" +
                "       如果(ruleId:013 or ruleId:014 ){\n" +
                "        返回 '第二条规则'\n" +
                "       }\n" +
                "    }\n" +
                "} 返回 '默认'"};


        final Matcher matcher = R_REGEX.matcher(express[0]);

        Map<String, String> rMap = new HashMap<>();

        while (matcher.find()) {
            final String ruleId = matcher.group(2);
            if (StringUtils.isNotBlank(ruleId)) {
                //                final ConditionInfo byId = iConditionInfoService.findById(ruleId);
                final ConditionInfo byId = findById(ruleId);
                if (null != byId) {
                    final String expression = byId.getExpression();
                    //替换英文
                    final List<ParamInfo> paramInfos = getParamInfos(byId.getParamInfoIds());
//                    final List<ParamInfo> paramInfos = byId.getParamInfos();
                    if (null != paramInfos) {
                        final Map<String, String> collect = paramInfos.stream().collect(Collectors.toMap(ParamInfo::getTitle, ParamInfo::getField));
                        final String[] replaceAll = {expression};
                        collect.forEach((k, v) -> replaceAll[0] = replaceAll[0].replaceAll(k, v));
                        rMap.put(matcher.group(1) + ruleId, replaceAll[0]);
                    } else {
                        rMap.put(matcher.group(1) + ruleId, expression);
                    }
                }
            }
        }
        ExpressRunner runner = new ExpressRunner();

        if (!CollectionUtils.isEmpty(rMap)) {
            rMap.forEach((k, v) -> express[0] = express[0].replaceAll(k, v));
        }
        //定义操作符别名
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("否则", "else", null);
        runner.addOperatorWithAlias("返回", "return", null);
        //方法


        //上下文
        final UIndex uIndex = new UIndex();
        uIndex.setId("3");
        uIndex.setIndexIds("1,2,3,4,5");
        uIndex.setUserName("wangwu");


        final Map<String, String> objectObjectHashMap = new HashMap<>();

//        {"1":"中","2":"1","3":"5","4":"127.0.0.1","5":"xingwei"}

        objectObjectHashMap.put("1", "中");
        objectObjectHashMap.put("2", "1");
        objectObjectHashMap.put("3", "5");
        objectObjectHashMap.put("4", "127.0.0.1");
        objectObjectHashMap.put("5", "xingwei");
        uIndex.setIndexInfo(objectObjectHashMap);

//        final UIndex uIndex = iuIndexService.findById("3");
        IExpressContext<String, Object> expressContext = new DefaultContext<>();
        final Map<String, String> indexInfo = uIndex.getIndexInfo();
        final List<ParamInfo> paramInfos = getParamInfos(uIndex.getIndexIds());
//        final List<ParamInfo> paramInfos = uIndex.getParamInfos();

        paramInfos.forEach(p -> {
            if (indexInfo.containsKey(p.getId())) {
                final String value = indexInfo.get(p.getId());
                expressContext.put(p.getField(), value);
            }
        });

        //运行


        System.err.println("express = " + express[0]);
        /**
         如果 ( used_node == 'xingwei' ){
         如果(verification == 1 and safe_credit == 高 ){
         返回 'true'
         }否则{
         如果(lock_num >= 3 or used_ip in ['127.0.0.1','192.168.1.1'] ){
         返回 '第二条规则'
         }
         }
         } 返回 '默认'
         */

        final Object execute = runner.execute(express[0], expressContext, null, false, false);

        System.err.println(execute);


    }

    @Test
    void test1() throws Exception {

        String express = "if ( used_node == 'xingwei' ){\n" +
                "    if(verification == 1 and safe_credit == 高 ){\n" +
                "        return true;\n" +
                "    }else{\n" +
                "       if(lock_num >= 3 or used_ip in ['127.0.0.1','192.168.1.1'] ){\n" +
                "        return '第二条规则';\n" +
                "       }\n" +
                "    }\n" +
                "}";


        String demo = "         如果 ( used_node == 'xingwei' ){\n" +
                "         如果(verification == 1 and safe_credit == 高 ){\n" +
                "         返回 'true'\n" +
                "         }否则{\n" +
                "         如果(lock_num >= 3 or used_ip in ['127.0.0.1','192.168.1.1'] ){\n" +
                "         返回 '第二条规则'\n" +
                "         }\n" +
                "         }\n" +
                "         } 返回 '默认'";


        IExpressContext<String, Object> expressContext = new DefaultContext<>();
//        Map<String,String> map = new HashMap<>();
        expressContext.put("lock_num", "5");
        expressContext.put("used_node", "xingwei");
        expressContext.put("used_ip", "127.0.0.1");
        expressContext.put("verification", "1");
        expressContext.put("safe_credit", "中");
//        expressContext.put("rule",map);
        ExpressRunner runner = new ExpressRunner();
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("否则", "else", null);
        runner.addOperatorWithAlias("返回", "return", null);

//        final Object execute = runner.execute("rule.used_node == 'xingwei'", expressContext, null, false, false);
        final Object execute = runner.execute(demo, expressContext, null, false, false);


        System.err.println(execute);


    }


}