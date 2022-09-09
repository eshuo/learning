package com.example.mongo.condition;

/**
 * @Description 查询Condition
 * @Author wangshuo
 * @Date 2022-08-08 16:42
 * @Version V1.0
 */
public class Condition {


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

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "column='" + column + '\'' +
                ", conditionType=" + conditionType +
                ", value=" + value +
                '}';
    }
}
