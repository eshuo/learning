package com.example.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-08 11:34
 * @Version V1.0
 */
@Document("mongo_demo")
public class MongoDemo {

    @Id
    private String id;


    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MongoDemo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
