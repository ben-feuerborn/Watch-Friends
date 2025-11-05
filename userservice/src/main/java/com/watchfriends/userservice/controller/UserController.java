package com.watchfriends.userservice.controller;

import com.watchfriends.userservice.model.User;
import com.watchfriends.userservice.service.UserService;
import com.watchfriends.userservice.utils.UserContextHolder;

import jakarta.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws TimeoutException {
        logger.debug("UserServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @RolesAllowed({ "ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
