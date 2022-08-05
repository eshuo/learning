package com.example.mongo.config;

import com.example.mongo.utils.MongoUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-05 10:37
 * @Version V1.0
 */
@Configuration
@Import(MongoDBConfig.class)
public class MongoDBAutoConfiguration {


    @Bean
    public MongoUtils mongoUtils(MongoTemplate mongoTemplate) {
        return new MongoUtils(mongoTemplate);
    }

}
