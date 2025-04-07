package com.example.url_shortener.controller;

import com.example.url_shortener.DTO.Analytics;
import com.example.url_shortener.service.AnalyticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @RequestMapping(value = "/anayltics",method = RequestMethod.POST)
    public Analytics createAnalytics(@RequestBody Analytics analytics){
        System.out.println("Storing analytics: " + analytics);
        analyticsService.storeAnaylitcs(analytics);
        return analytics;
    }

}
