package com.example.url_shortener.DTO;

public class UrlRequest {
    private final String url;

    public String getUrl() {
        return url;
    }

    public UrlRequest(String url) {
        this.url = url;
    }
}
