package com.example.url_shortener.controller;

import com.example.url_shortener.DTO.ShortenedUrl;
import com.example.url_shortener.DTO.UrlRequest;
import com.example.url_shortener.Redis.RedisService;
import com.example.url_shortener.service.UrlShortenerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;
    private final RedisService redisService;

    public UrlShortenerController(UrlShortenerService urlShortenerService, RedisService redisService) {
        this.urlShortenerService = urlShortenerService;
        this.redisService = redisService;
    }

    @RequestMapping(value = "/api/shorten", method = RequestMethod.POST)
    public String createShortenedUrl(@RequestBody UrlRequest urlRequest) {
        System.out.println("Shortening the original URL: " + urlRequest.getUrl());
        String shortenedUrl = urlShortenerService.getShortenedUrl(urlRequest.getUrl());
        //call RedisService to persist shortenedUrl for faster retrieval at Redirecting
        redisService.saveShortenedUrl(new ShortenedUrl(shortenedUrl,urlRequest.getUrl()));
        return shortenedUrl;
    }
}
