package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.jpa.User;
import com.asklepios.backend_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Get all active users
    public List<User> getAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }
    
    // Get user by key
    public User getUserByKey(String key) {
        return userRepository.findById(key).orElse(null);
    }
    

    
    // Get all users (simple list)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
} 