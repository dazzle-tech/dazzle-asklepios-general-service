package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.exception.EntityInUseException;
import com.asklepios.backend_service.model.DTO.EnumOption;
import com.asklepios.backend_service.model.DTO.RoleDTO;
import com.asklepios.backend_service.model.enums.RoleType;
import com.asklepios.backend_service.model.jpa.Facility;
import com.asklepios.backend_service.model.jpa.Role;
import com.asklepios.backend_service.service.FacilityService;
import com.asklepios.backend_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.asklepios.backend_service.service.FacilityService;
import org.springframework.web.server.ResponseStatusException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setup/role")
//@CrossOrigin(origins = "*")
public class RoleController {

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    private final RoleService roleService;
    private final FacilityService facilityService;

    @Autowired
    public RoleController(RoleService roleService, FacilityService facilityService) {
        this.roleService = roleService;
        this.facilityService = facilityService;
    }

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

        Role createdRole = roleService.saveRole(role);

        return ResponseEntity.ok(new RoleDTO(createdRole));
    }
    //TODO:   always show isValid as true, need to fix it in back end


    @GetMapping
    public ResponseEntity<Page<RoleDTO>> getRoles(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortType
    ) {
        if (sortBy.equalsIgnoreCase("key")) {
            sortBy = "id";
        }
        Sort sort = sortType.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Role> rolesPage = roleService.getRoles(pageable);

        Page<RoleDTO> dtoPage = rolesPage.map(RoleDTO::new);

        return ResponseEntity.ok(dtoPage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try{
            roleService.deleteRole(id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("Cannot delete role because it is assigned to users.");
        }
       catch (Exception e){
           logger.error("Error occurred while deleting role with id " + id, e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.noContent().build();
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        existing.setName(roleData.getName());
        existing.setType(roleData.getType());

        if (roleData.getFacilityId() != null) {
            Facility facility = facilityService.getFacilityById(roleData.getFacilityId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facility not found"));
            existing.setFacility(facility);
        } else {
            existing.setFacility(null);
        }

        Role updated = roleService.saveRole(existing);

        return ResponseEntity.ok(new RoleDTO(updated));
    }

} 