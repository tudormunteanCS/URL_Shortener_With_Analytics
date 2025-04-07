package com.example.url_shortener.Redis;

import com.example.url_shortener.DTO.ShortenedUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
class RedisConfig {
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, ShortenedUrl> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ShortenedUrl> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<ShortenedUrl> serializer = new Jackson2JsonRedisSerializer<>(ShortenedUrl.class);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}