package com.example.qlexpressdemo;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.entity.ParamInfo;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.CollectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.qlexpressdemo.utils.QlRunnerUtils.ExpressToFunction;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-04 15:48
 * @Version V1.0
 */


public class demoTest {

    private static Unsafe unsafe;

    private static Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        if (null == unsafe) {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        }
        return unsafe;
    }


    public static void main(String[] args) throws Exception {

        //行为节点条件进行筛选 得到运行规则语句

        // findByAction(actionId,appId);


//        verify();
//        demo();
//        test();
//        test1();

//        likeTest();

//        dateDemo();
//        final Object o = getUnsafe().allocateInstance(String.class);
//        System.out.println(o);

//        check1();

        //三分钟之内错误次数大于3

//        获取当前时间是周几
        DayOfWeek day = DayOfWeek.of(LocalDate.now().get(ChronoField.DAY_OF_WEEK));

        System.out.println(day.getValue());


    }


    public static void dateDemo() throws Exception {

//        String str = "new Date().getTime() - lastTime >1";

        String str = " LocalDate.now()";

        ExpressRunner runner = new ExpressRunner();

        final DefaultContext<String, Object> defaultContext = new DefaultContext<>();
        defaultContext.put("lastTime", System.currentTimeMillis());
        final Object execute = runner.execute(str, defaultContext, null, false, false);


        System.out.println(execute);


    }

    private static void check1() throws Exception {

        ExpressRunner runner = new ExpressRunner();

//        runner.addOperatorWithAlias("如果", "if", null);
//        runner.addOperatorWithAlias("否则", "else", null);
//        runner.addOperatorWithAlias("大于", ">", null);
//        runner.addOperatorWithAlias("小于", "<", null);
//        runner.addOperatorWithAlias("不等于", "!=", null);
//        runner.addOperatorWithAlias("返回", "return", null);
        //多个决策点进行汇总一个语句并根据返回结果取最大值
        IExpressContext<String, Object> expressContext = new DefaultContext<>();

        String s1 = "if( xinyong != '高'   ){" +
                "if (xinyong == '中'){" +
                "return '低风险1'" +
                "} else{" +
                "return '高风险1'" +
                "}" +
                "" +
                "}else{" +
                "return '低风险2'" +
                "}";

        expressContext.put("xinyong", "低");

        String s2 = "if(mima < 3){" +
                "return '低风险3'" +
                "}else{" +
                "return '中风险'" +
                "}";

        expressContext.put("mima", 2);

//        String express = "   dataList = new ArrayList();       " ;
//        express +="function s1(){"+s1+"}";
//        express +="function s2(){"+s2+"}";
//        express+=" dataList.add(s1());dataList.add(s2()); return dataList;";
        String str = ExpressToFunction(s1, s2);


        System.out.println("str = " + str);


        final boolean b = runner.checkSyntax(s1);
        System.err.println(b);

        List<String> execute = (List<String>) runner.execute(str, expressContext, null, false, false);

        for (String s : execute) {
            System.err.println("s==>" + s);
        }


        System.err.println(execute);


    }


//    @Autowired
//    private QlExpressBeanRunner qlExpressBeanRunner;

    private final static Pattern R_REGEX = Pattern.compile("(ruleId:)([\\S\\d]+)");


    private final static Map<String, ConditionInfo> cMap = new HashMap<>(10);

    private final static Map<String, ParamInfo> pMap = new HashMap<>(10);

    private final static List<UserLog> userLogList = new ArrayList<>(10);

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


        userLogList.add(new UserLog("3", "1", System.currentTimeMillis() - 1 * 60 * 1000));
        userLogList.add(new UserLog("3", "1", System.currentTimeMillis() - 2 * 60 * 1000));
        userLogList.add(new UserLog("3", "1", System.currentTimeMillis() - 3 * 60 * 1000));
        userLogList.add(new UserLog("3", "1", System.currentTimeMillis() - 4 * 60 * 1000));


    }


    private static ConditionInfo findById(String ruleId) {
        return cMap.get(ruleId);
    }


    private static List<ParamInfo> getParamInfos(String ids) {
        List<ParamInfo> list = new ArrayList<>();

        if (StringUtils.isNotBlank(ids)) {
            final String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(pMap.get(split[i]));
            }
        }
        return list;
    }


    static void test() throws Exception {
        //五分钟之内错误次数大于3


        String express = " 用户时间段锁定(uId,lockType,5)>3";
//        String express = " 用户时间段锁定(\"3\",\"1\",5)>3";

        ExpressRunner runner = new ExpressRunner();
        runner.addFunctionOfClassMethod("用户时间段锁定", demoTest.class.getName(), "checkUserLogType", new Class[]{String.class, String.class, Long.class}, null);
//        runner.addFunctionOfClassMethod("用户时间段锁定", demoTest.class.getName(), "checkUserLogType", new Class[]{String.class, String.class},                null);

        IExpressContext<String, Object> expressContext = new DefaultContext<>();
        expressContext.put("uId", "3");
        expressContext.put("lockType", "1");

        final Object execute = runner.execute(express, expressContext, null, false, false);
        System.err.println(execute);
//        new Date().getTime()-5 * 60 *1000


    }

    public static long checkUserLogType(String userId, String type) {
        return checkUserLogType(userId, type, null);
    }

    /**
     * @param userId
     * @param type
     * @param time   几分钟
     * @return
     */
    public static long checkUserLogType(String userId, String type, Long time) {
        if (null != time) {
            time = new Date().getTime() - time * 60 * 1000;
        }
        Long finalTime = time;
        return userLogList.stream().filter(u -> u.getId().equals(userId) && u.getType().equals(type) && (null == finalTime || u.getTime() > finalTime)).count();
    }


    static void verify() throws Exception {

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
//                    if (null != paramInfos) {
                    final Map<String, String> collect = paramInfos.stream().collect(Collectors.toMap(ParamInfo::getTitle, ParamInfo::getField));
                    final String[] replaceAll = {expression};
                    collect.forEach((k, v) -> replaceAll[0] = replaceAll[0].replaceAll(k, v).replaceAll("指标", "rule"));
                    rMap.put(matcher.group(1) + ruleId, replaceAll[0]);
//                    } else {
//                        rMap.put(matcher.group(1) + ruleId, expression);
//                    }
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


//    void beanDemo() throws Exception {
//
//        String statement = "getUserInfo(uId)";
//
//        Map<String, Object> innerContext = new HashMap<String, Object>();
//
//        innerContext.put("uId", "1");
//
//        ExpressRunner runner = new ExpressRunner();
//
////        final IUIndexService expressBeanRunnerBean = qlExpressBeanRunner.getBean(IUIndexService.class);
//
////        final IExpressContext beanContext = qlExpressBeanRunner.getBeanContext(innerContext);
//
//        runner.addFunctionOfServiceMethod("getUserInfo", qlExpressBeanRunner.getBean(IUIndexService.class), "findById", new Class[]{String.class}, null);
//
//        final Object execute = runner.execute(statement, qlExpressBeanRunner.getBeanContext(innerContext), null, false, false);
//
//
//        System.out.println(execute);
//
//
//    }

    static void likeTest() throws Exception {
//       import  java.lang.String;
//        String express = "return abc.acb.size() == 1 ";
//        String express = "return ! (aaa in bbb) ";
        String express = "a1 in a2";

        ExpressRunner runner = new ExpressRunner();
        IExpressContext<String, Object> expressContext = new DefaultContext<>();

        HashMap<String, Object> aaa = new HashMap<>();
        aaa.put("acb", Collections.singletonList("a"));
        expressContext.put("abc", aaa);
        expressContext.put("aaa", "a");
        expressContext.put("a2", Arrays.asList("a", "b"));
        expressContext.put("a1", getUnsafe().allocateInstance(String.class));
        expressContext.put("a2", getUnsafe().allocateInstance(ArrayList.class));

        final Object execute = runner.execute(express, expressContext, null, false, false);

        System.out.println(express);
        System.out.println(execute);

    }


    static void demo() throws Exception {


        String[] express = {"如果 ( ruleId:088 ){" +
                "    如果(ruleId:011 and ruleId:012 ){" +
                "        返回 'true'" +
                "    }否则{" +
                "       如果(ruleId:013 or ruleId:014 ){" +
                "        返回 '第二条规则'" +
                "       }" +
                "    }" +
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

    public static void test1() throws Exception {

        String demo = "if ( used_node == 'xingwei' ){" +
                "    if(verification == 1 and safe_credit == 高 ){" +
                "        return true;" +
                "    }else{" +
                "       if(lock_num >= 3 or used_ip in ['127.0.0.1','192.168.1.1'] ){" +
                "        return '第二条规则';" +
                "       }" +
                "    }" +
                "}";


//        String demo = "         如果 ( used_node == 'xingwei' ){" +
//                "         如果(verification == 1 and safe_credit == 高 ){" +
//                "         返回 'true'" +
//                "         }否则{" +
//                "         如果(lock_num >= 3 or used_ip in ['127.0.0.1','192.168.1.1'] ){" +
//                "         返回 '第二条规则'" +
//                "         }" +
//                "         }" +
//                "         } 返回 '默认'";

//        com.ql.util.express.ExpressRunner.parseInstructionSet

        IExpressContext<String, Object> expressContext = new DefaultContext<>();
//        Map<String,String> map = new HashMap<>();
        expressContext.put("lock_num", "5");
        expressContext.put("used_node", "xingwei");
        expressContext.put("used_ip", "127.0.0.1");
        expressContext.put("verification", "1");
        expressContext.put("safe_credit", "中");
//        expressContext.put("rule",map);
        ExpressRunner runner = new ExpressRunner();

        final boolean b = runner.checkSyntax(demo);


        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("否则", "else", null);
        runner.addOperatorWithAlias("返回", "return", null);
//        final Object execute = runner.execute("rule.used_node == 'xingwei'", expressContext, null, false, false);
        final Object execute = runner.execute(demo, expressContext, null, false, false);

        System.err.println(execute);


    }


    public static class UserLog {


        private String id;

        //1 = 锁定
        private String type;

        private Long time;

        public UserLog(String id, String type, Long time) {
            this.id = id;
            this.type = type;
            this.time = time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }
    }

    public static class ConditionInfo {

        private String id;

        private String expression;

        /**
         * 所属规则
         */
        private String ruleId;

        private String paramInfoIds;


        public ConditionInfo(String id, String expression, String ruleId, String paramInfoIds) {
            this.id = id;
            this.expression = expression;
            this.ruleId = ruleId;
            this.paramInfoIds = paramInfoIds;
        }

        /**
         * 参数集合
         */
        private List<ParamInfo> paramInfos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getRuleId() {
            return ruleId;
        }

        public void setRuleId(String ruleId) {
            this.ruleId = ruleId;
        }

        public String getParamInfoIds() {
            return paramInfoIds;
        }

        public void setParamInfoIds(String paramInfoIds) {
            this.paramInfoIds = paramInfoIds;
        }

        public List<ParamInfo> getParamInfos() {
            return paramInfos;
        }

        public void setParamInfos(List<ParamInfo> paramInfos) {
            this.paramInfos = paramInfos;
        }
    }


    public static class ParamInfo {
        private String id;

        private String field;

        private String title;

        public ParamInfo(String id, String field, String title) {
            this.id = id;
            this.field = field;
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    public static class UIndex {
        private String id;

        private String userName;

        private String indexIds;


        /**
         * 动态指标集合
         */
        private Map<String, String> indexInfo;

        /**
         * 参数集合
         */
        private List<ParamInfo> paramInfos;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIndexIds() {
            return indexIds;
        }

        public void setIndexIds(String indexIds) {
            this.indexIds = indexIds;
        }

        public Map<String, String> getIndexInfo() {
            return indexInfo;
        }

        public void setIndexInfo(Map<String, String> indexInfo) {
            this.indexInfo = indexInfo;
        }

        public List<ParamInfo> getParamInfos() {
            return paramInfos;
        }

        public void setParamInfos(List<ParamInfo> paramInfos) {
            this.paramInfos = paramInfos;
        }
    }


}
