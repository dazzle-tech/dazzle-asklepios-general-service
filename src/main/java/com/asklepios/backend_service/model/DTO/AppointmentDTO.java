package com.asklepios.backend_service.model.DTO;

import java.util.List;
import lombok.Data;
@Data
public class AppointmentDTO {
    private String key;
    private String patientKey;
    private String facilityKey;
    private String resourceTypeLkey;
    private String resourceKey;
    private String visitTypeLkey;
    private String durationLkey;
    private String appointmentStart;
    private String appointmentEnd;
    private String instructions;
    private String notes;
    private String priorityLkey;
    private String isReminder;
    private String reminderLkey;
    private String consentForm;
    private String referingPhysicianLkey;
    private String externalPhysician;
    private String procedureLevelLkey;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;
    private Boolean isValid;
    private String resourceLkey;
    private String instructionsLkey;
    private String appointmentStatus;
    private String reasonLkey;
    private String reasonValue;
    private String otherReason;
    private String noShowReasonLkey;
    private String noShowReasonValue;
    private String noShowOtherReason;

    private List<TimeSliceDTO> slices;
    private String departmentKey;
}
