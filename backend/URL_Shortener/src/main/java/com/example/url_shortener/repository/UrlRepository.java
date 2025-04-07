package com.example.url_shortener.repository;

import com.example.url_shortener.DTO.ShortenedUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<ShortenedUrl, String> {
    ShortenedUrl findByShortUrl(String shortUrl);
}
