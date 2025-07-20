package com.asklepios.backend_service.repository;

import com.asklepios.backend_service.model.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findById(long id);

    List<Role> findByName(String name);

    List<Role> findByFacilityId(long type);

    boolean existsByName(String name);

    void deleteById(long id);
} 