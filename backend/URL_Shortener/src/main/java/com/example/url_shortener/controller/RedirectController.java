package com.example.url_shortener.controller;

import com.example.url_shortener.Redis.RedisService;
import com.example.url_shortener.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {
    private final UrlShortenerService urlShortenerService;
    private final RedisService redisService;

    public RedirectController(UrlShortenerService urlShortenerService, RedisService redisService) {
        this.urlShortenerService = urlShortenerService;
        this.redisService = redisService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        System.out.println("trying to redirect to the original url from the shortUrl: " + shortUrl);
        //first try to retrieve from Redis
        String originalUrl = null;
        originalUrl = redisService.getShortenedUrl(shortUrl).getOriginalUrl();
        if (originalUrl != null) {
            System.out.println("Retrieved the URL from Redis");
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
        } else {
            originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
        }
    }

}
