package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.Data;

@Data
public class NurseSummaryReportRequestDTO {

    private ApPatient patient;
    private ApEncounter encounter;
}
