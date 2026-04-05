package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User createUser(User user) {
        // Check if username already exists
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username '" + user.getUsername() + "' already exists!");
        }

        // Encode password
        user.setPassword(encoder.encode(user.getPassword()));

        // Set default role and active status
        user.setRole(Role.VIEWER);
        user.setActive(true);

        return repo.save(user);
    }
    
    public Optional<User> getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}