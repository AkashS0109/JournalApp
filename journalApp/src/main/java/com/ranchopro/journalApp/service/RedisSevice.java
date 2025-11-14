package com.ranchopro.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

// whenever we will  interact with redis we will  use redisService
@Service
@Slf4j
public class RedisSevice {

    @Autowired
    private RedisTemplate redisTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            if (value == null) {
                log.warn("No value found in Redis for key: {}", key);
                return null;
            }
            return mapper.readValue(value, entityClass);
        } catch (Exception e) {
            log.error("Error reading key {} from Redis", key, e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {
        try {
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
            log.info("Saved key {} to Redis with TTL {}s", key, ttl);
        } catch (Exception e) {
            log.error("Error saving key {} to Redis", key, e);
        }
    }


}
