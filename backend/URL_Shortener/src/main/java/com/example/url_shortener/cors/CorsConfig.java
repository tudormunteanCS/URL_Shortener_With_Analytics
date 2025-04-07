package com.example.url_shortener.cors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from the front-end (localhost:3000) to the backend
        registry.addMapping("/**") // Allow CORS for all paths
                .allowedOrigins("http://localhost:5173") // Front-end URL
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*") // Allowed request headers
                .allowCredentials(true); // Allow credentials (e.g., cookies, authentication headers)
    }
}