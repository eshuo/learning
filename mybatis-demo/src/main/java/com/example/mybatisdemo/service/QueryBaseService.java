package com.example.mybatisdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisdemo.domain.request.RequestPage;
import com.example.mybatisdemo.domain.response.ResponsePage;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description 基础查询操作
 * @Author wangshuo
 * @Date 2022-08-18 10:38
 * @Version V1.0
 */
public interface QueryBaseService<T> extends Serializable {

    //TODO 2023/3/31 WangShuo: mapper方法下移到dao层

    QueryBaseDao<T> getBaseDao();

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     */
    default boolean save(T entity) {
        return getBaseDao().save(entity);
    }

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     */
    default boolean saveBatch(Collection<T> entityList) {
        return getBaseDao().saveBatch(entityList);
    }

    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     */
    default boolean updateById(T entity) {
        return getBaseDao().updateById(entity);
    }

    /**
     * 根据ID删除
     *
     * @param id
     *
     * @return
     */
    default boolean removeById(Serializable id) {
        return getBaseDao().removeById(id);
    }

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体
     *
     * @since 3.4.4
     */
    default boolean removeById(T entity) {
        return getBaseDao().removeById(entity);
    }

    /**
     * 删除（根据ID 批量删除）
     *
     * @param list 主键ID或实体列表
     */
    default boolean removeByIds(Collection<T> list) {
        return getBaseDao().removeByIds(list);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    default T getById(Serializable id) {
        return getBaseDao().getById(id);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     */
    default List<T> listByIds(Collection<? extends Serializable> idList) {
        return getBaseDao().listByIds(idList);
    }

    /**
     * 查询所有
     */
    default List<T> list() {
        return getBaseDao().list();
    }

    /**
     * 查询列表
     *
     * @param t
     *
     * @return
     */
    default List<T> list(T t) {
        return getBaseDao().list(t);
    }


    /**
     * 查询分页数据
     *
     * @param page
     * @param <X>
     *
     * @return
     */
    default <X extends RequestPage<T>> ResponsePage<T> page(X page) {
        return getBaseDao().responsePage(page);
    }


    /**
     * 查询分页数据
     *
     * @param t
     * @param page
     * @param <X>
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    default <X extends RequestPage> Page<T> pageList(T t, X page) {
        return getBaseDao().pageList(t, page);
    }

    /**
     * 根据条件统计
     *
     * @param t
     *
     * @return
     */
    default long count(T t) {
        return getBaseDao().count(t);
    }

    /**
     * 得到一个
     *
     * @param t t
     *
     * @return {@code T}
     */
    default T getOne(T t) {
        return getBaseDao().getOne(t);
    }

}
