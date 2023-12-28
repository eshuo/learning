package com.example.mongo.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.io.Serializable;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

/**
 * @Description 自定义接口拓展
 * @Author wangshuo
 * @Date 2022-08-05 14:31
 * @Version V1.0
 */
public class MongoDefaultRepositoryBean<T extends MongoRepository<S, ID>, S, ID extends Serializable>
        extends MongoRepositoryFactoryBean<T, S, ID> {

    public MongoDefaultRepositoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    @SuppressWarnings({"rawtypes", "hiding"})
    protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
        return new LCRRepositoryFactory(operations);
//        return super.getFactoryInstance(operations);
    }

    private static class LCRRepositoryFactory<S, ID extends Serializable> extends MongoRepositoryFactory {

        private final MongoOperations mongoOperations;

        public LCRRepositoryFactory(MongoOperations mongoOperations) {
            super(mongoOperations);
            this.mongoOperations = mongoOperations;
        }

        @Override
        @SuppressWarnings({"unchecked", "rawtypes", "hiding"})
        protected Object getTargetRepository(RepositoryInformation information) {
            Class<?> repositoryInterface = information.getRepositoryInterface();
            MongoEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
            if (isQueryDslRepository(repositoryInterface)) {
                return new SimpleMongoRepository(entityInformation, mongoOperations);
            } else {
                return new MongoDefaultRepositoryImpl<>(entityInformation, this.mongoOperations);
            }
        }


        private static boolean isQueryDslRepository(Class<?> repositoryInterface) {
            return QUERY_DSL_PRESENT && QueryByExampleExecutor.class.isAssignableFrom(repositoryInterface);
        }

        @Override
        @SuppressWarnings({"hiding"})
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return isQueryDslRepository(metadata.getRepositoryInterface()) ? super.getRepositoryBaseClass(metadata)
                    : MongoDefaultRepositoryImpl.class;
        }
    }
}
