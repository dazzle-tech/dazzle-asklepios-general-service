package com.asklepios.backend_service.model.pojo.request;


import com.asklepios.backend_service.model.generated.pojo.ApDuplicationCandidateSetup;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;

public class PatientRoleRequest {
    private ApPatient patient;
    private ApDuplicationCandidateSetup role;

    public ApPatient getPatient() {
        return patient;
    }

    public void setPatient(ApPatient patient) {
        this.patient = patient;
    }

    public ApDuplicationCandidateSetup getRole() {
        return role;
    }

    public void setRole(ApDuplicationCandidateSetup role) {
        this.role = role;
    }
// getters & setters
}
