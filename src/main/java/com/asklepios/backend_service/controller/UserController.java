package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.jpa.User;
import com.asklepios.backend_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jpa/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    

    
    // Get user by key
    @GetMapping("/{key}")
    public ResponseEntity<User> getUserByKey(@PathVariable String key) {
        User user = userService.getUserByKey(key);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    

    
    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
} 