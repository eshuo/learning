package com.example.redisdemo.config;

import com.example.redisdemo.properties.RedisProperties;
import com.example.redisdemo.utils.RedisUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.CollectionUtils;

/**
 * @Description @Author wangshuo @Date 2023-02-17 14:42 @Version V1.0
 */
@ConditionalOnProperty(prefix = "demo.redis", name = "enabled", havingValue = "true")
@Configuration
@EnableConfigurationProperties({RedisProperties.class})
@EnableCaching
public class MyRedisConfig {

    private static final ObjectMapper MAPPER = new ObjectMapper().setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setSerializationInclusion(JsonInclude.Include.NON_NULL);

    //    @Bean(name = "redisTemplateString")
    //    @ConditionalOnClass(RedisOperations.class)
    //    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    // {
    //        // 创建 RedisTemplate 对象
    //        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //        // 设置连接工厂
    //        redisTemplate.setConnectionFactory(connectionFactory);
    //        // 设置 Key 的序列化 - String 序列化 RedisSerializer.string() => StringRedisSerializer.UTF_8
    //        redisTemplate.setKeySerializer(RedisSerializer.string());
    //        redisTemplate.setHashKeySerializer(RedisSerializer.string());
    //        // 设置 Value 的序列化 - JSON 序列化 RedisSerializer.json() => GenericJackson2JsonRedisSerializer
    //        MAPPER.activateDefaultTyping(
    //                MAPPER.getPolymorphicTypeValidator(),
    //                ObjectMapper.DefaultTyping.NON_FINAL,
    //                JsonTypeInfo.As.PROPERTY);
    //        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer =
    //                new GenericJackson2JsonRedisSerializer(MAPPER);
    //        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
    //        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
    //        redisTemplate.afterPropertiesSet();
    //        // 返回
    //        return redisTemplate;
    //    }


    @ConditionalOnMissingBean
    @Bean("redisUtils")
    @Primary
    public RedisUtils createRedisUtils(RedisProperties redisProperties) {
        return new RedisUtils(createRedisTemplate(getLettuceClusterConnectionFactory(redisProperties)));
    }

    private LettuceConnectionFactory getLettuceClusterConnectionFactory(RedisProperties redisProperties) {
        List<String> clusterNodes = redisProperties.getClusterNodes();
        if (CollectionUtils.isEmpty(clusterNodes)) {

            final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisProperties.getIp(), redisProperties.getPort());
            configuration.setDatabase(redisProperties.getDatabase());
            final LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
            factory.afterPropertiesSet();
            return factory;
        } else {
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            if (redisProperties.getPassword() != null && !redisProperties.getPassword().equals("")) {
                redisClusterConfiguration.setPassword(redisProperties.getPassword());
            }
            for (int i = 0; i < clusterNodes.size(); i++) {
                String s = clusterNodes.get(i);
                String[] split = s.split(":");
                if (split == null || split.length != 2) {
                    throw new RuntimeException("redis 节点配置错误: " + s + " 应该为 ip:port 格式");
                }
                Integer port = null;
                try {
                    port = Integer.parseInt(split[1]);
                } catch (Exception e) {
                    throw new RuntimeException("redis 节点： " + s + " 端口转换失败 errorMsg: " + e.getMessage());
                }
                String host = split[0];
                RedisNode redisNode = new RedisNode(host, port);
                redisClusterConfiguration.addClusterNode(redisNode);
            }
            LettuceConnectionFactory factory = new LettuceConnectionFactory(redisClusterConfiguration, getLettucePoolingClientConfiguration(redisProperties));
            factory.afterPropertiesSet();
            return factory;
        }
    }

    private LettucePoolingClientConfiguration getLettucePoolingClientConfiguration(RedisProperties redisProperties) {
        final LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
            .commandTimeout(Duration.ofMillis(redisProperties.getConnectionTimeout()))
            .shutdownTimeout(Duration.ofMillis(redisProperties.getSoTimeout())).build();
        return lettucePoolingClientConfiguration;
    }

    /**
     * 创建redis模板
     *
     * @param connectionFactory 连接工厂
     * @return {@code org.springframework.data.redis.core.RedisTemplate<String, Object>}
     */
    public org.springframework.data.redis.core.RedisTemplate<String, Object> createRedisTemplate(org.springframework.data.redis.connection.RedisConnectionFactory connectionFactory) {
        // 创建 RedisTemplate 对象
        org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate = new org.springframework.data.redis.core.RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 设置 Key 的序列化 - String 序列化 RedisSerializer.string() => StringRedisSerializer.UTF_8
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 设置 Value 的序列化 - JSON 序列化 RedisSerializer.json() => GenericJackson2JsonRedisSerializer
        final GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = getGenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        // 返回
        return redisTemplate;
    }

    private static GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        MAPPER.activateDefaultTyping(MAPPER.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(MAPPER);
        return genericJackson2JsonRedisSerializer;
    }


    /**
     * 缓存管理器
     *
     * @return
     */
    @ConditionalOnBean(name = "redisUtils")
    @ConditionalOnMissingBean
    @Bean
    public CacheManager cacheManager(RedisProperties redisProperties) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存管理器管理的缓存的默认过期时间
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(redisProperties.getDefaultTime()))
            // 设置 key为string序列化
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
            // 设置value为json序列化
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getGenericJackson2JsonRedisSerializer()))
            //变双冒号为单冒号
            .computePrefixWith(name -> name.endsWith(":") ? name : name + ":")
            // 不缓存空值
            .disableCachingNullValues();

        Set<String> cacheNames = new HashSet<>();
        final Map<String, RedisCacheConfiguration> configMap = getStringRedisCacheConfigurationMap(redisProperties, defaultCacheConfig, cacheNames);
        return RedisCacheManager.builder(getLettuceClusterConnectionFactory(redisProperties))
            .cacheDefaults(defaultCacheConfig)
            .initialCacheNames(cacheNames)
            .withInitialCacheConfigurations(configMap)
            .build();
    }


    /**
     * 获取字符串redis缓存配置映射
     *
     * @param redisProperties    redis特性
     * @param defaultCacheConfig 默认缓存配置
     * @param cacheNames         缓存名称
     * @return {@code Map<String, RedisCacheConfiguration>}
     */
    private static Map<String, RedisCacheConfiguration> getStringRedisCacheConfigurationMap(RedisProperties redisProperties, RedisCacheConfiguration defaultCacheConfig, Set<String> cacheNames) {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        final List<RedisProperties.CacheInfo> cacheInfoList = redisProperties.getCacheInfoList();
        if (!CollectionUtils.isEmpty(cacheInfoList)) {
            cacheInfoList.forEach(cacheInfo -> {
                cacheNames.add(cacheInfo.getKey());
                // 对每个缓存空间应用不同的配置
                configMap.put(cacheInfo.getKey(), defaultCacheConfig.entryTtl(Duration.ofSeconds(null == cacheInfo.getTime() ? redisProperties.getDefaultTime() : cacheInfo.getTime())));
            });
        }
        return configMap;
    }


    /**
     * 在使用@Cacheable时，如果不指定key，则使用找个默认的key生成器生成的key
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(".").append(method.getName());
                StringBuilder paramsSb = new StringBuilder();
                for (Object param : params) {
                    // 如果不指定，默认生成包含到键值中
                    if (param != null) {
                        paramsSb.append(param);
                    }
                }
                if (paramsSb.length() > 0) {
                    sb.append("_").append(paramsSb);
                }
                return sb.toString();
            }

        };

    }
}
