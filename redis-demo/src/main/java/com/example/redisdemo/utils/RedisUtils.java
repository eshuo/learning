package com.example.redisdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-02-17 14:43
 * @Version V1.0
 */
@Component
public class RedisUtils {


    private final static Logger log = LoggerFactory.getLogger(RedisUtils.class);


    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(@Qualifier("redisTemplateString")RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    // ##########################【操作String类型】#####################################################

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     *
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 设置值并设置过期时间（单位秒）
     *
     * @param key
     * @param value
     * @param time  过期时间
     *
     * @return
     */
    public boolean set(String key, Object value, long time) {
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
     *
     * @return
     */
    public Object getAndSet(String key, Object value) {
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
     *
     * @return
     */
    public boolean setIfAbsent(String key, String value) {
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
     *
     * @return
     */
    public boolean setIfAbsent(String key, String value, long timeout) {
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
     *
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
     *
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
     *
     * @return
     */
    public boolean append(String key, String value) {
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
     *
     * @return
     */
    public <T> T get(String key) {
        return key == null ? null : (T) redisTemplate.opsForValue().get(key);

    }

    /**
     * 批量获取值
     *
     * @param keys
     *
     * @return
     */
    public List<Object> multiGet(Collection<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }


    /**
     * 删除缓存，支持批量删除
     *
     * @param key
     *
     * @return
     */
    public Long del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
                return 1L;
            } else {
                return redisTemplate.delete(Arrays.asList(key));
            }
        }
        return 0L;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     *
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
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
     *
     * @return 时间(秒) 返回-1, 代表为永久有效
     */
    public long getKeyExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     *
     * @return
     */
    public boolean expireKey(String key, long time) {
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
     *
     * @return
     */
    public Long increment(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 通过increment(K key, double increment)方法以增量方式存储double值（正值则自增，负值则自减）
     *
     * @param key
     * @param increment
     *
     * @return
     */
    public Double increment(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 修改redis中key的名称
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 如果旧值key存在时，将旧值改为新值
     *
     * @param oldKey
     * @param newKey
     *
     * @return
     */
    public Boolean renameOldKeyIfAbsent(String oldKey, String newKey) {
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
     *
     * @return
     */
    public Object hashGetOne(String mapName, Object hashKey) {
        return redisTemplate.opsForHash().get(mapName, hashKey);
    }

    /**
     * 获取mapName中的所有的键值对
     *
     * @param mapName Map名字
     *
     * @return
     */
    public Map<Object, Object> hashGetAll(String mapName) {
        return redisTemplate.opsForHash().entries(mapName);
    }


    /**
     * 删除一个或者多个hash表字段
     *
     * @param key
     * @param fields
     *
     * @return
     */
    public Long hashDelete(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 查看hash表中指定字段是否存在
     *
     * @param key
     * @param field
     *
     * @return
     */
    public boolean hashExists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 给哈希表key中的指定字段的整数值加上增量increment
     *
     * @param key
     * @param field
     * @param increment
     *
     * @return
     */
    public Long hashIncrementByLong(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 给哈希表key中的指定字段的double加上增量increment
     *
     * @param key
     * @param field
     * @param delta
     *
     * @return
     */
    public Double hashIncrementByDouble(String key, Object field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 获取hash表中存在的所有的key
     *
     * @param mapName map名字
     *
     * @return
     */
    public Set<Object> hashKeys(String mapName) {
        return redisTemplate.opsForHash().keys(mapName);
    }

    /**
     * 获取hash表中存在的所有的Value
     *
     * @param mapName map名字
     *
     * @return
     */
    public List<Object> hashValues(String mapName) {
        return redisTemplate.opsForHash().values(mapName);
    }

    /**
     * 获取hash表的大小
     *
     * @param mapName
     *
     * @return
     */
    public Long hashSize(String mapName) {
        return redisTemplate.opsForHash().size(mapName);
    }

    // ##########################【操作List类型】#####################################################

    /**
     * 设置值到List中的头部
     *
     * @param key
     * @param value
     *
     * @return
     */
    public Long listAddInHead(String key, Object value) {
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
     *
     * @return
     */
    public Long listAddAllInHead(String key, Collection<Object> values) {
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
     *
     * @return
     */
    public Long listAddIfPresent(String key, Object value) {
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
     *
     * @return
     */
    public Long listAddInEnd(String key, Object value) {
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
     *
     * @return
     */
    public Long listAddAllInEnd(String key, Collection<Object> values) {
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
     *
     * @return
     */
    public Boolean listAddByIndex(String key, long index, Object value) {
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
     *
     * @return
     */
    public Object listGetByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 根据索引范围获取list中的值
     *
     * @param key   list名字
     * @param start
     * @param end
     *
     * @return
     */
    public List<Object> listGetByRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除并获取列表中第一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
     *
     * @param key list名字
     *
     * @return
     */
    public Object listLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并获取列表中最后一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)
     *
     * @param key list名字
     *
     * @return
     */
    public Object listRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 删除集合中值等于value的元素(
     * index=0, 删除所有值等于value的元素;
     * index>0, 从头部开始删除第一个值等于value的元素;
     * index<0, 从尾部开始删除第一个值等于value的元素)
     *
     * @param key
     * @param index
     * @param value
     *
     * @return
     */
    public Long listRemove(String key, long index, Object value) {
        Long removeNum = redisTemplate.opsForList().remove(key, index, value);
        return removeNum;
    }

// ##########################【操作Set类型】#####################################################

    /**
     * 设置值到Set集合(支持批量)
     *
     * @param key
     * @param value
     *
     * @return
     */
    public Long setAdd(String key, Object... value) {
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
     *
     * @return 移除的数量
     */
    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断Set中是否存在value
     *
     * @param key
     * @param value
     *
     * @return
     */
    public boolean setIsExist(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


    public String type(String key) {
        final DataType type = redisTemplate.type(key);
        if (null == type) {
            return DataType.NONE.code();
        }
        return type.code();
    }


}
