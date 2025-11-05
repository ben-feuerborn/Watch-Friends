package com.watchfriends.catalogservice.controller;

import com.watchfriends.catalogservice.model.Show;
import com.watchfriends.catalogservice.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService service;

    public ShowController(ShowService service) {
        this.service = service;
    }

    @GetMapping
    public List<Show> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Show get(@PathVariable Long id) {
        return service.findById(id).orElseThrow();
    }

    @PostMapping
    public Show create(@RequestBody Show show) {
        return service.save(show);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}