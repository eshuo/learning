package com.example.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-05 14:31
 * @Version V1.0
 */
@NoRepositoryBean
public interface MongoDefaultRepository<T, ID> extends MongoRepository<T, ID> {

    /**
     * 根据传入的对象 修改
     *
     * @param id
     * @param t
     */
    public void update(ID id, T t);




    /**
     * 根据id修改
     *
     * @param id             更新主键
     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
     */
    public void update(ID id, Map<String, Object> updateFieldMap);

    /**
     * 根据传入值修改
     *
     * @param queryFieldMap  key:查询条件的属性  value:对应的属性值
     * @param updateFieldMap key:需要更新的属性  value:对应的属性值
     */
    public void update(Map<String, Object> queryFieldMap, Map<String, Object> updateFieldMap);


    //find  pageFind


    /**
     * 自定义分页查询
     *
     * @param query
     * @param pageable
     * @return
     */
    Page<T> findPageByQuery(Query query, Pageable pageable);

    /**
     * 自定义分页查询
     *
     * @param criteria
     * @param pageable
     * @return
     */
    Page<T> findPageByCriteria(Criteria criteria, Pageable pageable);

}
