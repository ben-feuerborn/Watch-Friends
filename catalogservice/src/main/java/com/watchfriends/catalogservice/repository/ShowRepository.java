package com.watchfriends.catalogservice.repository;

import com.watchfriends.catalogservice.model.Show;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {
    
}