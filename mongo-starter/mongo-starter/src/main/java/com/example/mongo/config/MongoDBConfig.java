package com.example.mongo.config;

import com.example.mongo.repository.MongoDefaultRepositoryBean;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-04 17:17
 * @Version V1.0
 */
@Configuration
@EnableConfigurationProperties(MongoDBProperties.class)
//TODO 包名定义！！！
@EnableMongoRepositories(basePackages = {"com.**.mongo.dao"}, repositoryFactoryBeanClass = MongoDefaultRepositoryBean.class)
public class MongoDBConfig {

    private final MongoDatabaseFactory mongoDatabaseFactory;

    private final MongoMappingContext mongoMappingContext;

    public MongoDBConfig(MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext mongoMappingContext) {
        this.mongoDatabaseFactory = mongoDatabaseFactory;
        this.mongoMappingContext = mongoMappingContext;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        mongoMappingContext.setAutoIndexCreation(true);
        mongoMappingContext.afterPropertiesSet();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        // 此处是去除插入数据库的 _class 字段
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnMissingBean(MongoClientSettings.class)
    static class MyMongoClientConfiguration {
        @Bean
        MongoClientSettings mongoClientSettings() {
            return MongoClientSettings.builder().build();
        }

        @Bean
        MongoPropertiesClientSettingsBuilderCustomizer mongoPropertiesCustomizer(MongoDBProperties mongoDBProperties,
                                                                                 Environment environment) {
            MongoProperties mongoProperties = new MongoProperties();
            BeanUtils.copyProperties(mongoDBProperties, mongoProperties);
            return new MongoPropertiesClientSettingsBuilderCustomizer(mongoProperties, environment);
        }
    }

}
