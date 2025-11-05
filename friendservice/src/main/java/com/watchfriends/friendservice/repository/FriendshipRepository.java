package com.watchfriends.friendservice.repository;

import com.watchfriends.friendservice.model.Friendship;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
    List<Friendship> findByUserId(Long userId);
}
