package com.asklepios.backend_service.repository;

import com.asklepios.backend_service.model.jpa.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    
    // Find by name
    List<Facility> findByName(String name);
    

    
    // Find by type
    List<Facility> findByType(String type);
    
    // Find by email address
    Optional<Facility> findByEmailAddress(String emailAddress);
    
    // Find by phone
    List<Facility> findByPhone1(String phone1);
    
    // Find by address ID
    List<Facility> findByAddressId(String addressId);
    
    // Find facilities registered after a specific date
    List<Facility> findByRegistrationDateAfter(LocalDate date);
    
    // Check if facility exists by name
    boolean existsByName(String name);
    
    // Check if facility exists by email
    boolean existsByEmailAddress(String emailAddress);

    //  Delete operations:
    void deleteByName(String name);

} 