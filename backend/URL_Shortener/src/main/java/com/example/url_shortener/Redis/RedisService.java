package com.example.url_shortener.Redis;

import com.example.url_shortener.DTO.ShortenedUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, ShortenedUrl> redisTemplate;
    private static final String baseUrl = "http://localhost:9090";

    public void saveShortenedUrl(ShortenedUrl shortenedUrl) {
        System.out.println("setting key in Redis: " + shortenedUrl.getShortUrl());
        redisTemplate.opsForValue().set(shortenedUrl.getShortUrl(), shortenedUrl);
    }

    public ShortenedUrl getShortenedUrl(String shortUrl) {
        System.out.println("Retrieving short url from Redis! as key: " + baseUrl + "/" + shortUrl);
        return redisTemplate.opsForValue().get(baseUrl + "/" + shortUrl);
    }
}
