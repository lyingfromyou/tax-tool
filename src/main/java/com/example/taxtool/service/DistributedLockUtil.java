package com.example.taxtool.service;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DistributedLockUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public Boolean tryGetDistributedLock(String key, String val, long timeout, TimeUnit unit) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, val, timeout, unit);
        return result;
    }


    public Boolean unLockDistributedLock(String key, String val) {
        String result = redisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(result) && val.equals(result)) {
            return redisTemplate.delete(key);
        }
        return Boolean.FALSE;
    }


}
