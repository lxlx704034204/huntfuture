package com.hante.common.component.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 写入缓存(键值对)
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入键值对设置时效时间(秒)
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除键值对
     */
    public void remove(final String key) {
        boolean flag = exists(key);
        if (flag) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的key
     */
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }


    /**
     * 读取缓存
     */
    public String get(final String key) {
        String result = null;
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * redis 分布式加锁
     *
     * @param lockKey  加锁的key
     * @param lockTime 加锁时间（毫秒）
     * @return 是否成功
     */
    public synchronized boolean lock(String lockKey, long lockTime) {
        boolean flag = (boolean) stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisSerializer serializer = new StringRedisSerializer();
                Boolean success = connection.setNX(serializer.serialize(lockKey), serializer.serialize(String.valueOf(lockTime)));
                connection.close();
                return success;
            }
        });
        if (flag) {
            log.info("开始设置过期时间 lockKey：{} lockTime:{}", lockKey, lockTime);
            //设置超时时间，释放内存
            stringRedisTemplate.expire(lockKey, lockTime, TimeUnit.MILLISECONDS);
        }
        return flag;
    }

    /**
     * redis 分布式解锁
     *
     * @param lockKey  解锁的key
     * @param lockTime 解锁时间（毫秒）
     * @return 是否成功
     */
    public synchronized boolean unlock(String lockKey, long lockTime) {
        try {
            String expireTime = this.get(lockKey);
            if (expireTime != null && (System.currentTimeMillis() - Long.parseLong(expireTime)) > lockTime) {
                this.remove(lockKey);
                log.info("解锁成功 lockKey:{}", lockKey);
                return true;
            } else {
                Thread.sleep(new Random().nextInt(1000));
            }
        } catch (Exception e) {
            log.error("e:{}", e);
            return false;
        }
        return false;
    }
}
