package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.jpa.Facility;
import com.asklepios.backend_service.service.FacilityService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setup/facilities")
//@CrossOrigin(origins = "*")
public class FacilityController {
    
    @Autowired
    private FacilityService facilityService;
    
    // Create facility
    @PostMapping
    public ResponseEntity<Facility> createFacility(@Valid @RequestBody Facility facility) {
        Facility createdFacility = facilityService.createFacility(facility);
        return ResponseEntity.ok(createdFacility);
    }
    //TODO:   always show isValid as true, need to fix it in back end
    // Get all facilities
    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return ResponseEntity.ok(facilities);
    }
    
    // Get facility by ID
    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id) {
        Optional<Facility> facility = facilityService.getFacilityById(id);
        return facility.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Get facility by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Facility>> getFacilityByName(@PathVariable String name) {
        List<Facility> facilities = facilityService.getFacilityByName(name);
        return ResponseEntity.ok(facilities);
    }
    
    // Get facility by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Facility> getFacilityByEmail(@PathVariable String email) {
        Optional<Facility> facility = facilityService.getFacilityByEmail(email);
        return facility.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    
    // Get facilities by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Facility>> getFacilitiesByType(@PathVariable String type) {
        List<Facility> facilities = facilityService.getFacilitiesByType(type);
        return ResponseEntity.ok(facilities);
    }
    
    // Get facilities by phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Facility>> getFacilitiesByPhone(@PathVariable String phone) {
        List<Facility> facilities = facilityService.getFacilitiesByPhone(phone);
        return ResponseEntity.ok(facilities);
    }
    
    // Get facilities by address ID
    @GetMapping("/address/{addressId}")
    public ResponseEntity<List<Facility>> getFacilitiesByAddressId(@PathVariable String addressId) {
        List<Facility> facilities = facilityService.getFacilitiesByAddressId(addressId);
        return ResponseEntity.ok(facilities);
    }
    
    // Get facilities registered after date
    @GetMapping("/registered-after")
    public ResponseEntity<List<Facility>> getFacilitiesRegisteredAfter(@RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Facility> facilities = facilityService.getFacilitiesRegisteredAfter(localDate);
            return ResponseEntity.ok(facilities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Update facility
    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody Facility facility) {
        try {
            Facility updatedFacility = facilityService.updateFacility(id, facility);
            return ResponseEntity.ok(updatedFacility);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete facility
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok().build();
    }
    
    // Check if facility exists by name
    @GetMapping("/exists/name/{name}")
    public ResponseEntity<Boolean> facilityExistsByName(@PathVariable String name) {
        boolean exists = facilityService.facilityExistsByName(name);
        return ResponseEntity.ok(exists);
    }
    
    // Check if facility exists by email
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> facilityExistsByEmail(@PathVariable String email) {
        boolean exists = facilityService.facilityExistsByEmail(email);
        return ResponseEntity.ok(exists);
    }
} 