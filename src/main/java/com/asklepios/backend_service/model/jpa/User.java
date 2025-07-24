package com.asklepios.backend_service.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import jakarta.persistence.Column;import lombok.Data;

@Entity
@Table(name = "app_user")
@Data
public class User {
    
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;
    
    @Column(name = "email")
    private String email;

    @Column(name = "activated")
    private boolean activated;


} 