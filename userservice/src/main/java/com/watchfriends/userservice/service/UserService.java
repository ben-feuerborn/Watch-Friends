package com.watchfriends.userservice.service;

import com.watchfriends.userservice.model.User;
import com.watchfriends.userservice.repository.UserRepository;
import com.watchfriends.userservice.utils.UserContextHolder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "buildFallbackUserList")
    public Optional<User> getUserById(Long userId) throws TimeoutException {
        logger.debug("getUserById Correlation id: {}",
                UserContextHolder.getContext().getCorrelationId());
        randomlyRunLong();
        return userRepository.findById(userId);
    }

    @SuppressWarnings("unused")
    private List<User> buildFallbackUserList(Long userId, Throwable t) {
        List<User> fallbackList = new ArrayList<>();
        User user = new User();
        user.setName("null");
        user.setEmail("null");
        user.setBio("null");
        user.setPreferences("null");
        fallbackList.add(user);
        return fallbackList;
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3)
            sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setBio(updatedUser.getBio());
                    existingUser.setPreferences(updatedUser.getPreferences());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
