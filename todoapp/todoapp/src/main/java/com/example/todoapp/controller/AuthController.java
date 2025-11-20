package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        Optional<User> existing = userRepo.findByUsername(user.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }
        userRepo.save(user);
        return ResponseEntity.ok("Registered successfully!");
    }

   @PostMapping("/login")
public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
    Optional<User> found = userRepo.findByUsername(user.getUsername());
    Map<String, String> response = new HashMap<>();

    if (found.isPresent()) {
        System.out.println("User found: " + found.get().getUsername());
        System.out.println("Stored password: " + found.get().getPassword());
        System.out.println("Entered password: " + user.getPassword());
    } else {
        System.out.println("User not found in DB!");
    }

    if (found.isPresent() && found.get().getPassword().equals(user.getPassword())) {
        response.put("message", "Login successful!");
        return ResponseEntity.ok(response);
    } else {
        response.put("message", "Invalid username or password!");
        return ResponseEntity.status(401).body(response);
    }
}

}