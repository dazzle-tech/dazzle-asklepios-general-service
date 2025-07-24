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
        logger.info("Received request to create facility with data: {}", facility);
        try {
            Facility createdFacility = facilityService.createFacility(facility);
            logger.info("Facility Saved successfully with data: {}", facility);
            return ResponseEntity.ok(createdFacility);
        } catch (Exception ex) {
            logger.error("Error occurred while creating facility: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    //TODO:   always show isValid as true, need to fix it in back end
    // Get all facilities
    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacilities() {
        logger.info("Received request to get all facilities");
        try {
            List<Facility> facilities = facilityService.getAllFacilities();
            logger.info("Successfully retrieved {} facilities", facilities.size());
            return ResponseEntity.ok(facilities);
        } catch (Exception ex) {
            logger.error("Error occurred while fetching facilities", ex);
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id) {
        logger.info("Received request to get facility with id {}", id);
        try {
            Optional<Facility> facility = facilityService.getFacilityById(id);
            if (facility.isPresent()) {
                logger.info("Facility with id {} found", id);
                return ResponseEntity.ok(facility.get());
            } else {
                logger.warn("Facility with id {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            logger.error("Error while fetching facility with id {}: {}", id, ex.getMessage(), ex);
            throw ex;
        }
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
        logger.info("Received request to update facility with id {} and payload: {}", id, facility);
        try {
            Facility updatedFacility = facilityService.updateFacility(id, facility);
            logger.info("Facility with id {} updated successfully to: {}", id, updatedFacility);
            return ResponseEntity.ok(updatedFacility);
        } catch (RuntimeException e) {
            logger.error("Error updating facility with id {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        logger.info("Received request to delete facility with id: {}", id);
        try {
            facilityService.deleteFacility(id);
            logger.info("Facility with id {} deleted successfully", id);
        } catch (DataIntegrityViolationException ex) {
            logger.warn("Attempted to delete facility with id {} but failed due to integrity constraint", id);
            throw new EntityInUseException("Cannot delete facility because it is referenced by other entities.");
        } catch (Exception ex) {
            logger.error("Error occurred while deleting facility with id {}: {}", id, ex.getMessage(), ex);
            throw ex;
        }
        return ResponseEntity.noContent().build();
    }

} 