package com.wyci.mogodbdemo.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:11
 * @Version V1.0
 */
//TODO https://www.jianshu.com/p/32c21a390e1d
@Document(collection = "user_dynamic") //通过collection参数指定当前实体类对应的文档
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDynamic {

    @Id//主键
    private String id;

    @Indexed //索引
    @Field("name")
    private String name;

    @Field("msg") //别名
    private String message;


    //    动态存储
//    @Transient
    @JsonIgnore
    @JsonAnySetter
    private HashMap<String, Object> dataMap;


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

    @JsonAnyGetter
    public HashMap<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
