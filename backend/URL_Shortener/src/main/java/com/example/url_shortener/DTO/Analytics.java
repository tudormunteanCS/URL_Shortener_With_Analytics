package com.example.url_shortener.DTO;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "analytics")
public class Analytics implements Serializable {
    @Indexed(unique = true)
    private String shortUrl;
    private double lat, lng;
    private int clickCount;

    public Analytics() {
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "shortUrl='" + shortUrl + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", clickCount=" + clickCount +
                '}';
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public int getClickCount() {
        return clickCount;
    }

    public Analytics(String shortUrl, long lat, long lng, int clickCount) {
        this.shortUrl = shortUrl;
        this.lat = lat;
        this.lng = lng;
        this.clickCount = clickCount;
    }
}
