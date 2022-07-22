package com.example.qlexpressdemo.utils;

import com.example.qlexpressdemo.bean.rest.QLDemo;
import com.ql.util.express.ExpressRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-04 10:36
 * @Version V1.0
 */
public class QlRunnerUtils {

    /**
     * 空格数据
     */
    public static final String SPACE = " ";

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


    public static String ConditionsInfoToExpress(QLDemo.ConditionsInfo conditionsInfo) {
        StringBuilder express = new StringBuilder();
//        逻辑   规则   逻辑  结果  逻辑  结果

        if (QLDemo.ConditionsInfoEnum.RULE.name().equals(conditionsInfo.getType())) {

        }
        final List<QLDemo.Expressions> conditions = conditionsInfo.getConditions();

        if (!CollectionUtils.isEmpty(conditions)) {
            //括号
            boolean addBrackets = false;
            for (QLDemo.Expressions condition : conditions) {
                final QLDemo.ExpressionsEnum expressionsEnum = QLDemo.ExpressionsEnum.judgeValue(condition.getClassType());
                if (expressionsEnum != null) {
                    final QLDemo.RuleInfoData ruleInfoData = condition.getData();
                    //        如果  规则1 && 规则2 && 规则3 返回 结果1 否则 返回 结果2
                    switch (expressionsEnum) {
                        case SEPARATOR:
                            final QLDemo.SeparatorEnum separatorEnum = QLDemo.SeparatorEnum.judgeValue(ruleInfoData.getValue());
                            if (separatorEnum != null) {
                                switch (separatorEnum) {
                                    case _IF:
                                        express.append(QLDemo.SeparatorEnum._IF.getValue()).append("( ");
                                        addBrackets = true;
                                        break;
                                    case _RETURN:
    //                                    规则体加入
                                        if (addBrackets) {
                                            express.append(" ) ");
                                            addBrackets = false;
                                        }
                                        express.append(" { ").append(QLDemo.SeparatorEnum._RETURN.getValue()).append(SPACE);
                                        break;
                                    case _ELSE:
                                        express.append(QLDemo.SeparatorEnum._ELSE.getValue()).append(SPACE);
                                        break;
                                    case _AND:
                                        express.append(QLDemo.SeparatorEnum._AND.getValue()).append(SPACE);
                                        break;
                                    case _OR:
                                        express.append(QLDemo.SeparatorEnum._OR.getValue()).append(SPACE);
                                        break;
                                    case _NO:
                                        express.append(QLDemo.SeparatorEnum._NO.getValue()).append(SPACE);
                                        break;
                                    case OTHER:
                                        break;
                                    default:
                                        express.append(ruleInfoData.getKey()).append(SPACE);
                                        break;
                                }
                            }else{
                                express.append(ruleInfoData.getValue()).append(SPACE);
                            }
                            break;
                        case INDEX:
//                            express.append(ruleInfoData.getKey()).append(SPACE);
//                            break;
                        case RESULT:
                            express.append(ruleInfoData.getKey()).append(SPACE);
                            break;
                        case NODE_RESULT:
                            express.append(ruleInfoData.getKey()).append(SPACE).append(" } ");
                            break;
                        case RULE:
                            express.append(ruleInfoData.getKey()).append(SPACE);
                            break;
                        default:
                            break;
                    }
                }
            }


        }


        return express.toString();
    }


}
