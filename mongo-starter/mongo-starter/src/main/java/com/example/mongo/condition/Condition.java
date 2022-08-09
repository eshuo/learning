package com.example.mongo.condition;

import lombok.Data;

import java.util.List;

/**
 * @Description 查询Condition
 * @Author wangshuo
 * @Date 2022-08-08 16:42
 * @Version V1.0
 */
@Data
public class Condition {


    // column   type---enum  value

    private String column;

    private ConditionType conditionType;

    private Object value;


    public Condition() {
    }

    public Condition(String column, ConditionType conditionType, Object value) {
        this.column = column;
        this.conditionType = conditionType;
        this.value = value;
    }
}
