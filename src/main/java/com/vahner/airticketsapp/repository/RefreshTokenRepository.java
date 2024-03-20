package com.vahner.airticketsapp.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {

    public static final String HASH_KEY = "REFRESH-TOKEN";

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String login, String refreshToken) {
        redisTemplate.opsForHash().put(HASH_KEY, login, refreshToken);
    }

    public String findById(String login) {
        return (String) redisTemplate.opsForHash().get(HASH_KEY, login);
    }
}