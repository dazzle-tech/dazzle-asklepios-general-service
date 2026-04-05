package com.asklepios.backend_service.model.newEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Getter
@Setter
@Slf4j
public class PatientEncounter  implements Serializable {

    private Long key;

    // ====== Relations as IDs ======
    private Long patientKey;
    private String followUpEncounterId;

    private Long facilityId;
    private Long departmentId;
    private Long practitionerId;

    // ====== Basic Fields ======
    private String encounterNumber;

    private String encounterType;
    private String encounterReason;
    private String priorityLevel;

    private String originType;
    private String originName;
    private String notes;

    private Integer departmentDailySequenceNumber;

    private String encounterDate;
    private String status;

    private String chiefComplaint;

    private String dischargeType;
    private String dischargeAt;




}
