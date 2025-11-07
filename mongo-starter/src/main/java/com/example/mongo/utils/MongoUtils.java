package com.example.mongo.utils;

import com.example.mongo.entity.DistributedLock;
import com.example.mongo.rest.Page;
import com.example.mongo.service.DistributedLockService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-04 10:12
 * @Version V1.0
 */
public class MongoUtils implements Serializable {

    private final static Logger logger = LoggerFactory.getLogger(MongoUtils.class);

    private static final long serialVersionUID = 2427942411914844609L;

    private final MongoTemplate mongoTemplate;


    private final DistributedLockService distributedLockService;

    public MongoUtils(MongoTemplate mongoTemplate, DistributedLockService distributedLockService) {
        this.mongoTemplate = mongoTemplate;
        this.distributedLockService = distributedLockService;
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
     * 批量添加数据
     *
     * @param beans
     * @param <T>
     * @return
     */
    public <T> boolean saveAll(List<T> beans) {
        final Collection<T> insertAll = mongoTemplate.insertAll(beans);
        return beans.size() == insertAll.size();
    }

    /**
     * 更新条件全部数据
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean update(T t) {
        return updateMulti(createQuery(t), t);
    }

    /**
     * 通过条件修改所找到的所有数据
     *
     * @param query
     */
    public <T> boolean updateMulti(Query query, T t) {
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
        return updateMulti(query, bean);
    }

    /**
     * 更新条件第一条数据
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean updateFirst(T t) {
        return updateFirst(createQuery(t), t);
    }

    /**
     * 更新条件第一条数据
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> boolean updateFirst(Query query, T t) {
        Update update = createUpdate(t);
        return mongoTemplate.updateFirst(query, update, t.getClass()).wasAcknowledged();
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
     * 根据实例条件查询分页数据
     *
     * @param t
     * @param page
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Page<T> findPage(T t, Page<T> page) {


        final Query query = createQuery(t, page);

        final long count = count(query, t.getClass());

        return new Page<>(findAll(query, (Class<T>) t.getClass()), page, count);
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


    /**
     * 通过条件查询实体记录数
     *
     * @param t
     * @return
     */
    public <T> long count(T t) {
        return count(createQuery(t), t.getClass());
    }

    public <T> long count(Query query, Class<T> c) {
        return mongoTemplate.count(query, c);
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

    //TODO 非空更新


    public static <T> Update createUpdate(T t) {
        Update update = new Update();
        if (null != t) {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                convertUpdate(t, update, field, null);
            }
        }
        return update;
    }

    private static <T> void convertUpdate(T t, Update update, Field field, String parentName) {
        field.setAccessible(true);
        try {
            Object value = field.get(t);
            if (ReflectUtil.isNullOrEmpty(value)) {
                return;
            }

            String fieIdName = getFieIdName(field, parentName);
            final ClassLoader classLoader = value.getClass().getClassLoader();
            if (null != classLoader) {
                try {
                    final Field[] declaredFields = value.getClass().getDeclaredFields();
                    if (declaredFields.length > 0) {
                        for (Field declaredField : declaredFields) {
                            convertUpdate(value, update, declaredField, fieIdName);
                        }
                    }
                } catch (Exception e) {
                    logger.error("convertUpdate ClassLoader error", e);
                    update.set(fieIdName, value);
                }
            } else {
                if (value instanceof Map) {
                    ((Map<?, ?>) value).forEach((k, v) -> update.set(fieIdName + "." + k, v));
                } else {
                    update.set(fieIdName, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("convertUpdate error", e);
        }
    }


    /**
     * 根据object和它的field上面的注解构造成Criteria
     *
     * @param t
     * @return
     */
    public static <T> Query createQuery(T t) {
        return createQuery(t, (Pageable) null);
    }

    public static <T> Query createQuery(T t, Page<T> page) {
        if (null == page) {
            return createQuery(t, (Pageable) null);
        } else {
            return createQuery(t, page.toPageRequest());
        }
    }

    public static <T> Query createQuery(T t, Pageable pageable) {
        Query query = new Query();
        if (t == null) {
            return query;
        }
        for (Field field : t.getClass().getDeclaredFields()) {
            convertQuery(t, query, field, null);
        }
        if (null != pageable) {
            query.with(pageable);
        }
        return query;
    }


    private static <T> void convertQuery(T t, Query query, Field field, String parentName) {
        field.setAccessible(true);
        try {
            Object value = field.get(t);
            if (ReflectUtil.isNullOrEmpty(value)) {
                return;
            }

            String fieIdName = getFieIdName(field, parentName);
            final ClassLoader classLoader = value.getClass().getClassLoader();
            if (null != classLoader) {
                try {
                    final Field[] declaredFields = value.getClass().getDeclaredFields();
                    if (declaredFields.length > 0) {
                        for (Field declaredField : declaredFields) {
                            convertQuery(value, query, declaredField, fieIdName);
                        }
                    }
                } catch (Exception e) {
                    logger.error("convertUpdate ClassLoader error", e);
                    query.addCriteria(Criteria.where(fieIdName).is(value));
                }
            } else {
                if (value instanceof Map) {
                    ((Map<?, ?>) value).forEach((k, v) -> query.addCriteria(Criteria.where(fieIdName + "." + k).is(v)));
                } else {
                    query.addCriteria(Criteria.where(fieIdName).is(value));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("convertUpdate error", e);
        }
    }

    private static String getFieIdName(Field field, String parentName) {
        final org.springframework.data.mongodb.core.mapping.Field annotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
        String fieIdName = field.getName();

        if (null != annotation) {
            if (StringUtils.hasText(annotation.value())) {
                fieIdName = annotation.value();
            }
        }
        //x.xx
        if (StringUtils.hasText(parentName)) {
            fieIdName = parentName.concat(".").concat(fieIdName);
        }
        return fieIdName;
    }


    //TODO 关联查询     AggregationOperation            Aggregation         mongoTemplate.aggregate

    /**
     * 加锁(默认30分钟)
     *
     * @param key
     * @return
     */
    public boolean lock(String key) {
        return lock(key, 3600L);
    }

    /**
     * 尝试获取锁（使用 findAndModify 原子操作）
     *
     * @param key           加锁key
     * @param expireSeconds 过期时间(s)
     * @return
     */
    public boolean lock(String key, long expireSeconds) {
        Long now = new Date().getTime();
        Long expireAt = now + expireSeconds * 1000;

        try {
            try {
                // 1. 原子插入（唯一键冲突表示锁存在）
                DistributedLock distributedLock = new DistributedLock(key, expireAt);
                distributedLockService.getMongoTemplate().insert(distributedLock);
                return true;
            } catch (DuplicateKeyException e) {
                // 2. 已存在锁，判断是否过期
                DistributedLock distributedLock = distributedLockService.findById(key);
                if (null != distributedLock) {
                    if (distributedLock.getExpire() > now) {
                        return false;
                    }
                    distributedLock.setExpire(expireAt);
                    return distributedLockService.updateFirst(distributedLock);
                } else {
                    return lock(key, expireSeconds);
                }
            }

        } catch (Exception e) {
            logger.error("lock error:", e);
            return false;
        }
    }

    /**
     * 释放锁
     */
    public void unlock(String key) {
        distributedLockService.deleteById(key);
    }

}
