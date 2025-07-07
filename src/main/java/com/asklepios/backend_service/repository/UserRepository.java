package com.asklepios.backend_service.repository;

import com.asklepios.backend_service.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    


    // Find users by email
    List<User> findByEmail(String email);
    
    // Custom query to get all users with basic info
    @Query("SELECT u FROM User u ")
    List<User> findAllActiveUsers();
} 