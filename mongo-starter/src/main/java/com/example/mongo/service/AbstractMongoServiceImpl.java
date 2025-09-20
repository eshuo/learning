package com.example.mongo.service;

import com.example.mongo.rest.Page;
import com.example.mongo.utils.FormatUtils;
import com.example.mongo.utils.ReflectUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.QueryMapper;
import org.springframework.data.mongodb.core.convert.UpdateMapper;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-08 10:57
 * @Version V1.0
 */
public abstract class AbstractMongoServiceImpl<T> implements IMongoBaseService<T> {

    private final static Logger log = LoggerFactory.getLogger(AbstractMongoServiceImpl.class);

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected MappingMongoConverter mappingMongoConverter;

    protected QueryMapper queryMapper;

    protected UpdateMapper updateMapper;

    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * 打印日志开关
     */
    @Value("${eshuo.mongodb.print:true}")
    private boolean print;


    @PostConstruct
    public void init() {
        queryMapper = new QueryMapper(mappingMongoConverter);
        updateMapper = new UpdateMapper(mappingMongoConverter);
    }


    @Override
    public T save(T entity) {
        logSave(entity);
        return mongoTemplate.save(entity);
    }

    @Override
    public boolean saveAll(List<T> beans) {
        logSave(beans);
        final Collection<T> insertAll = mongoTemplate.insertAll(beans);
        return beans.size() == insertAll.size();
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        logDelete(query);
        mongoTemplate.remove(this.findById(id));
    }

    @Override
    public void delete(T t) {
        logDel(t);
        mongoTemplate.remove(t);
    }


    @Override
    public void delete(Query query, Class<T> tClass) {
        logDelete(query);
        mongoTemplate.remove(query, tClass);
    }

    /**
     * 根据对象的属性删除
     *
     * @param t
     */
    @Override
    public void deleteByCondition(T t) {
        Query query = createQuery(t);
        delete(query, getEntityClass());
    }


    @Override
    public void updateById(String id, T t) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        updateMulti(query, t);
    }

    @Override
    public void update(T t) {
        updateMulti(createQuery(t), t);
    }


    @Override
    public boolean updateMulti(Query query, T t) {
        Update update = createUpdate(t);
        logUpdate(query, update);
        return mongoTemplate.updateMulti(query, update, t.getClass()).wasAcknowledged();
    }

    @Override
    public boolean updateFirst(T t) {
        return updateFirst(createQuery(t), t);
    }

    @Override
    public boolean updateFirst(Query query, T t) {
        Update update = createUpdate(t);
        logUpdate(query, update);
        return mongoTemplate.updateFirst(query, update, t.getClass()).wasAcknowledged();
    }

    @Override
    public List<T> find(Query query) {
        logQuery(query);
        return mongoTemplate.find(query, getEntityClass());
    }

    @Override
    public T findOne(Query query) {
        logQuery(query);
        return mongoTemplate.findOne(query, getEntityClass());
    }

    @Override
    public T findOne(T t) {
        return findOne(createQuery(t));
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, getEntityClass());
    }

    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, getEntityClass(), collectionName);
    }

    @Override
    public Page<T> findPage(Page<T> page, Query query) {
        final long count = count(query);
        query.with(page.toPageRequest());
        return new Page<>(findAll(query), page, count);

    }

    @Override
    public Page<T> findPage(Page<T> page, T t) {
        return findPage(page, createQuery(t));
    }

    @Override
    public List<T> findAll(T t) {
        return findAll(createQuery(t));
    }

    @Override
    public List<T> findAll(Query query) {
        logQuery(query);
        return mongoTemplate.find(query, getEntityClass());
    }

    @Override
    public long count(Query query) {
        logCount(query);
        return mongoTemplate.count(query, getEntityClass());
    }

    @Override
    public long count(T t) {
        return count(createQuery(t));
    }


    public static <T> Update createUpdate(T t) {
        Update update = new Update();
        for (Field field : getAllFields(t.getClass())) {
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
        return createQuery(t, null);
    }

    public static <T> Query createQuery(T t, Page<T> page) {
        Query query = new Query();
        if (t == null) {
            return query;
        }
        for (Field field :getAllFields(t.getClass())) {
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
                if (value instanceof Map) {
                    String finalFieIdName = fieIdName;
                    ((Map<?, ?>) value).forEach((k, v) -> query.addCriteria(Criteria.where(finalFieIdName + "." + k).is(v)));
                } else {
                    query.addCriteria(Criteria.where(fieIdName).is(value));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("createQuery error", e);
            }
        }
        if (null != page) {
            query.with(page.toPageRequest());
        }
        return query;
    }


    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        List<Class<?>> classHierarchy = new ArrayList<>();

        // 先收集继承链（Object 不要）
        while (clazz != null && clazz != Object.class) {
            classHierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }

        // 逆序遍历：先父类，再子类
        Collections.reverse(classHierarchy);

        for (Class<?> c : classHierarchy) {
            Collections.addAll(fields, c.getDeclaredFields());
        }
        return fields;
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        return getSuperClassGenricType(getClass(), 0);
    }

    /**
     * 获取MongoDB模板操作
     *
     * @return
     */
    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    private Class getSuperClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }


    /**
     * 打印查询语句
     *
     * @param query
     */
    private void logQuery(Query query) {
        if (print) {
            Class<?> clazz = this.getEntityClass();
            MongoPersistentEntity<?> entity = mappingMongoConverter.getMappingContext().getPersistentEntity(clazz);
            Document mappedQuery = queryMapper.getMappedObject(query.getQueryObject(), entity);
            Document mappedField = queryMapper.getMappedObject(query.getFieldsObject(), entity);
            Document mappedSort = queryMapper.getMappedObject(query.getSortObject(), entity);

            String logStr = "\ndb." + FormatUtils.lowerFirst(clazz.getSimpleName()) + ".find(";

            logStr += FormatUtils.bson(mappedQuery.toJson()) + ")";

            if (!query.getFieldsObject().isEmpty()) {
                logStr += ".projection(";
                logStr += FormatUtils.bson(mappedField.toJson()) + ")";
            }

            if (query.isSorted()) {
                logStr += ".sort(";
                logStr += FormatUtils.bson(mappedSort.toJson()) + ")";
            }

            if (query.getLimit() != 0l) {
                logStr += ".limit(" + query.getLimit() + ")";
            }

            if (query.getSkip() != 0l) {
                logStr += ".skip(" + query.getSkip() + ")";
            }
            logStr += ";";

            log.info(logStr);
        }
    }

    /**
     * 打印查询语句
     *
     * @param query
     */
    private void logCount(Query query) {
        if (print) {
            Class<?> clazz = this.getEntityClass();
            MongoPersistentEntity<?> entity = mappingMongoConverter.getMappingContext().getPersistentEntity(clazz);
            Document mappedQuery = queryMapper.getMappedObject(query.getQueryObject(), entity);

            String logStr = "\ndb." + FormatUtils.lowerFirst(clazz.getSimpleName()) + ".find(";
            logStr += FormatUtils.bson(mappedQuery.toJson()) + ")";
            logStr += ".count();";

            log.info(logStr);
        }
    }

    /**
     * 打印查询语句
     *
     * @param query
     */
    private void logDelete(Query query) {
        if (print) {
            Class<?> clazz = this.getEntityClass();
            MongoPersistentEntity<?> entity = mappingMongoConverter.getMappingContext().getPersistentEntity(clazz);
            Document mappedQuery = queryMapper.getMappedObject(query.getQueryObject(), entity);

            String logStr = "\ndb." + FormatUtils.lowerFirst(clazz.getSimpleName()) + ".remove(";
            logStr += FormatUtils.bson(mappedQuery.toJson()) + ")";
            logStr += ";";
            log.info(logStr);
        }
    }

    /**
     * 打印查询语句
     *
     * @param query
     */
    private void logUpdate(Query query, Update update) {
        if (print) {
            Class<?> clazz = this.getEntityClass();
            MongoPersistentEntity<?> entity = mappingMongoConverter.getMappingContext().getPersistentEntity(clazz);
            Document mappedQuery = queryMapper.getMappedObject(query.getQueryObject(), entity);
            Document mappedUpdate = updateMapper.getMappedObject(update.getUpdateObject(), entity);

            String logStr = "\ndb." + FormatUtils.lowerFirst(clazz.getSimpleName()) + ".update(";
            logStr += FormatUtils.bson(mappedQuery.toJson()) + ",";
            logStr += FormatUtils.bson(mappedUpdate.toJson()) + ",";
            logStr += FormatUtils.bson("{multi:" + false + "})");
            logStr += ";";
            log.info(logStr);
        }

    }

    /**
     * 打印查询语句
     *
     * @param object
     */
    private void logSave(Object object) {
        if (print) {
            String logStr = "\ndb." + FormatUtils.lowerFirst(object.getClass().getSimpleName()) + ".save(";
            logStr += gson.toJson(object);
            logStr += ");";
            log.info(logStr);
        }
    }

    /**
     * 打印查询语句
     */
    private void logSave(List<?> list) {
        if (print && list.size() > 0) {
            Object object = list.get(0);

            String logStr = "\ndb." + FormatUtils.lowerFirst(object.getClass().getSimpleName()) + ".save(";
            logStr += gson.toJson(list);
            logStr += ");";
            log.info(logStr);
        }
    }


    private void logDel(Object object) {
        if (print) {
            String logStr = "\ndb." + FormatUtils.lowerFirst(object.getClass().getSimpleName()) + ".delete(";
            logStr += gson.toJson(object);
            logStr += ");";
            log.info(logStr);
        }
    }


}
