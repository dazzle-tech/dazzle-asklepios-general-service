package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.jpa.Role;
import com.asklepios.backend_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Create Role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Get all Roles
    public List<Role> getAllFacilities() {
        return roleRepository.findAll();
    }

    // Get Role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

        // Delete Role
        @Transactional
        public void deleteRole(Long id) {
            try {
                roleRepository.deleteById(id);
            } catch (DataIntegrityViolationException ex) {
                throw new RuntimeException("Cannot delete role: it is assigned to users");
            }
        }

}


    
//    // Delete Role
//    public void deleteRole(Long id) {
//        RoleRepository.deleteById(id);
//    }
    
    // Check if Role exists by name
//    public boolean RoleExistsByName(String name) {
//        return RoleRepository.existsByName(name);
//    }
//
