package com.asklepios.backend_service.model.enums;

public enum RoleType {
    MEDICAL_STAFF("Medical Staff"),
    LAB_STAFF("Lab Staff"),
    ADMIN_ENCOUNTER("Admin - Encounter"),
    ADMIN_APPOINTMENT("Admin - Appointment"),
    SYSTEM_ADMIN("System Administrator"),
    RECEPTION("Receptionist"),
    NURSE("Nurse"),
    TECHNICIAN("Technician");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}