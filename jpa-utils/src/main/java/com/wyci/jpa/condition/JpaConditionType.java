package com.wyci.jpa.condition;

/**
 * @Description
 * @Author wangshuo
 * @Version V1.0
 */
public enum JpaConditionType {


    GT,
    GTE,
    LT,
    LTE,
    IN,
    NOT_IN,
    /**
     * like %xx%
     */
    LIKE,
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
    EQ,
    /**
     * true : null   flase: notNull
     */
    IS_NULL,

    /**
     * not eq
     */
    NE,
    ;

}
