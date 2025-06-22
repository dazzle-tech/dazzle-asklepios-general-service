package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApEncounterEntity;

@Getter
@Setter
@Slf4j
public class ApEncounter extends ApEncounterEntity implements Serializable {
    private String departmentName;
    ApPatient patientObject;
    private String diagnosis;
    private boolean hasOrder;
    private boolean hasAllergy;
    private boolean HasObservation;
    private boolean hasPrescription;
    private boolean isObservations;
    private String BloodGroup;
    Object resourceObject;
    ApPractitioner practitionerObject;
    public boolean isEditable() {
        if (getEncounterStatusLkey() == null) {
            return false;
        } else if (getEncounterStatusLkey().equals("91109811181900")) // TODO replace with redis by lov code (ENC_STATUS/CLOSED)
        {
            return false;
        }

        return true;
    }
}