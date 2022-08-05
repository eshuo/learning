package com.example.mongo.utils;

import com.example.mongo.rest.request.RequestPage;
import com.example.mongo.rest.response.ResponsePage;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-02 17:46
 * @Version V1.0
 */
public class MongoDBHelper {


    /**
     * 注入template
     */
    public final MongoTemplate mongoTemplate;

    public MongoDBHelper(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private <T> String getCollectionName(Class<T> clazz) {
        return mongoTemplate.getCollectionName(clazz);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * 功能描述: 创建一个集合
     * 同一个集合中可以存入多个不同类型的对象，我们为了方便维护和提升性能，
     * 后续将限制一个集合中存入的对象类型，即一个集合只能存放一个类型的数据
     *
     * @param name 集合名称，相当于传统数据库的表名
     * @return:void
     */
    public void createCollection(String name) {
        mongoTemplate.createCollection(name);
    }

    /**
     * 功能描述: 创建索引
     * 索引是顺序排列，且唯一的索引
     *
     * @param collectionName 集合名称，相当于关系型数据库中的表名
     * @param filedName      对象中的某个属性名
     * @return:java.lang.String
     */
    public String createIndex(String collectionName, String filedName) {
        //配置索引选项
        IndexOptions options = new IndexOptions();
        // 设置为唯一
        options.unique(true);
        //创建按filedName升序排的索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.ascending(filedName), options);
    }


    /**
     * 功能描述: 获取当前集合对应的所有索引的名称
     *
     * @param collectionName
     * @return:java.util.List<java.lang.String>
     */
    public List<String> getAllIndexes(String collectionName) {
        ListIndexesIterable<Document> list = mongoTemplate.getCollection(collectionName).listIndexes();
        List<String> indexes = new ArrayList<>();
        for (Document document : list) {
            document.forEach((s, value) -> {
                //提取出索引的名称
                if (s.equals("name")) {
                    indexes.add(value.toString());
                }
            });
        }
        return indexes;
    }


    public <T> boolean insert(T info) {
        return insert(info, getCollectionName(info.getClass()));
    }

    /**
     * 功能描述: 往对应的集合中插入一条数据
     *
     * @param info           存储对象
     * @param collectionName 集合名称
     * @return:void
     */
    public <T> boolean insert(T info, String collectionName) {
        mongoTemplate.insert(info, collectionName);
        return true;
    }


    /**
     * 功能描述: 往对应的集合中批量插入数据，注意批量的数据中不要包含重复的id
     *
     * @param infos 对象列表
     * @return:void
     */

    public <T> boolean insertMulti(List<T> infos, String collectionName) {
        Collection<T> insert = mongoTemplate.insert(infos, collectionName);
        return insert.size() == infos.size();
    }


    public <T> boolean updateById(String id, T updateObject) {
        return updateById(id, getCollectionName(updateObject.getClass()), updateObject, updateObject.getClass());
    }


    /**
     * 功能描述: 使用索引信息精确更改某条数据
     *
     * @param id             唯一键
     * @param collectionName 集合名称
     * @param updateObject   待更新的内容
     * @return:void
     */
    public <T> boolean updateById(String id, String collectionName, T updateObject) {
        return updateById(id, collectionName, updateObject, updateObject.getClass());
    }

    public <O, T> boolean updateById(String id, String collectionName, O updateObject, Class<T> entityClass) {
        return update(new Query(Criteria.where("id").is(id)), collectionName, updateObject, entityClass);
    }

    public <O> boolean update(Object objectParam, O updateObject) {
        return update(objectParam, getCollectionName(updateObject.getClass()), updateObject, updateObject.getClass());
    }

    public <O> boolean update(Object objectParam, String collectionName, O updateObject) {
        return update(objectParam, collectionName, updateObject, updateObject.getClass());
    }

    public <O, T> boolean update(Object objectParam, String collectionName, O updateObject, Class<T> entityClass) {
        Query query = new Query();
        queryAddCriteria(query, objectParam);
        return update(query, collectionName, updateObject, entityClass);
    }

    public <O> boolean update(Query query, String collectionName, O updateObject) {
        return update(query, collectionName, updateObject, updateObject.getClass());
    }

    public <O, T> boolean update(Query query, String collectionName, O updateObject, Class<T> entityClass) {
        return update(query, createUpdate(updateObject), collectionName, entityClass);
    }

    public <T> boolean update(Query query, Update update, String collectionName, Class<T> entityClass) {
        if (!StringUtils.hasText(collectionName)) {
            collectionName = getCollectionName(entityClass);
        }
        return mongoTemplate.updateMulti(query, update, entityClass, collectionName).wasAcknowledged();
    }


    public <T> Update createUpdate(T updateObject) {
        Update update = new Update();

        for (Field declaredField : updateObject.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                final Object o = declaredField.get(updateObject);
                if (null != o) {
                    if (ReflectUtil.isNullOrEmpty(o)) {
                        continue;
                    }
                    String name = declaredField.getName();
                    if (!("id".equals(name) || "_id".equals(name))) {
                        update.set(name, declaredField.get(updateObject));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("服务出错");
            }

        }
        return update;
    }

    public <T> boolean deleteById(String id, Class<T> clazz) {
        // 设置查询条件，当id=#{id}
        Query query = new Query(Criteria.where("id").is(id));
        return deleteByQuery(getCollectionName(clazz), query, clazz);
    }

    /**
     * 功能描述: 根据id删除集合中的内容
     *
     * @param id             序列id
     * @param collectionName 集合名称
     * @param clazz          集合中对象的类型
     * @return:void
     */

    public <T> boolean deleteById(String id, Class<T> clazz, String collectionName) {
        // 设置查询条件，当id=#{id}
        Query query = new Query(Criteria.where("id").is(id));
        return deleteByQuery(collectionName, query, clazz);
    }


    public <T> T selectById(String id, Class<T> clazz) {
        // 查询对象的时候，不仅需要传入id这个唯一键，还需要传入对象的类型，以及集合的名称
        return selectById(id, clazz, getCollectionName(clazz));
    }

    /**
     * 功能描述: 根据id查询信息
     *
     * @param id             注解
     * @param clazz          类型
     * @param collectionName 集合名称
     * @return:java.util.List<T>
     */

    public <T> T selectById(String id, Class<T> clazz, String collectionName) {
        // 查询对象的时候，不仅需要传入id这个唯一键，还需要传入对象的类型，以及集合的名称
        return mongoTemplate.findById(id, clazz, collectionName);
    }


    public boolean deleteByObjectParam(String collectName, Object objectParam, Class<?> clazz) {
        Query query = new Query();
        queryAddCriteria(query, objectParam);
        return deleteByQuery(collectName, query, clazz);
    }


    public boolean deleteByQuery(String collectName, Query query, Class<?> clazz) {
        return mongoTemplate.remove(query, clazz, collectName).wasAcknowledged();
    }

    public <T> List<T> selectList(T entity) {
        return selectByParam(getCollectionName(entity.getClass()), entity, entity.getClass());
    }

    /**
     * 功能描述: 查询列表信息
     * 将集合中符合对象类型的数据全部查询出来
     *
     * @param collectName 集合名称
     * @return:java.util.List<T>
     */
    public <T> List<T> selectList(String collectName, T objectParam) {
        final List<?> objects = selectByParam(collectName, objectParam, objectParam.getClass());
        return null == objects ? null : (List<T>) objects;
    }


    /**
     * 功能描述: 根据条件查询集合
     *
     * @param collectName 集合名称
     * @param objectParam 查询条件
     * @param clazz       对象类型
     * @return:java.util.List<T>
     */
    public <T> List<T> selectByParam(String collectName, T objectParam, Class<?> clazz) {
        Query query = new Query();
        queryAddCriteria(query, objectParam);
        return selectByQuery(collectName, query, clazz);
    }


    public <T> List<T> selectByQuery(Query query, Class<?> clazz) {
        return selectByQuery(getCollectionName(clazz), query, clazz);
    }

    /**
     * 功能描述: 根据条件查询集合
     *
     * @param collectName 集合名称
     * @param query       查询条件
     * @param clazz       对象类型
     * @return
     */
    public <T> List<T> selectByQuery(String collectName, Query query, Class<?> clazz) {
        final List<?> objects = mongoTemplate.find(query, clazz, collectName);
        return null == objects ? null : (List<T>) objects;
    }


    public <T> T selectOneByObjectParam(Object objectParam, Class<T> clazz) {
        return selectOneByObjectParam(getCollectionName(clazz), objectParam, clazz);
    }

    public <T> T selectOneByObjectParam(String collectName, Object objectParam, Class<T> clazz) {
        Query query = new Query();
        queryAddCriteria(query, objectParam);
        return selectOneByQuery(collectName, query, clazz);
    }

    public <T> T selectOneByQuery(Query query, Class<T> clazz) {
        return selectOneByQuery(getCollectionName(clazz), query, clazz);
    }

    public <T> T selectOneByQuery(String collectName, Query query, Class<T> clazz) {
        return mongoTemplate.findOne(query, clazz, collectName);
    }

    public long countByObjectParam(String collectName, Object objectParam) {
        Query query = new Query();
        queryAddCriteria(query, objectParam);
        return countByQuery(collectName, query);
    }


    public long countByQuery(String collectName, Query query) {
        return mongoTemplate.count(query, collectName);
    }

    public <T> ResponsePage<T> pageByObjectParam(String collectName, RequestPage page, Object objectParam, Class<T> clazz) {
        Query query = new Query();
        queryAddCriteria(query, objectParam);
        return this.pageByQuery(collectName, page, query, clazz);
    }

    /**
     * @param collectName 集合名字
     * @param page        page 里面包含分页的相关信息，数据查询完成后，会赋值到page的data属性中
     * @param query       条件  里面不要包含分页条件
     * @param clazz       接受查询数据的类型class
     * @param <T>
     * @return
     */
    public <T> ResponsePage<T> pageByQuery(String collectName, RequestPage page, Query query, Class<T> clazz) {
        long count = this.countByQuery(collectName, query);
        if (count > 0) {
            return new ResponsePage<>(mongoTemplate.find(query.with(page.toPageRequest()), clazz), page, count);
        }
        return new ResponsePage<T>();
    }

    /**
     * aggregate分页
     *
     * @param conditions 已经写好的条件 不要添加count和skip limit
     * @param page       分页结果,请事先填充正确的MongoDBPage#current,MongoDBPage#size
     * @param clazz      接受结果的类型
     * @param <T>
     * @return
     */
    public <T> ResponsePage<T> aggregatePage(List<AggregationOperation> conditions, String collectionName, RequestPage page, Class<T> clazz) {
        conditions.add(Aggregation.count().as("count"));
        List<HashMap> hashMaps = aggregateData(Aggregation.newAggregation(conditions), collectionName, HashMap.class);
        Long total = hashMaps.isEmpty() ? 0L : Long.parseLong(String.valueOf(hashMaps.get(0).get("count")));

        if (total > 0) {
            conditions.remove(conditions.size() - 1);
            conditions.add(Aggregation.sort(Sort.by(page.toSortOrderList())));
            conditions.add(Aggregation.skip(page.getSkip()));
            conditions.add(Aggregation.limit(page.getSize()));
            Aggregation aggregation = Aggregation.newAggregation(conditions);
            return new ResponsePage<>(aggregateData(aggregation, collectionName, clazz), page, total);
        }
        return new ResponsePage<T>();
    }


    public <T> AggregationResults<T> aggregate(Aggregation aggregation, String collectionName, Class<T> outputType) {
        return mongoTemplate.aggregate(aggregation, collectionName, outputType);
    }


    public <T> List<T> aggregateData(Aggregation aggregation, String collectionName, Class<T> outputType) {
        return aggregate(aggregation, collectionName, outputType).getMappedResults();
    }


    public void queryAddCriteria(Query query, Object objectParam) {
        query.addCriteria(createCriteria(objectParam));
    }

    /**
     * 根据object和它的field上面的注解构造成Criteria
     *
     * @param objectParam
     * @return
     */
    public Criteria createCriteria(Object objectParam) {
        if (objectParam == null) return new Criteria();
        AtomicReference<Criteria> criteria = new AtomicReference<>();

        for (Field field : objectParam.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(objectParam);
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
                    //TODO 集合查询拼接问题
                    String finalFieIdName = fieIdName;
                    ((Map<?, ?>) value).forEach((k, v) -> criteria.set(criteriaAndConditions(criteria.get(), finalFieIdName + "." + k, v)));
                } else {
                    criteria.set(criteriaAndConditions(criteria.get(), fieIdName, value));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("get error", e);
            }


        }
        return criteria.get() == null ? new Criteria() : criteria.get();
    }


    /**
     * criteria 条件连接起来
     *               TODO  or in gt lt ...???
     *
     * @param criteria
     * @param key
     * @param value
     * @return
     */
    private Criteria criteriaAndConditions(Criteria criteria, String key, Object value) {
        if (criteria == null) {
            return Criteria.where(key).is(value);
        } else {

//            if (Objects.equals(criteria.getKey(), key)) {
//                return Criteria.where(key).is(value);
//            } else {
//                return criteria.andOperator(Criteria.where(key).is(value));
            return criteria.and(key).is(value);
//            }
        }
    }


}
