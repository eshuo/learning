package com.wyci.jpa.repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @Description 自定义方法实现
 * @Author wangshuo
 * @Date 2024-09-12 14:53
 * @Version V1.0
 */
public class JpaDefaultRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaDefaultRepository<T, ID> {


    private final EntityManager entityManager;


    public JpaDefaultRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public JpaDefaultRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        entityManager = em;
    }

    /**
     * 根据实例条件删除
     *
     * @param t
     */
    @Override
    public void removeAll(T t) {

        //后续完善
    }
}
