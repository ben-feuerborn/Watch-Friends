package com.watchfriends.catalogservice.service;

import com.watchfriends.catalogservice.model.Movie;
import com.watchfriends.catalogservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        repo.findAll().forEach(movies::add);
        return movies;
    }
    
    public Optional<Movie> findById(Long id) { return repo.findById(id); }
    public Movie save(Movie movie) { return repo.save(movie); }
    public void delete(Long id) { repo.deleteById(id); }
}