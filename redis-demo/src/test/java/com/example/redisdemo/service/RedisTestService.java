package com.example.redisdemo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Description demo
 * @Author wangshuo
 * @Date 2023-10-26 17:25
 * @Version V1.0
 */
@Service
public class RedisTestService {

  //基于Spring cache注解的增删改查demo
//  @Cacheable来定义缓存
  @Cacheable("test")
  public String test() {
    return "key == test";
  }


  @Cacheable("cacheable")
  public String cacheable() {
    return "key == cacheable";
  }

  @Cacheable("demo")
  public String demo() {
    return "key == demo";
  }


  @Cacheable(value = "cacheable", condition = "#key.length()>1", key = "#key", unless = "#key == null && #result ==null")
  public String cacheableKey(String key) {
    return "key == cacheableKey" + key;
  }

  //  @CachePut
//方法永远会被执行，并把结果放入缓存
  @CachePut(value = "cacheable", condition = "#key.length()>1", key = "#key", unless = "#key == null && #result ==null")
  public String updateCacheableKey(String key) {
    return "key == updateCacheableKey" + key;
  }

  //  @CacheEvict
//将缓存移除
  @CacheEvict(value = "cacheable", allEntries = true)
  public void deleteCacheable() {
  }

  @CacheEvict(value = "cacheable", key = "#key", allEntries = true)
  public void deleteCacheableKey(String key) {
  }

//  @Caching
//当一个类型有多个注解的时候使用

  @Caching(evict = {
      @CacheEvict(value = "cacheable", key = "#key", allEntries = true),
      @CacheEvict(value = "test", allEntries = true)
  })
  public void caching(String key) {
  }


}
