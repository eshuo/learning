package com.example.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:11
 * @Version V1.0
 */
@Document(collection = "userInfo") //通过collection参数指定当前实体类对应的文档
@Data
public class UserInfo implements Serializable {

    @Id//主键
    private String id;

    @Indexed //索引
    @Field("name")
    private String name;

    @Field("msg") //别名
    private String message;


    private Integer age;


    private List<String> tags;

    public UserInfo() {
    }

    public UserInfo(String id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public UserInfo(String id, String name, String message, Integer age) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.age = age;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", age=" + age +
                '}';
    }
}
