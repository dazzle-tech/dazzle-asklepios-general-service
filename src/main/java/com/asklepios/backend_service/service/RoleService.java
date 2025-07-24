package com.asklepios.backend_service.service;
import com.asklepios.backend_service.exception.RoleDeletionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.asklepios.backend_service.model.jpa.Role;
import com.asklepios.backend_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        log.info("Saving role: {}", role.getName());
        try {
            Role savedRole = roleRepository.save(role);
            log.debug("Saved role with ID: {}", savedRole.getId());
            return savedRole;
        } catch (Exception e) {
            log.error("Error while saving role: {}", role.getName(), e);
            throw e;
        }
    }

    public List<Role> getAllRoles() {
        log.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        log.debug("Fetched {} roles", roles.size());
        return roles;
    }


    public Page<Role> getRoles(Pageable pageable) {
        log.info("Fetching roles with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return roleRepository.findAll(pageable);
    }

    public Optional<Role> getRoleById(Long id) {
        log.info("Fetching role with ID: {}", id);
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            log.warn("Role with ID {} not found", id);
        } else {
            log.debug("Role found: {}", role.get().getName());
        }
        return role;
    }

    @Transactional
    public void deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception ex) {
            log.error(String.valueOf(ex.getClass()),ex);
        }
    }
}


