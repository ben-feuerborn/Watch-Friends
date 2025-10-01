package com.watch_friends.media_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watch_friends.media_service.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTmdbId(Long tmdbId);
}
