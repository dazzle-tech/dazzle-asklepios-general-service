package com.asklepios.backend_service.model.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long  id;

    @NotNull(message = "must not be null")
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "facility_id", insertable = false, updatable = false)
    private Long facilityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    private Facility facility;
}