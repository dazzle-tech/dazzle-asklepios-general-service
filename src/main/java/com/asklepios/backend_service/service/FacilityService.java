package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.jpa.Facility;
import com.asklepios.backend_service.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
@Transactional
public class FacilityService {
    
    @Autowired
    private FacilityRepository facilityRepository;
    
    // Create facility
    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }
    
    // Get all facilities
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }
    
    // Get facility by ID
    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }
    
    // Get facility by name
    public List<Facility> getFacilityByName(String name) {
        return facilityRepository.findByName(name);
    }
    
    // Get facility by email
    public Optional<Facility> getFacilityByEmail(String emailAddress) {
        return facilityRepository.findByEmailAddress(emailAddress);
    }
    

    
    // Get facilities by type
    public List<Facility> getFacilitiesByType(String type) {
        return facilityRepository.findByType(type);
    }
    
    // Get facilities by phone
    public List<Facility> getFacilitiesByPhone(String phone1) {
        return facilityRepository.findByPhone1(phone1);
    }
    
    // Get facilities by address ID
    public List<Facility> getFacilitiesByAddressId(String addressId) {
        return facilityRepository.findByAddressId(addressId);
    }
    
    // Get facilities registered after a specific date
    public List<Facility> getFacilitiesRegisteredAfter(LocalDate date) {
        return facilityRepository.findByRegistrationDateAfter(date);
    }
    
    // Update facility
    public Facility updateFacility(Long id, Facility facilityDetails) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        
        facility.setName(facilityDetails.getName());
        facility.setType(facilityDetails.getType());
        facility.setRegistrationDate(facilityDetails.getRegistrationDate());
        facility.setEmailAddress(facilityDetails.getEmailAddress());
        facility.setPhone1(facilityDetails.getPhone1());
        facility.setPhone2(facilityDetails.getPhone2());
        facility.setFax(facilityDetails.getFax());
        facility.setAddressId(facilityDetails.getAddressId());
        facility.setDefaultCurrencyLkey(facilityDetails.getDefaultCurrencyLkey());
        facility.setIsValid(facilityDetails.getIsValid());
        
        return facilityRepository.save(facility);
    }
    
    // Delete facility
    public void deleteFacility(Long id) {
        facilityRepository.deleteById(id);
    }
    
    // Check if facility exists by name
    public boolean facilityExistsByName(String name) {
        return facilityRepository.existsByName(name);
    }
    
    // Check if facility exists by email
    public boolean facilityExistsByEmail(String emailAddress) {
        return facilityRepository.existsByEmailAddress(emailAddress);
    }
} 