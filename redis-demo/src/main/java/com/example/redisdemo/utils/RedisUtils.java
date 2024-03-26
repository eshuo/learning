package com.example.redisdemo.utils;

import com.example.redisdemo.properties.RedisProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * @Description @Author wangshuo @Date 2023-02-17 14:43 @Version V1.0
 */
public class RedisUtils {


    //前缀
    public final String PREFIX;
    private RedisProperties redisProperties;

    private final static Logger log = LoggerFactory.getLogger(RedisUtils.class);


    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate, RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
        this.redisTemplate = redisTemplate;
        this.PREFIX = redisProperties.getPrefix();
    }

    // ##########################【操作String类型】#####################################################

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        key = getPrefixKey(key);
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    private String getPrefixKey(String key) {
        if (!ObjectUtils.isEmpty(PREFIX) && !ObjectUtils.isEmpty(key) && !key.startsWith(PREFIX)) {
            key = PREFIX.concat(key);
        }
        return key;
    }

    /**
     * 设置值并设置过期时间（单位秒）
     *
     * @param key
     * @param value
     * @param time  过期时间
     * @return
     */

    public boolean set(String key, Object value, long time) {
        key = getPrefixKey(key);
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 设置一个已经存在的key的值，并返回旧值
     *
     * @param key
     * @param value
     * @return
     */

    public Object getAndSet(String key, Object value) {
        key = getPrefixKey(key);
        try {
            Object andSet = redisTemplate.opsForValue().getAndSet(key, value);
            return andSet;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 如果不存在则设置值value，返回true。 否则返回false
     *
     * @param key
     * @param value
     * @return
     */

    public boolean setIfAbsent(String key, String value) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 如果不存在则设置值value，返回true。 否则返回false
     *
     * @param key
     * @param value
     * @return
     */

    public boolean setIfAbsent(String key, String value, long timeout) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 批量设置 k->v 到 redis
     *
     * @param valueMap
     * @return
     */

    public boolean multiSet(HashMap valueMap) {
        try {
            redisTemplate.opsForValue().multiSet(valueMap);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 如果不存在对应的Map，则批量设置 k->v 到 redis
     *
     * @param valueMap
     * @return
     */

    public boolean multiSetIfAbsent(HashMap valueMap) {
        try {
            redisTemplate.opsForValue().multiSetIfAbsent(valueMap);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 在原有的值基础上新增字符串到末尾
     *
     * @param key
     * @param value
     * @return
     */

    public boolean append(String key, String value) {
        key = getPrefixKey(key);
        try {
            redisTemplate.opsForValue().append(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 获取value
     *
     * @param key
     * @return
     */

    public <T> T get(String key) {
        key = getPrefixKey(key);
        return key == null ? null : (T) redisTemplate.opsForValue().get(key);

    }

    /**
     * 批量获取值
     *
     * @param keys
     * @return
     */

    public List<Object> multiGet(Collection<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        keys = keys.stream().map(k -> {
            if (!ObjectUtils.isEmpty(PREFIX) && !k.startsWith(PREFIX)) {
                k = PREFIX.concat(k);
            }
            return k;
        }).collect(Collectors.toList());

        return redisTemplate.opsForValue().multiGet(keys);
    }


    /**
     * 删除缓存，支持批量删除
     *
     * @param key
     * @return
     */

    public Long del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                String string = key[0];
                if (!ObjectUtils.isEmpty(PREFIX) && !string.startsWith(PREFIX)) {
                    string = PREFIX.concat(string);
                }
                redisTemplate.delete(string);
                return 1L;
            } else {
                final List<String> keys = Arrays.stream(key).map(k -> {
                    if (!ObjectUtils.isEmpty(PREFIX) && !k.startsWith(PREFIX)) {
                        k = PREFIX.concat(k);
                    }
                    return k;
                }).collect(Collectors.toList());
                return redisTemplate.delete(keys);
            }
        }
        return 0L;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */

    public boolean hasKey(String key) {
        key = getPrefixKey(key);
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 根据key 获取key的过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1, 代表为永久有效
     */

    public long getKeyExpire(String key) {
        key = getPrefixKey(key);
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */

    public boolean expireKey(String key, long time) {
        key = getPrefixKey(key);
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 通过increment(K key, long increment)方法以增量方式存储long值（正值则自增，负值则自减）
     *
     * @param key
     * @param increment
     * @return
     */

    public Long increment(String key, long increment) {
        key = getPrefixKey(key);
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 通过increment(K key, double increment)方法以增量方式存储double值（正值则自增，负值则自减）
     *
     * @param key
     * @param increment
     * @return
     */

    public Double increment(String key, double increment) {
        key = getPrefixKey(key);
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 修改redis中key的名称
     *
     * @param oldKey
     * @param newKey
     */

    public void renameKey(String oldKey, String newKey) {
        oldKey = getPrefixKey(oldKey);
        newKey = getPrefixKey(newKey);
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 如果旧值key存在时，将旧值改为新值
     *
     * @param oldKey
     * @param newKey
     * @return
     */

    public Boolean renameOldKeyIfAbsent(String oldKey, String newKey) {
        oldKey = getPrefixKey(oldKey);
        newKey = getPrefixKey(newKey);
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    // ##########################【操作Hash类型】#####################################################

    /**
     * 批量添加Map中的键值对
     *
     * @param mapName map名字
     * @param maps
     */

    public boolean hashPutAll(String mapName, Map<String, String> maps) {
        mapName = getPrefixKey(mapName);
        try {
            redisTemplate.opsForHash().putAll(mapName, maps);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 添加一个键值对
     *
     * @param mapName
     * @param key
     * @param value
     */

    public boolean hashPutOne(String mapName, String key, String value) {
        mapName = getPrefixKey(mapName);
        try {
            redisTemplate.opsForHash().put(mapName, key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 添加一个键值对,仅当hashKey不存在时才设置
     *
     * @param mapName
     * @param hashKey
     * @param value
     */

    public boolean hashPutOneIfAbsent(String mapName, String hashKey, String value) {
        mapName = getPrefixKey(mapName);
        try {
            redisTemplate.opsForHash().putIfAbsent(mapName, hashKey, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 获取mapName中的所有的键值对
     *
     * @param mapName Map名字
     * @return
     */

    public Object hashGetOne(String mapName, Object hashKey) {
        mapName = getPrefixKey(mapName);
        return redisTemplate.opsForHash().get(mapName, hashKey);
    }

    /**
     * 获取mapName中的所有的键值对
     *
     * @param mapName Map名字
     * @return
     */

    public Map<Object, Object> hashGetAll(String mapName) {
        mapName = getPrefixKey(mapName);
        return redisTemplate.opsForHash().entries(mapName);
    }


    /**
     * 删除一个或者多个hash表字段
     *
     * @param key
     * @param fields
     * @return
     */

    public Long hashDelete(String key, Object... fields) {
        key = getPrefixKey(key);
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 查看hash表中指定字段是否存在
     *
     * @param key
     * @param field
     * @return
     */

    public boolean hashExists(String key, String field) {
        key = getPrefixKey(key);
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 给哈希表key中的指定字段的整数值加上增量increment
     *
     * @param key
     * @param field
     * @param increment
     * @return
     */

    public Long hashIncrementByLong(String key, Object field, long increment) {
        key = getPrefixKey(key);
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 给哈希表key中的指定字段的double加上增量increment
     *
     * @param key
     * @param field
     * @param delta
     * @return
     */

    public Double hashIncrementByDouble(String key, Object field, double delta) {
        key = getPrefixKey(key);
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 获取hash表中存在的所有的key
     *
     * @param mapName map名字
     * @return
     */

    public Set<Object> hashKeys(String mapName) {
        mapName = getPrefixKey(mapName);
        return redisTemplate.opsForHash().keys(mapName);
    }

    /**
     * 获取hash表中存在的所有的Value
     *
     * @param mapName map名字
     * @return
     */

    public List<Object> hashValues(String mapName) {
        mapName = getPrefixKey(mapName);
        return redisTemplate.opsForHash().values(mapName);
    }

    /**
     * 获取hash表的大小
     *
     * @param mapName
     * @return
     */

    public Long hashSize(String mapName) {
        mapName = getPrefixKey(mapName);
        return redisTemplate.opsForHash().size(mapName);
    }


    public List hashMultiGet(String key, Collection list) {
        key = getPrefixKey(key);
        return redisTemplate.opsForHash().multiGet(key, list);
    }

    // ##########################【操作List类型】#####################################################

    /**
     * 设置值到List中的头部
     *
     * @param key
     * @param value
     * @return
     */

    public Long listAddInHead(String key, Object value) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * 批量设置值到List中的头部
     *
     * @param key    List名字
     * @param values
     * @return
     */

    public Long listAddAllInHead(String key, Collection<Object> values) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForList().leftPushAll(key, values);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * 如果存在List->key, 则设置值到List中的头部
     *
     * @param key   List名字
     * @param value
     * @return
     */

    public Long listAddIfPresent(String key, Object value) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForList().leftPushIfPresent(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * 设置值到List中的尾部
     *
     * @param key   List名字
     * @param value
     * @return
     */

    public Long listAddInEnd(String key, Object value) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * 批量设置值到List中的尾部
     *
     * @param key    List名字
     * @param values
     * @return
     */

    public Long listAddAllInEnd(String key, Collection<Object> values) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForList().rightPushAll(key, values);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return 0L;
    }

    /**
     * 通过索引去设置List->key中的值
     *
     * @param key
     * @param index
     * @param value
     * @return
     */

    public Boolean listAddByIndex(String key, long index, Object value) {
        key = getPrefixKey(key);
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 根据索引获取list中的值
     *
     * @param key   list名字
     * @param index
     * @return
     */

    public Object listGetByIndex(String key, long index) {
        key = getPrefixKey(key);
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 根据索引范围获取list中的值
     *
     * @param key   list名字
     * @param start
     * @param end
     * @return
     */

    public List<Object> listGetByRange(String key, long start, long end) {
        key = getPrefixKey(key);
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除并获取列表中第一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
     *
     * @param key list名字
     * @return
     */

    public Object listLeftPop(String key) {
        key = getPrefixKey(key);
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并获取列表中最后一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
     *
     * @param key list名字
     * @return
     */

    public Object listRightPop(String key) {
        key = getPrefixKey(key);
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 删除集合中值等于value的元素( index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素; index<0, 从尾部开始删除第一个值等于value的元素)
     *
     * @param key
     * @param index
     * @param value
     * @return
     */

    public Long listRemove(String key, long index, Object value) {
        key = getPrefixKey(key);
        Long removeNum = redisTemplate.opsForList().remove(key, index, value);
        return removeNum;
    }

// ##########################【操作Set类型】#####################################################

    /**
     * 设置值到Set集合(支持批量)
     *
     * @param key
     * @param value
     * @return
     */

    public Long setAdd(String key, Object... value) {
        key = getPrefixKey(key);
        try {
            return redisTemplate.opsForSet().add(key, value);
//            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
//            return false;
        }
        return 0L;
    }

    /**
     * 移除Set集合中的值，支持批量
     *
     * @param key
     * @param values
     * @return 移除的数量
     */

    public long setRemove(String key, Object... values) {
        key = getPrefixKey(key);
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断Set中是否存在value
     *
     * @param key
     * @param value
     * @return
     */

    public boolean setIsExist(String key, Object value) {
        key = getPrefixKey(key);
        return redisTemplate.opsForSet().isMember(key, value);
    }


    public Set<Object> setReverseRange(String key, long start, long end) {
        key = getPrefixKey(key);
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }


    public Set setReverseRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        key = getPrefixKey(key);
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }


    public Long zCard(String key) {
        key = getPrefixKey(key);
        return redisTemplate.opsForZSet().zCard(key);
    }


    public Double score(String key, String hKey) {
        key = getPrefixKey(key);
        return redisTemplate.opsForZSet().score(key, hKey);
    }


    public String type(String key) {
        key = getPrefixKey(key);
        final DataType type = redisTemplate.type(key);
        if (null == type) {
            return DataType.NONE.code();
        }
        return type.code();
    }



    /*-------------------------------兼容RedisTemplate方法----------------------------------*/


    public RedisProperties getProperties() {
        return this.redisProperties;
    }


    public long ttl(String key) {
        key = getPrefixKey(key);
        return this.getKeyExpire(key);
    }


    public String setStringValue(String key, String value, int seconds) {
        key = getPrefixKey(key);
        this.set(key, value, seconds);
        return "OK";
    }


    public String setStringValue(String key, String value) {
        key = getPrefixKey(key);
        this.set(key, value);
        return "OK";
    }


    public String setObjectValue(String key, Object obj, int seconds) {
        key = getPrefixKey(key);
        this.set(key, obj, seconds);
        return "OK";
    }


    public String setObjectValue(String key, Object obj) {
        key = getPrefixKey(key);
        boolean set = this.set(key, obj);
        if (set) {
            return "OK";
        }
        return null;
    }


    public Long setnxValue(String key, String value) {
        key = getPrefixKey(key);
        return this.setIfAbsent(key, value) ? 1L : 0L;
    }


    public Long setnxValue(String key, String value, long expireTime) {
        key = getPrefixKey(key);
        return this.setIfAbsent(key, value, expireTime) ? 1L : 0L;
    }


    public long publish(String channel, String message) {
        channel = getPrefixKey(channel);
        redisTemplate.convertAndSend(channel, message);
        return 0L;
    }


    public String getStringValue(String key) {
        key = getPrefixKey(key);
        return this.get(key);
    }


    public Object getObjectValue(String key) {
        key = getPrefixKey(key);
        return this.get(key);
    }


    public <T> T getObjectValue(String key, Class<T> cls) {
        key = getPrefixKey(key);
        return this.get(key);
//        if(result == null || result.isEmpty())
//        {
//            return null;
//        }
//        return JSONObject.parseObject(result, cls);
    }


    public Long del(String key) {
        key = getPrefixKey(key);
        redisTemplate.delete(key);
        return 1L;
    }


    public Long incrBy(String key, int i) {
        key = getPrefixKey(key);
        return this.increment(key, i);
    }


    public boolean exists(String key) {
        key = getPrefixKey(key);
        return this.hasKey(key);
    }


    public long expire(String key, int seconds) {
        key = getPrefixKey(key);
        this.expireKey(key, seconds);
        return 1L;
    }


    public long expireAt(String key, long unixTime) {
        key = getPrefixKey(key);
        this.expireKey(key, unixTime);
        return 1L;
    }


    public long sadd(String key, String... members) {
        key = getPrefixKey(key);
        return this.setAdd(key, members);
    }


    public boolean sismember(String key, String member) {
        key = getPrefixKey(key);
        return this.setIsExist(key, member);
    }


    public boolean isAlive() {
        return null != redisTemplate;
    }


    public List<String> keys(String pattern) {
        pattern = getPrefixKey(pattern);
        final Set<String> keys = redisTemplate.keys(pattern);
        if (null == keys) {
            return new ArrayList<>();
        }
        return new ArrayList<>(keys);
    }


    public Long hSetObject(String key, String field, Object value) {
        return null;
    }


    public Long hSetString(String key, String field, String value) {
        return null;
    }


    public Long hSetObject(String key, String field, Object value, int seconds) {
        return null;
    }


    public Long hSetString(String key, String field, String value, int seconds) {
        return null;
    }


    public <T> T hGetObject(String key, String field, Class<T> clazz) {
        return null;
    }


    public String hGetString(String key, String field) {
        return null;
    }


}
