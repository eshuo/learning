package com.wyci.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Description 自定义方法
 * @Author wangshuo
 * @Date 2024-09-12 14:45
 * @Version V1.0
 */

@NoRepositoryBean
public interface JpaDefaultRepository<T, ID> extends JpaRepository<T, ID> {


    /**
     * 根据实例条件删除
     *
     * @param t
     */
    void removeAll(T t);


}
