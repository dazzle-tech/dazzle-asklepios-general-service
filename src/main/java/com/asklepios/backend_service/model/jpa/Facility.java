package com.asklepios.backend_service.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "facility")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 255)
    private String name;
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String type;

    private LocalDate registrationDate;

    @Column(length = 100)
    private String emailAddress;

    @Column(length = 100)
    private String phone1;

    @Column(length = 100)
    private String phone2;

    @Column(length = 100)
    private String fax;

    @Column(length = 100)
    private String addressId;

    @Column(length = 100)
    private String defaultCurrencyLkey;

    @Column(nullable = false)
    private Boolean isValid = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (isValid == null) {
            isValid = true;
        }
    }
}
