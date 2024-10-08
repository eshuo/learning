package com.wyci.jpa.condition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Description
 * @Author wangshuo
 * @Version V1.0
 */
public class JpaConditionWrapper<T> {

    boolean orType = false;

    List<Condition> andConditions;

    List<Condition> orConditions;

    T modal;

    //    Condition
    private Condition condition;


    public JpaConditionWrapper() {
    }

    public JpaConditionWrapper(T modal) {
        this.modal = modal;
    }

    public JpaConditionWrapper(Condition condition) {
        this.condition = condition;
    }

    public JpaConditionWrapper(boolean orType, T modal) {
        this.orType = orType;
        this.modal = modal;
    }

    public JpaConditionWrapper(boolean orType, Condition condition) {
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


    public JpaConditionWrapper<T> and(Condition condition) {
        initAnd();
        this.andConditions.add(condition);
        return this;
    }

    public JpaConditionWrapper<T> and(String column, JpaConditionType jpaConditionType, Object value) {
        initAnd();
        this.andConditions.add(new Condition(column, jpaConditionType, value));
        return this;
    }

    public JpaConditionWrapper<T> or(Condition condition) {
        initOr();
        this.orConditions.add(condition);
        return this;
    }

    public JpaConditionWrapper<T> or(String column, JpaConditionType jpaConditionType, Object value) {
        initOr();
        this.orConditions.add(new Condition(column, jpaConditionType, value));
        return this;
    }


    public Specification<T> toSpecification() {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> all = new ArrayList<>();
            initAnd();
            initOr();
            if (null != getModal()) {
                //字段  大小写   注解
                for (Field field : modal.getClass().getDeclaredFields()) {
                    convertSpecification(field);
                }
            }
            //and
            if (!andConditions.isEmpty()) {
                all.addAll(buildPredicate(andConditions, root, criteriaBuilder));
            }
            //or
            if (!orConditions.isEmpty()) {
                List<Predicate> predicateList = buildPredicate(orConditions, root, criteriaBuilder);
                all.add(criteriaBuilder.or(predicateList.toArray(new Predicate[0])));
            }
            return criteriaBuilder.and(all.toArray(all.toArray(new Predicate[0])));
        };


    }

    private List<Predicate> buildPredicate(List<Condition> conditionList, Root<T> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> condition = new ArrayList<>();
        for (Condition conditionInfo : conditionList) {
            if (null != conditionInfo) {
                String column = conditionInfo.getColumn();
                Expression expression = root.get(column);
                Object conditionInfoValue = conditionInfo.getValue();
                switch (conditionInfo.getConditionType()) {
                    case GT:
                        condition.add(criteriaBuilder.greaterThan(expression, (Comparable) conditionInfoValue));
                        break;
                    case GTE:
                        condition.add(criteriaBuilder.greaterThanOrEqualTo(expression, (Comparable) conditionInfoValue));
                        break;
                    case IN:
                        condition.add(expression.in(conditionInfoValue));
                        break;
                    case NOT_IN:
                        condition.add(expression.in(conditionInfoValue).not());
                        break;
                    case LIKE:
                        condition.add(criteriaBuilder.like(expression, "%" + conditionInfoValue + "%"));
                        break;
                    case START_LIKE:
                        condition.add(criteriaBuilder.like(expression, "%" + conditionInfoValue));

                        break;
                    case END_LIKE:
                        condition.add(criteriaBuilder.like(expression, conditionInfoValue + "%"));
                        break;
                    case EQ:
                        condition.add(criteriaBuilder.equal(expression, conditionInfoValue));
                        break;
                    case IS_NULL:
                        if ((Boolean) conditionInfoValue) {
                            condition.add(criteriaBuilder.isNull(expression));
                        } else {
                            condition.add(criteriaBuilder.isNotNull(expression));
                        }
                        break;
                    case LT:
                        condition.add(criteriaBuilder.lessThan(expression, (Comparable) conditionInfoValue));
                        break;
                    case LTE:
                        condition.add(criteriaBuilder.lessThanOrEqualTo(expression, (Comparable) conditionInfoValue));
                        break;
                    case NE:
                        condition.add(criteriaBuilder.notEqual(expression, conditionInfoValue));
                        break;
                }
            }
        }
        return condition;
    }

    private void convertSpecification(Field field) {
        field.setAccessible(true);
        try {
            Object value = field.get(modal);
            if (isNullOrEmpty(value)) {
                return;
            }

            String fieIdName = getFieIdName(field);
            //映射类?
            final ClassLoader classLoader = value.getClass().getClassLoader();
            if (null != classLoader) {
                try {
                    final Field[] declaredFields = value.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        convertSpecification(declaredField);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("convertSpecification ClassLoader error", e);
                }
            } else {
                // 比较直
                andConditions.add(new Condition(fieIdName, JpaConditionType.EQ, value));
            }
        } catch (Exception e) {
            throw new RuntimeException("convertSpecification error", e);
        }
    }


    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : object) {
                if (!isNullOrEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    private static String getFieIdName(Field field) {
//        final Column annotation = field.getAnnotation(Column.class);
        String fieIdName = field.getName();
//        if (null != annotation) {
//            if (StringUtils.hasText(annotation.name())) {
//                fieIdName = annotation.name();
//            }
//        } else {
//            //驼峰 ?
//
//        }
        return fieIdName;
    }

    public List<Condition> getAndConditions() {
        return andConditions;
    }

    public void setAndConditions(List<Condition> andConditions) {
        this.andConditions = andConditions;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public T getModal() {
        return modal;
    }

    public void setModal(T modal) {
        this.modal = modal;
    }

    public List<Condition> getOrConditions() {
        return orConditions;
    }

    public void setOrConditions(List<Condition> orConditions) {
        this.orConditions = orConditions;
    }

    public boolean isOrType() {
        return orType;
    }

    public void setOrType(boolean orType) {
        this.orType = orType;
    }
}
