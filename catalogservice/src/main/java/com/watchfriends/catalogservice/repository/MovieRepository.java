package com.watchfriends.catalogservice.repository;

import com.watchfriends.catalogservice.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}