package com.example.mongo;

import com.example.mongo.repository.MongoDefaultRepositoryBean;
import com.example.mongo.repository.MongoDefaultRepositoryImpl;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TestApp {
}
