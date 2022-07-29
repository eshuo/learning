package com.wyci.mogodbdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * @Description 用户快照表
 * @Author wangshuo
 * @Date 2022-07-27 14:21
 * @Version V1.0
 */

@Document(collection = "user_snapshot")
public class UserSnapshot {
    @Id//主键
    private String id;

    @Indexed
    private String userName;


    private HashMap<String, Object> resourceMap;




//    /**
//     * 键
//     */
//    private String resourceKey;
//
//    /**
//     * 值
//     */
//    private String resourceValue;
//
//    /**
//     * 类型
//     */
//    private String resourceType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<String, Object> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(HashMap<String, Object> resourceMap) {
        this.resourceMap = resourceMap;
    }

}
