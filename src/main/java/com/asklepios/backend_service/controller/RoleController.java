package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.DTO.EnumOption;
import com.asklepios.backend_service.model.DTO.RoleDTO;
import com.asklepios.backend_service.model.enums.RoleType;
import com.asklepios.backend_service.model.jpa.Facility;
import com.asklepios.backend_service.model.jpa.Role;
import com.asklepios.backend_service.service.FacilityService;
import com.asklepios.backend_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asklepios.backend_service.service.FacilityService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setup/role")
//@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;
    private final FacilityService facilityService;

    @Autowired
    public RoleController(RoleService roleService, FacilityService facilityService) {
        this.roleService = roleService;
        this.facilityService = facilityService;
    }

    // Create role
    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleData) {
        Role role = new Role();
        role.setName(roleData.getName());
        role.setType(roleData.getType());

        if (roleData.getFacilityId() != null) {
            Facility facility = facilityService.getFacilityById(roleData.getFacilityId())
                    .orElseThrow(() -> new RuntimeException("Facility not found"));
            role.setFacility(facility);
        }

        Role createdRole = roleService.createRole(role);

        return ResponseEntity.ok(new RoleDTO(createdRole));
    }
    //TODO:   always show isValid as true, need to fix it in back end
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<Role> roles = roleService.getAllFacilities();
        List<RoleDTO> dtos = roles.stream()
                .map(RoleDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    
    // Get role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Delete role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/types")
    public ResponseEntity<List<EnumOption>> getRoleTypes() {
        List<EnumOption> options = Arrays.stream(RoleType.values())
                .map(rt -> new EnumOption(rt.name(), rt.getDisplayName()))
                .toList();

        return ResponseEntity.ok(options);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleData) {
        Role existing = roleService.getRoleById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existing.setName(roleData.getName());
        existing.setType(roleData.getType());

        if (roleData.getFacilityId() != null) {
            Facility facility = facilityService.getFacilityById(roleData.getFacilityId())
                    .orElseThrow(() -> new RuntimeException("Facility not found"));
            existing.setFacility(facility);
        } else {
            existing.setFacility(null);
        }

        Role updated = roleService.createRole(existing);

        return ResponseEntity.ok(new RoleDTO(updated));
    }

} 