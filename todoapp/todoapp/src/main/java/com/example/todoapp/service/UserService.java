package com.example.todoapp.service;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public String register(User user) {
        Optional<User> existing = userRepo.findByUsername(user.getUsername());
        if (existing.isPresent()) {
            return "Username already exists!";
        }
        userRepo.save(user);
        return "Registered successfully!";
    }

    public String login(User user) {
        Optional<User> found = userRepo.findByUsername(user.getUsername());
        if (found.isPresent() && found.get().getPassword().equals(user.getPassword())) {
            return "Login successful";
        }
        return "Invalid username or password!";
    }
}
