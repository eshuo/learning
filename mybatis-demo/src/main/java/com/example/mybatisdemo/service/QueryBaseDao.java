package com.example.mybatisdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisdemo.domain.request.RequestPage;
import com.example.mybatisdemo.domain.response.ResponsePage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

/**
 * @Description 基础数据库操作
 * @Author wangshuo
 * @Date 2023-03-31 15:03
 * @Version V1.0
 */
public interface QueryBaseDao<T> extends IService<T> {

    final static Logger logger = LoggerFactory.getLogger(QueryBaseDao.class);

    /**
     * 查询列表
     *
     * @param t
     *
     * @return
     */
    default List<T> list(T t) {
        return list(new LambdaQueryWrapper<>(copyProperties(t)));
    }


    /**
     * 查询分页数据
     *
     * @param page
     * @param <X>
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    default <X extends RequestPage<T>> Page<T> pageList(X page) {
        return getBaseMapper().selectPage(page.toPage(), new LambdaQueryWrapper<>(copyProperties(page.getQueryData())));
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
    default <X extends RequestPage<T>> Page<T> pageList(T t, X page) {
        return getBaseMapper().selectPage(page.toPage(), new LambdaQueryWrapper<>(copyProperties(t)));
    }


    /**
     * 查询分页数据
     *
     * @param page
     * @param <X>
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    default <X extends RequestPage<T>> ResponsePage<T> responsePage(X page) {
        final Page<T> queryPage = getBaseMapper().selectPage(page.toPage(), new LambdaQueryWrapper<>(copyProperties(page.getQueryData())));
        return new ResponsePage<T>(queryPage.getRecords(), page, queryPage.getTotal());
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
    default <X extends RequestPage<T>> ResponsePage<T> responsePage(T t, X page) {
        final Page<T> queryPage = getBaseMapper().selectPage(page.toPage(), new LambdaQueryWrapper<>(copyProperties(t)));
        return new ResponsePage<T>(queryPage.getRecords(), page, queryPage.getTotal());
    }


    /**
     * 根据条件统计
     *
     * @param t
     *
     * @return
     */
    default long count(T t) {
        return getBaseMapper().selectCount(new LambdaUpdateWrapper<>(copyProperties(t)));
    }

    /**
     * 得到一个
     *
     * @param t t
     *
     * @return {@code T}
     */
    default T getOne(T t) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<>(copyProperties(t)));
    }


    default T copyProperties(T t) {
        T newInstance;
        try {
            newInstance = (T) t.getClass().newInstance();
            BeanUtils.copyProperties(t, newInstance, getNullPropertyNames(t));
        } catch (Exception e) {
            logger.error("copyProperties error :", e);
            return t;
        }
        return newInstance;
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (ObjectUtils.isEmpty(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
