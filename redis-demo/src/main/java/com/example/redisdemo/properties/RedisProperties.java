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

    private int database = 0;

    private String type = "redisCluster";

    private boolean enabled = true;

    private String password;

    private int connectionTimeout = 2000;

    private int soTimeout = 2000;

    private int maxAttempts = 2;


    /**
     * 池中“空闲”连接的最大数量。使用负值表示空闲连接的数量不受限制。
     */
    private int maxIdle = 8;

    /**
     * 池中要维护的最小空闲连接数的目标。只有当它和驱逐运行之间的时间都为正时，此设置才会生效。
     */
    private int minIdle = 0;
    /**
     * 池在给定时间可以分配的最大连接数。使用负值表示没有限制。
     */
    private int maxActive = 8;


    private List<String> clusterNodes;

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
     * 命名前缀
     */
    private String prefix;

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
