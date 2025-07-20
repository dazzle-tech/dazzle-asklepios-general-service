package com.asklepios.backend_service.service;

import com.asklepios.backend_service.exception.EntityInUseException;
import com.asklepios.backend_service.exception.EntityNotFoundException;
import com.asklepios.backend_service.model.jpa.Facility;
import com.asklepios.backend_service.model.jpa.Role;
import com.asklepios.backend_service.repository.FacilityRepository;
import io.micrometer.core.util.internal.logging.InternalLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import com.asklepios.backend_service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

@Service
@Transactional
public class FacilityService {

    private static final Logger log = LoggerFactory.getLogger(FacilityService.class);
    @Autowired
    private FacilityRepository facilityRepository;
    
    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }
    
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }



    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }
    
    public List<Facility> getFacilityByName(String name) {
        return facilityRepository.findByName(name);
    }

    public List<Facility> getFacilitiesRegisteredAfter(LocalDate date) {
        return facilityRepository.findByRegistrationDateAfter(date);
    }
    
    public Facility updateFacility(Long id, Facility facilityDetails) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id: " + id));
        
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

     public void deleteFacility(Long id) {
         Facility facility = facilityRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Facility with ID " + id + " not found"));
          facilityRepository.delete(facility);

     }
 }