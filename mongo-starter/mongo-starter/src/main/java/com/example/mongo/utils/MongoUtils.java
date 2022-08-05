package com.example.mongo.utils;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-04 10:12
 * @Version V1.0
 */
public class MongoUtils {


    private final MongoTemplate mongoTemplate;


    public MongoUtils(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param bean
     * @return
     */
    public <T> T save(T bean) {
        return mongoTemplate.save(bean);
    }


    /**
     * 通过条件查询更新数据
     *
     * @param query
     */
    public <T> boolean update(Query query, T t) {
        Update update = createUpdate(t);
        return mongoTemplate.updateMulti(query, update, t.getClass()).wasAcknowledged();
    }


    /**
     * 更新
     *
     * @param bean
     * @param id
     * @return
     */
    public <T> boolean updateById(String id, T bean) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return update(query, bean);
    }

    /**
     * 删除对象
     *
     * @param t
     */
    public <T> void delete(T t) {
        mongoTemplate.remove(t);
    }

    /**
     * 根据对象的属性删除
     *
     * @param t
     */
    public <T> void deleteByCondition(T t) {
        Query query = createQuery(t);
        mongoTemplate.remove(query, t.getClass());
    }

//    find

    /**
     * 通过条件查询实体(集合)
     *
     * @param t
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(T t) {
        return findAll(createQuery(t), (Class<T>) t.getClass());
    }

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     * @return
     */
    public <T> List<T> findAll(Query query, Class<T> clazz) {
        return mongoTemplate.find(query, clazz);
    }


//    public <T> List<T> find(Query query, RequestPage page, Class<T> clazz) {
//        query.with(page.toPageRequest());
//        return mongoTemplate.find(query, clazz);
//    }


    /**
     * 通过条件查询实体记录数
     *
     * @param t
     * @return
     */
    public <T> long count(T t) {
        return mongoTemplate.count(createQuery(t), t.getClass());
    }


    /**
     * 通过一定的条件查询一个实体
     *
     * @param t
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T findOne(T t) {
        return findOne(createQuery(t), (Class<? extends T>) t.getClass());
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public <T> T findOne(Query query, Class<T> clazz) {
        return mongoTemplate.findOne(query, clazz);
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param id
     * @param c
     * @return
     */
    public <T> T findById(String id, Class<T> c) {
        return mongoTemplate.findById(id, c);
    }


    //page query


    public static <T> Update createUpdate(T t) {
        Update update = new Update();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (ReflectUtil.isNullOrEmpty(value)) {
                    continue;
                }
                update.set(field.getName(), value);
            } catch (Exception e) {
                throw new RuntimeException("createUpdate error", e);
            }
        }
        return update;
    }


    /**
     * 根据object和它的field上面的注解构造成Criteria
     *
     * @param t
     * @return
     */
    public static <T> Query createQuery(T t) {
        Query query = new Query();
        if (t == null) {
            return query;
        }
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
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
                //todo and or in gt le ...???
                if (value instanceof Map) {
                    //TODO 集合查询拼接问题
                    String finalFieIdName = fieIdName;
                    ((Map<?, ?>) value).forEach((k, v) -> query.addCriteria(Criteria.where(finalFieIdName + "." + k).is(v)));
                } else {
                    query.addCriteria(Criteria.where(fieIdName).is(value));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("createQuery error", e);
            }
        }
        return query;
    }


}
