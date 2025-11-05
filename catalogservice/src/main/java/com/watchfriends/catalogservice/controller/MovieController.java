package com.watchfriends.catalogservice.controller;

import com.watchfriends.catalogservice.model.Movie;
import com.watchfriends.catalogservice.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<Movie> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        return service.findById(id).orElseThrow();
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {
        return service.save(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}