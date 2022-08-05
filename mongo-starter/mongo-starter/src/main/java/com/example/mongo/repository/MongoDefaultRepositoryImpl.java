package com.example.mongo.repository;

import com.example.mongo.utils.MongoUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-05 14:50
 * @Version V1.0
 */
public class MongoDefaultRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements MongoDefaultRepository<T, ID> {

    protected final MongoOperations mongoOperations;

    protected final MongoEntityInformation<T, ID> entityInformation;

    /**
     * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and {@link MongoTemplate}.
     *
     * @param metadata        must not be {@literal null}.
     * @param mongoOperations must not be {@literal null}.
     */
    public MongoDefaultRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.entityInformation = metadata;
    }


    @Override
    public void update(ID id, T t) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        final Update update = MongoUtils.createUpdate(t);
        this.mongoOperations.updateFirst(query, update, entityInformation.getJavaType());
    }

    @Override
    public void update(ID id, Map<String, Object> updateFieldMap) {
        if (!CollectionUtils.isEmpty(updateFieldMap)) {
            Query query = new Query().addCriteria(Criteria.where("id").is(id));
            Update update = new Update();
            updateFieldMap.forEach(update::set);
            this.mongoOperations.updateFirst(query, update, entityInformation.getJavaType());
        }
    }

    @Override
    public void update(Map<String, Object> queryFieldMap, Map<String, Object> updateFieldMap) {
        Criteria criteria = new Criteria();
        if (null != queryFieldMap && !queryFieldMap.isEmpty()) {
            queryFieldMap.forEach((key, value) -> criteria.and(key).is(value));
        }

        if (updateFieldMap != null && !updateFieldMap.isEmpty()) {
            Update update = new Update();
            updateFieldMap.forEach(update::set);
            mongoOperations.updateMulti(new Query(criteria), update, entityInformation.getJavaType());
        }
    }

    @Override
    public Page<T> findPageByQuery(Query query, Pageable pageable) {
        long total = mongoOperations.count(query, entityInformation.getJavaType());
        List<T> list = mongoOperations.find(query.with(pageable), entityInformation.getJavaType());
        return new PageImpl<T>(list, pageable, total);
    }

    @Override
    public Page<T> findPageByCriteria(Criteria criteria, Pageable pageable) {
        return findPageByQuery(new Query(criteria), pageable);
    }
}
