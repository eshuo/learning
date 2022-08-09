package com.example.mongo.service;

import com.example.mongo.rest.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-08 10:56
 * @Version V1.0
 */
public interface IMongoBaseService<T> {
    /**
     * 保存一个对象到mongodb
     *
     * @param entity
     * @return
     */
    public T save(T entity);


    /**
     * 批量添加数据
     *
     * @param beans
     * @return
     */
    public boolean saveAll(List<T> beans);


    /**
     * 根据id删除对象
     */
    public void deleteById(String id);

    /**
     * 根据对象的属性删除
     *
     * @param t
     */
    public void delete(T t);


    /**
     * 根据条件删除
     *
     * @param query
     * @param tClass
     */
    public void delete(Query query, Class<T> tClass);

    /**
     * 根据对象的属性删除
     *
     * @param t
     */
    public  void deleteByCondition(T t);

    /**
     * 根据id进行更新
     *
     * @param id
     * @param t
     */
    public void updateById(String id, T t);


    /**
     * 根据对象的属性查询
     *
     * @param t
     * @return
     */
    public void update(T t);



    /**
     * 通过条件修改所找到的所有数据
     *
     * @param query
     */
    public  boolean updateMulti(Query query, T t);


    /**
     * 更新条件第一条数据
     *
     * @param t
     * @return
     */
    public  boolean updateFirst(T t);

    /**
     * 更新条件第一条数据
     *
     * @param t
     * @return
     */
    public  boolean updateFirst(Query query, T t);



    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    public List<T> find(Query query);

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public T findOne(Query query);

    /**
     * 通过一定的条件查询一个实体
     *
     * @param t
     * @return
     */
    public T findOne(T t);


    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     *
     * @param id
     * @param collectionName 集合名
     * @return
     */
    public T findById(String id, String collectionName);

    /**
     * 通过条件查询,查询分页结果
     *
     * @param page
     * @param query
     * @return
     */
    public Page<T> findPage(Page<T> page, Query query);

    public Page<T> findPage(Page<T> page, T t);


    /**
     * 通过条件查询实体(集合)
     *
     * @param t
     * @return
     */
    public  List<T> findAll(T t);

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     * @return
     */
    public  List<T> findAll(Query query);



    /**
     * 求数据总和
     *
     * @param query
     * @return
     */
    public long count(Query query);

    /**
     * 通过条件查询实体记录数
     *
     * @param t
     * @return
     */
    public  long count(T t);

    /**
     * 获取MongoDB模板操作
     *
     * @return
     */
    public MongoTemplate getMongoTemplate();
}
