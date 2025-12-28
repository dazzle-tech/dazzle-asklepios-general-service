package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionReportRequestDTO {

    private ApPatient patient;
    private ApEncounter encounter;
    private String prescriptionKey;

    private String facilityName;
    private String authenticatedUserName;
    private String authenticatedUserEmail;
    private List<BrandMedicationDTO> genericMedicationList;
}
