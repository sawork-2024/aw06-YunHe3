package com.micropos.products.db;
import com.micropos.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, Product value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Product getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public List<Product> getValues() {
        Set<String> keys = redisTemplate.keys("*");
        assert keys != null;
        return keys.stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        Set<String> keys = redisTemplate.keys("*");
        return  (keys == null || keys.isEmpty());
    }
}