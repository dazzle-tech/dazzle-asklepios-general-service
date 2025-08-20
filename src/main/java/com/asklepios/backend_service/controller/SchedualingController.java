package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.DTO.ResourceAvailabilityDTO;
import com.asklepios.backend_service.model.DTO.ResourceAvailabilityEntryDTO;
import com.asklepios.backend_service.model.DTO.ResourceAvailabilityRequestDTO;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
@Slf4j

public class SchedualingController {
    private final ApResourceAvailabilitySliceService apResourceAvailabilitySliceService;


    public SchedualingController(ApResourceAvailabilitySliceService apResourceAvailabilitySliceService) {
        this.apResourceAvailabilitySliceService = apResourceAvailabilitySliceService;
    }


    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveAvailability(@RequestBody ResourceAvailabilityRequestDTO requestDTO) {
        try {
            apResourceAvailabilitySliceService.saveFullAvailability(requestDTO);
            return ResponseEntity.ok().body("Availability saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving availability: " + e.getMessage());
        }
    }

}
