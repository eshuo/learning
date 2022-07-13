package com.example.qlexpressdemo.utils;

import com.ql.util.express.ExpressRunner;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-04 10:36
 * @Version V1.0
 */
public class QlRunnerUtils {


    /**
     * 添加基础关键字
     *
     * @param runner
     */
    public static void BasicKeyword(ExpressRunner runner) throws Exception {
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);
        runner.addOperatorWithAlias("并且", "&&", null);
        runner.addOperatorWithAlias("或者", "||", null);
        runner.addOperatorWithAlias("大于", ">", null);
        runner.addOperatorWithAlias("大于等于", ">=", null);
        runner.addOperatorWithAlias("小于", "<", null);
        runner.addOperatorWithAlias("小于等于", "<=", null);
        runner.addOperatorWithAlias("等于", "=", null);
        runner.addOperatorWithAlias("不等于", "!=", null);
        runner.addOperatorWithAlias("返回", "return", null);
    }


    /**
     * 多个表达式转换统一结果
     *
     * @param express 表达式
     * @return List<?>   字符串
     */
    public static String ExpressToFunction(String... express) {
        if (null == express) {
            return null;
        }
        StringBuilder str = new StringBuilder("dataList = new ArrayList();");
        for (int i = 0; i < express.length; i++) {
            String functionName = "exp_to_fun_" + i + "()";
            str.append("function ").append(functionName).append("{").append(express[i]).append("}");
            str.append(" dataList.add(").append(functionName).append(");");
        }
        str.append("return dataList;");
        return str.toString();
    }


}
