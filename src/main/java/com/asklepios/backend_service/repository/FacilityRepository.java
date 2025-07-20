package com.asklepios.backend_service.repository;

import com.asklepios.backend_service.model.jpa.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    
    List<Facility> findByName(String name);
    

    
    List<Facility> findByType(String type);
    
    Optional<Facility> findByEmailAddress(String emailAddress);
    
    List<Facility> findByPhone1(String phone1);
    
    List<Facility> findByAddressId(String addressId);
    
    List<Facility> findByRegistrationDateAfter(LocalDate date);
    
    boolean existsByName(String name);
    
    boolean existsByEmailAddress(String emailAddress);

    void deleteByName(String name);

} 