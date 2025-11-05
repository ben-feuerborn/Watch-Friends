package com.watchfriends.userservice.repository;

import com.watchfriends.userservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
