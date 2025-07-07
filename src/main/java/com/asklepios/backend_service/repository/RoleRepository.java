package com.asklepios.backend_service.repository;

import com.asklepios.backend_service.model.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find by Id
    List<Role> findById(long id);

    // Find by name
    List<Role> findByName(String name);

    // Find by type
    List<Role> findByFacilityId(long type);

    // Check if role exists by name
    boolean existsByName(String name);

    //  Delete operations:
    void deleteById(long id);
} 