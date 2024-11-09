package com.example.mongo.config;

import com.example.mongo.repository.MongoDefaultRepositoryBean;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Collections;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
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


    private final MongoMappingContext mongoMappingContext;

    private final MongoDBProperties mongoDBProperties;

    public MongoDBConfig(MongoDBProperties mongoDBProperties, MongoMappingContext mongoMappingContext) {
        this.mongoDBProperties = mongoDBProperties;
        this.mongoMappingContext = mongoMappingContext;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        mongoMappingContext.setAutoIndexCreation(true);
        mongoMappingContext.afterPropertiesSet();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        // 此处是去除插入数据库的 _class 字段
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Bean
    public MongoTypeMapper defaultMongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClient mongoClient = getMongoClient(mongoDBProperties);
        return new MongoTemplate(mongoClient, mongoDBProperties.getDatabase());


    }

    private static MongoClient getMongoClient(MongoDBProperties mongoDBProperties) {
        MongoClient mongoClient;
        if (null == mongoDBProperties.getUri()) {
            MongoClientSettings settings;
            if (null != mongoDBProperties.getUsername() && null != mongoDBProperties.getPassword()) {
                MongoCredential credential = MongoCredential.createCredential(
                    mongoDBProperties.getUsername(),
                    mongoDBProperties.getDatabase(),
                    mongoDBProperties.getPassword()
                );
                settings = MongoClientSettings.builder()
                    .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress(mongoDBProperties.getHost(), mongoDBProperties.getPort()))))
                    .credential(credential)
                    .build();
            } else {
                settings = MongoClientSettings.builder()
                    .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress(mongoDBProperties.getHost(), mongoDBProperties.getPort()))))
                    .build();
            }
            mongoClient = MongoClients.create(settings);
        } else {
            ConnectionString connectionString = new ConnectionString(mongoDBProperties.getUri());

            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
            mongoClient = MongoClients.create(settings);
            mongoDBProperties.setDatabase(connectionString.getDatabase());
        }
        return mongoClient;
    }


    /**
     * 自定义mongo连接池
     *
     * @return MongoDbFactory对象
     */
    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        MongoClient mongoClient = getMongoClient(mongoDBProperties);
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoDBProperties.getDatabase());
    }

}
