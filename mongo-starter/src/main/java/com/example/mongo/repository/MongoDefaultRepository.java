package com.example.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-05 14:31
 * @Version V1.0
 */
@NoRepositoryBean
public interface MongoDefaultRepository<T, ID> extends MongoRepository<T, ID> {

//    /**
//     * 通过条件修改所找到的所有数据
//     *
//     * @param t
//     */
//    public void update(T t);


    /**
     * 根据传入的对象 修改
     *
     * @param id
     * @param t
     */
    public void update(ID id, T t);


    public void update(Query query, T t);

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
     * 统计
     *
     * @param query 查询
     * @return long
     */
    long count(Query query);


    /**
     * query查询
     *
     * @param query 查询
     * @return {@code List<T>}
     */
    List<T> findByQuery(Query query);


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


    /**
     * @className IBehaviourInfoDAO
     * @Description 根据时间段查询属性出现频率
     * @author wangyu
     * @date 2022/8/18,18:00
     */
    public List<Object> findByAgg(Criteria criteria, Query query, Aggregation aggregation, T t, Object object);

    /**
     * @className IBehaviourInfoDAO
     * @Description 查询最后一次出现的属性
     * @author wangyu
     * @date 2022/8/18,18:00
     */
    public Object findOneByQuery(Query query, Object object);


    /**
     * @className IBehaviourInfoDAO
     * @Description 保存或更新
     * @author wangyu
     * @date 2022/8/18,18:00
     */
    public void saveOrUpdate(Criteria criteria, T t);

}
