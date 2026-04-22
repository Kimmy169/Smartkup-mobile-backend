package org.smartkup.smartkup.service;

import org.smartkup.smartkup.dto.UserResponseDTO;
import org.smartkup.smartkup.entity.User;
import org.smartkup.smartkup.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Register a new user
    public UserResponseDTO registerUser(User user) {
        // In a real app, you would hash the password here before saving!
        // For now, we will just save the raw entity.
        User savedUser = repository.save(user);
        return new UserResponseDTO(savedUser);
    }

    // Fetch a user profile safely (without password)
    public UserResponseDTO getUserProfile(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user);
    }
}