package com.example.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description 分布式锁mongodb
 * @Author wangshuo
 * @Date 2025-11-07 14:03
 * @Version V1.0
 */
@Document("distributed_lock")
public class DistributedLock {


    @Id
    private String key;

    private Long expire;


    public DistributedLock(String key, Long expire) {
        this.key = key;
        this.expire = expire;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
