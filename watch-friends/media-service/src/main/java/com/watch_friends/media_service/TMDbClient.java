package com.watch_friends.media_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TMDbClient {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TMDbClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.themoviedb.org/3").build();
    }

    public String searchMovies(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
