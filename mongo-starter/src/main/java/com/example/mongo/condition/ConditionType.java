package com.example.mongo.condition;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-09 09:38
 * @Version V1.0
 */
public enum ConditionType {

    /**
     * 全部匹配
     */
    ALL,
    GT,
    GTE,
    IN,
    NOT_IN,
    /**
     * like %xx%
     */
    CONCAT,
    /**
     * like xx%
     */
    START_LIKE,
    /**
     * like %xx
     */
    END_LIKE,
    /**
     * ==
     */
    IS,
    /**
     *  true : null   flase: notNull
     */
    IS_NULL,
    LT,
    LTE,
    MOD,
    /**
     * not eq
     */
    NE,
    /**
     * = not_in
     */
    NIN,
    NOT,
    /**
     * 正则
     */
    REGEX,
    /**
     * 元素个数
     */
    SIZE,
    TYPE,
    SAMPLE_RATE;

    public static ConditionType judgeValue(String code) {

        ConditionType[] values = ConditionType.values();
        for (ConditionType value : values) {
            if (value.name().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
