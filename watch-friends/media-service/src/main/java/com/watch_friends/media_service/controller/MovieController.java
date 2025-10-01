package com.watch_friends.media_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watch_friends.media_service.model.Movie;
import com.watch_friends.media_service.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }
}
