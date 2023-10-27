package com.example.redisdemo.properties;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description @Author wangshuo @Date 2023-10-26 15:27 @Version V1.0
 */
@Data
@ConfigurationProperties(prefix = "demo.redis")
public class RedisProperties {

  private boolean enabled = true;

  private int database = 0;

  private String password;

  /**
   * 集群
   */
  private List<String> clusterNodes;

  private int connectionTimeout = 2000;

  private int soTimeout = 2000;

  private String ip = "127.0.0.1";

  private Integer port = 6379;

  /**
   * cache 默认缓存时间 (s)
   */
  private Integer defaultTime = 7200;

  /**
   * 自定义缓存列表
   */
  private List<CacheInfo> cacheInfoList;

  /**
   * spring cache配置
   */
  @Data
  public static class CacheInfo {

    /**
     * 缓存key
     */
    private String key;

    /**
     * 缓存时间
     */
    private Integer time;
  }
}
