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
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Page<Role> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
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


