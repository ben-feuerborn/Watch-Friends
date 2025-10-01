package com.watch_friends.media_service.service;

import org.springframework.stereotype.Service;

import com.watch_friends.media_service.TMDbClient;
import com.watch_friends.media_service.model.Movie;
import com.watch_friends.media_service.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final TMDbClient tmdbClient;

    public MovieService(MovieRepository movieRepository, TMDbClient tmdbClient) {
        this.movieRepository = movieRepository;
        this.tmdbClient = tmdbClient;
    }

    public String searchMovies(String query) {
        // Returns raw JSON from TMDb
        return tmdbClient.searchMovies(query);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
