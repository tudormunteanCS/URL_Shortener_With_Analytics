package com.example.url_shortener.repository;

import com.example.url_shortener.DTO.Analytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyiticsRepository extends MongoRepository<Analytics,Integer> {
    /**
     * retrieves the analytics by the shortUrl
     * @param shortUrl String
     * @return Analytics(clickCount,lat,lng)
     */
    Analytics findByShortUrl(String shortUrl);
}
