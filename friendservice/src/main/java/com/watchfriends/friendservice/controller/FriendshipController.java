package com.watchfriends.friendservice.controller;

import com.watchfriends.friendservice.model.Friendship;
import com.watchfriends.friendservice.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(friendshipService.getFriends(userId));
    }

    @PostMapping("/{userId}/{friendId}")
    public ResponseEntity<Friendship> addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        return ResponseEntity.ok(friendshipService.addFriend(userId, friendId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long id) {
        friendshipService.removeFriend(id);
        return ResponseEntity.noContent().build();
    }
}
