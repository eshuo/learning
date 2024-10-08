package com.wyci.jpa.condition;

/**
 * @Description 查询Condition
 * @Author wangshuo
 * @Version V1.0
 */
public class Condition {


    private String column;

    private JpaConditionType jpaConditionType;

    private Object value;


    public Condition() {
    }

    public Condition(String column, JpaConditionType jpaConditionType, Object value) {
        this.column = column;
        this.jpaConditionType = jpaConditionType;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public JpaConditionType getConditionType() {
        return jpaConditionType;
    }

    public void setConditionType(JpaConditionType jpaConditionType) {
        this.jpaConditionType = jpaConditionType;
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
                ", conditionType=" + jpaConditionType +
                ", value=" + value +
                '}';
    }
}
