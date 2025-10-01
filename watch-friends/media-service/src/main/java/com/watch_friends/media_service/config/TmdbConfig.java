package com.watch_friends.media_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TmdbConfig {

    @Value("${tmdb.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
