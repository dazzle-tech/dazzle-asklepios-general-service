package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.jpa.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;

    @NotBlank(message = "Role name is required")
    private String name;

    @NotBlank(message = "Role type is required")
    private String type;

    @NotBlank(message = "facility is required")
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