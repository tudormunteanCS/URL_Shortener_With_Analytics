package com.example.url_shortener.service;

import com.example.url_shortener.DTO.ShortenedUrl;
import com.example.url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlShortenerService {
    private final UrlRepository urlRepository;
    private final String baseUrl = "http://localhost:9090";

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    public String getShortenedUrl(String originalUrl) {
        String shortCode = generateShortCode();
        String shortenedClientUrl = baseUrl + "/" + shortCode;
        ShortenedUrl shortenedUrl = new ShortenedUrl(shortCode, originalUrl);
        urlRepository.save(shortenedUrl);
        return shortenedClientUrl;
    }

    /**
     * generates a short random code
     *
     * @return short code
     */
    private String generateShortCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(12, 2345678));
    }

    /**
     * querying the db to find the originalUrl by the shortUrl
     *
     * @param shortUrl String
     * @return originalUrl
     */
    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).getOriginalUrl();
    }
}
