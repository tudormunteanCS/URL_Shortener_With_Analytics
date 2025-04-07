package com.example.url_shortener.service;

import com.example.url_shortener.DTO.Analytics;
import com.example.url_shortener.repository.AnalyiticsRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    private final AnalyiticsRepository analyiticsRepository;

    public AnalyticsService(AnalyiticsRepository analyiticsRepository) {
        this.analyiticsRepository = analyiticsRepository;
    }


    public void storeAnaylitcs(Analytics analytics) {
        analyiticsRepository.save(analytics);
    }
}
