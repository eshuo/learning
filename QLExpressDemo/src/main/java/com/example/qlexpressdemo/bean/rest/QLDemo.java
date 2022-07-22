package com.example.qlexpressdemo.bean.rest;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-28 14:51
 * @Version V1.0
 */

public interface QLDemo {


    /**
     * 验证
     */
    @Data
    public static class Verify {

        private String ruleId;

        private String userId;

    }


    @Data
    public static class ConditionsInfo {

        /**
         * {@link ConditionsInfoEnum}
         */
        private String type;

        private List<Expressions> conditions;

    }

    @Data
    public static class Expressions {

        private String classType;

        private Integer seq;

        private RuleInfoData data;

    }

    @Data
    public static class RuleInfoData {

        private String key;

        private String value;

        private String type;

    }


    enum ConditionsInfoEnum {
        //规则  指标
        RULE, INDEX,
    }

    enum SeparatorEnum {
        //        逻辑   规则   逻辑  结果  逻辑  结果 其他

        _IF("如果", "if"),
        _RETURN("返回", "return"),
        _ELSE("否则", "else"),
        _AND("&&", "&&"),
        _OR("or", "||"),
        _NO("no", "!"),

        OTHER("other", ""),

        ;

        private String key;

        private String value;

        SeparatorEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static SeparatorEnum judgeValue(String key) {
            SeparatorEnum[] values = SeparatorEnum.values();
            for (SeparatorEnum value : values) {
                if (value.key.equals(key)) {
                    return value;
                }
            }
            return null;
        }
    }


    enum ExpressionsEnum {
        SEPARATOR("逻辑", "separator"),
        INDEX("指标", "index"),
        RULE("规则", "rule"),
        RESULT("结果", "result"),
        NODE_RESULT("行为结果", "node_result"),
        ;

        private String classType;

        private String desc;

        public String getClassType() {
            return classType;
        }

        public String getDesc() {
            return desc;
        }

        ExpressionsEnum(String value, String classType) {
            this.classType = classType;
            this.desc = value;
        }

        public static ExpressionsEnum judgeValue(String classType) {
            ExpressionsEnum[] values = ExpressionsEnum.values();
            for (ExpressionsEnum value : values) {
                if (value.classType.equals(classType)) {
                    return value;
                }
            }
            return null;
        }


    }


}
