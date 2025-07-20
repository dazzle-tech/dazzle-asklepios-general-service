package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.exception.EntityInUseException;
import com.asklepios.backend_service.model.jpa.Facility;
import com.asklepios.backend_service.service.FacilityService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RestController
@RequestMapping("/setup/facilities")
//@CrossOrigin(origins = "*")
public class FacilityController {
    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    private FacilityService facilityService;

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
    
    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id) {
        Optional<Facility> facility = facilityService.getFacilityById(id);
        return facility.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Facility>> getFacilityByName(@PathVariable String name) {
        List<Facility> facilities = facilityService.getFacilityByName(name);
        return ResponseEntity.ok(facilities);
    }
    


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
    
    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody Facility facility) {
        try {
            Facility updatedFacility = facilityService.updateFacility(id, facility);
            return ResponseEntity.ok(updatedFacility);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {

     try {
        facilityService.deleteFacility(id);
    }
        catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("Cannot delete facility because it is referenced by other entities.");
     }
        return ResponseEntity.noContent().build();
    }

} 