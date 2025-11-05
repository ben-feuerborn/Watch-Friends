package com.watchfriends.friendservice.service;

import com.watchfriends.friendservice.model.Friendship;
import com.watchfriends.friendservice.repository.FriendshipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;

    public FriendshipService(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    public List<Friendship> getFriends(Long userId) {
        return friendshipRepository.findByUserId(userId);
    }

    public Friendship addFriend(Long userId, Long friendId) {
        Friendship friendship = Friendship.builder()
                .userId(userId)
                .friendId(friendId)
                .since(LocalDate.now())
                .build();
        return friendshipRepository.save(friendship);
    }

    public void removeFriend(Long id) {
        friendshipRepository.deleteById(id);
    }
}
