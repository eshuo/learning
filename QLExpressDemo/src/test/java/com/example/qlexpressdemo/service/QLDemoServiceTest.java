package com.example.qlexpressdemo.service;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.ql.util.express.*;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-30 13:53
 * @Version V1.0
 */
@SpringBootTest
class QLDemoServiceTest {


    @Autowired
    private IConditionInfoService iConditionInfoService;

    private final static Pattern R_REGEX = Pattern.compile("(ruleId:)([\\S\\d]+)");


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

        final String[] express = {"if ( ruleId:001 or ruleId:002  ) { run  '允许访问';  } else {  run '不允许访问'; }  "};
        final Matcher matcher = R_REGEX.matcher(express[0]);

        Map<String, String> rMap = new HashMap<>();

        while (matcher.find()) {
            final String ruleId = matcher.group(2);
            if (StringUtils.isNotBlank(ruleId)) {
                final ConditionInfo byId = iConditionInfoService.findById(ruleId);
                if (null != byId) {
                    final String expression = byId.getExpression();
                    rMap.put(matcher.group(1) + ruleId, expression);
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
        runner.addOperatorWithAlias("run", "return", null);

        //方法

        //上下文

        //运行

        IExpressContext<String, Object> expressContext = new DefaultContext<>();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("锁定次数", "2");
        dataMap.put("安全信用", "7");

        expressContext.put("指标", dataMap);

        System.err.println("express = " + express[0]);

//        final Object execute = cacheRunner.execute(express[0], expressContext, null, false, false, null);
        final Object execute = runner.execute(express[0], expressContext, null, false, false);

        System.err.println(execute);


    }

    @Test
    void check() {
    }

    @Test
    void score() {
    }
}