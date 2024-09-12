package com.wyci.jpa.repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-09-12 15:30
 * @Version V1.0
 */
public class JpaDefaultRepositoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    public JpaDefaultRepositoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaDefaultRepository(entityManager);
    }


    private static class JpaDefaultRepository<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        public JpaDefaultRepository(EntityManager em) {
            super(em);
            this.em = em;
        }

        @SuppressWarnings({"unchecked", "rawtypes", "hiding"})
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
            SimpleJpaRepository<?, ?> repo = new JpaDefaultRepositoryImpl(metadata.getDomainType(), entityManager);
            return repo;
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return JpaDefaultRepositoryImpl.class;
        }
    }

}
