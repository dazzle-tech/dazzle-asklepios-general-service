package com.asklepios.backend_service.model.jpa;

import jakarta.persistence.*;
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
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "type", nullable = false, length = 255)
    private String type;
    
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    
    @Column(name = "email_address", length = 100)
    private String emailAddress;
    
    @Column(name = "phone1", length = 100)
    private String phone1;
    
    @Column(name = "phone2", length = 100)
    private String phone2;
    
    @Column(name = "fax", length = 100)
    private String fax;
    
    @Column(name = "address_id", length = 100)
    private String addressId;
    
    @Column(name = "default_currency_lkey", length = 100)
    private String defaultCurrencyLkey;
    
    @Column(name = "is_valid")
    private Boolean isValid = true;
    
    @Column(name = "created_at")
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