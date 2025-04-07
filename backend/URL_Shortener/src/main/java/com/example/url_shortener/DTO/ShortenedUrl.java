package com.example.url_shortener.DTO;


import java.io.Serializable;

public class ShortenedUrl implements Serializable {
    private String shortUrl, originalUrl;

    public ShortenedUrl() {
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public ShortenedUrl(String shortUrl, String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }
}
