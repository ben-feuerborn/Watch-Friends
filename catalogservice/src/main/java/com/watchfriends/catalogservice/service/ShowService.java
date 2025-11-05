package com.watchfriends.catalogservice.service;

import com.watchfriends.catalogservice.model.Show;
import com.watchfriends.catalogservice.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowRepository repo;

    public ShowService(ShowRepository repo) {
        this.repo = repo;
    }

    public List<Show> findAll() {
        List<Show> shows = new ArrayList<>();
        repo.findAll().forEach(shows::add);
        return shows;
    }

    public Optional<Show> findById(Long id) { return repo.findById(id); }
    public Show save(Show show) { return repo.save(show); }
    public void delete(Long id) { repo.deleteById(id); }
}