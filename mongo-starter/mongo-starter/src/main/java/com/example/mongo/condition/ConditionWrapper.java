package com.example.mongo.condition;

import com.example.mongo.rest.Page;
import com.example.mongo.utils.ReflectUtil;
import lombok.Data;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-09 10:05
 * @Version V1.0
 */
@Data
public class ConditionWrapper<T> {

    boolean orType = false;

    List<Condition> andConditions;

    List<Condition> orConditions;

    T modal;

    //    Condition
    private Condition condition;


    public ConditionWrapper() {
    }

    public ConditionWrapper(T modal) {
        this.modal = modal;
    }

    public ConditionWrapper(Condition condition) {
        this.condition = condition;
    }

    public ConditionWrapper(boolean orType, T modal) {
        this.orType = orType;
        this.modal = modal;
    }

    public ConditionWrapper(boolean orType, Condition condition) {
        this.orType = orType;
        this.condition = condition;
    }


    private void initAnd() {
        if (andConditions == null) {
            this.andConditions = new ArrayList<>();
        }
    }

    private void initOr() {
        if (orConditions == null) {
            this.orConditions = new ArrayList<>();
        }
    }


    public ConditionWrapper<T> and(Condition condition) {
        initAnd();
        this.andConditions.add(condition);
        return this;
    }

    public ConditionWrapper<T> and(String column, ConditionType conditionType, Object value) {
        initAnd();
        this.andConditions.add(new Condition(column, conditionType, value));
        return this;
    }

    public ConditionWrapper<T> or(Condition condition) {
        initOr();
        this.orConditions.add(condition);
        return this;
    }

    public ConditionWrapper<T> or(String column, ConditionType conditionType, Object value) {
        initOr();
        this.orConditions.add(new Condition(column, conditionType, value));
        return this;
    }


    public Query toQuery() {
        return toQuery(null);
    }

    public Query toQuery(Page page) {
        final Criteria criteria = toCriteria();
        final Query query = new Query(criteria);
        if (null != page) {
            query.with(page.toPageRequest());
        }
        return query;
    }

    public Criteria toCriteria() {
        Criteria criteria = new Criteria();

        List<Criteria> criteriaAddList = new ArrayList<>(5);
        List<Criteria> criteriaOrList = new ArrayList<>(5);

        if (null != modal) {
            for (Field field : modal.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(modal);
                    if (ReflectUtil.isNullOrEmpty(value)) {
                        continue;
                    }
                    final org.springframework.data.mongodb.core.mapping.Field annotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                    String fieIdName = field.getName();
                    if (null != annotation) {
                        if (StringUtils.hasText(annotation.value())) {
                            fieIdName = annotation.value();
                        }
                    }
                    if (value instanceof Map) {
                        String finalFieIdName = fieIdName;
                        ((Map<?, ?>) value).forEach((k, v) -> criteriaAddList.add(Criteria.where(finalFieIdName + "." + k).is(v)));
                    } else {
                        criteriaAddList.add(Criteria.where(fieIdName).is(value));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("toCriteria error", e);
                }
            }


        }

        if (!CollectionUtils.isEmpty(andConditions)) {
            initCriteria(criteriaAddList, andConditions);
            criteria.andOperator(criteriaAddList);
        }

        if (!CollectionUtils.isEmpty(orConditions)) {
            initCriteria(criteriaOrList, orConditions);
            criteria.orOperator(criteriaOrList);
        }
        return criteria;
    }

    private void initCriteria(List<Criteria> criteriaList, List<Condition> conditionList) {
        for (Condition condition : conditionList) {
            switch (condition.getConditionType()) {
                case ALL:
                    criteriaList.add(Criteria.where(condition.getColumn()).all(condition.getValue()));
                    break;
                case GT:
                    criteriaList.add(Criteria.where(condition.getColumn()).gt(condition.getValue()));
                    break;
                case GTE:
                    criteriaList.add(Criteria.where(condition.getColumn()).gte(condition.getValue()));
                    break;
                case IN:
                    criteriaList.add(Criteria.where(condition.getColumn()).in((Collection<?>) condition.getValue()));
                    break;
                case NIN:
                case NOT_IN:
                    criteriaList.add(Criteria.where(condition.getColumn()).nin((Collection<?>) condition.getValue()));
                    break;
                case CONCAT:
                    Pattern pattern = Pattern.compile("^.*" + replaceRegExp(condition.getValue()) + ".*$", Pattern.CASE_INSENSITIVE);
                    criteriaList.add(Criteria.where(condition.getColumn()).regex(pattern));
                    break;
                case START_LIKE:
                    pattern = Pattern.compile("^" + replaceRegExp(condition.getValue()) + ".*$", Pattern.CASE_INSENSITIVE);
                    criteriaList.add(Criteria.where(condition.getColumn()).regex(pattern));
                    break;
                case END_LIKE:
                    pattern = Pattern.compile("^.*" + replaceRegExp(condition.getValue()) + "$", Pattern.CASE_INSENSITIVE);
                    criteriaList.add(Criteria.where(condition.getColumn()).regex(pattern));
                    break;
                case IS:
                    criteriaList.add(Criteria.where(condition.getColumn()).is(condition.getValue()));
                    break;
                case IS_NULL:
                    if (Boolean.TRUE.equals(condition.getValue())) {
                        criteriaList.add(Criteria.where(condition.getColumn()).is(null));
                    } else {
                        criteriaList.add(Criteria.where(condition.getColumn()).ne(null));
                    }
                    break;
                case LT:
                    criteriaList.add(Criteria.where(condition.getColumn()).lt(condition.getValue()));
                    break;
                case LTE:
                    criteriaList.add(Criteria.where(condition.getColumn()).lte(condition.getValue()));
                    break;
                case MOD:
                    break;
                case NE:
                    criteriaList.add(Criteria.where(condition.getColumn()).ne(condition.getValue()));
                    break;
                case NOT:
                    //TODO not realized
//                    criteriaList.add(Criteria.where(condition.getColumn()).is(condition.getValue()).not());
                    break;
                case REGEX:
                    criteriaList.add(Criteria.where(condition.getColumn()).regex(String.valueOf(condition.getValue())));
                    break;
                case SIZE:
                    criteriaList.add(Criteria.where(condition.getColumn()).size((Integer) condition.getValue()));
                    break;
                case TYPE:
//                    criteriaList.add(Criteria.where(condition.getColumn()).type((Collection<JsonSchemaObject.Type>) condition.getValue()));
                    break;
                case SAMPLE_RATE:
                    criteriaList.add(Criteria.where(condition.getColumn()).sampleRate((Double) condition.getValue()));
                    break;
            }
        }
    }


    public String replaceRegExp(Object str) {
        if (ObjectUtils.isEmpty(str)) {
            return "";
        }
        return String.valueOf(str).replace("\\", "\\\\").replace("*", "\\*")//
                .replace("+", "\\+").replace("|", "\\|")//
                .replace("{", "\\{").replace("}", "\\}")//
                .replace("(", "\\(").replace(")", "\\)")//
                .replace("^", "\\^").replace("$", "\\$")//
                .replace("[", "\\[").replace("]", "\\]")//
                .replace("?", "\\?").replace(",", "\\,")//
                .replace(".", "\\.").replace("&", "\\&");
    }

}
