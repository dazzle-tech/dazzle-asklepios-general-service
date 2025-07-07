package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.jpa.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;
    private String type;
    private Long facilityId;
    private String facilityName;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.type = role.getType();
        this.facilityId = role.getFacilityId();
        this.facilityName = role.getFacility() != null ? role.getFacility().getName() : null;
    }
}