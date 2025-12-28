package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.Data;

@Data
public class RadiologyReportRequestDTO {

    private ApPatient patient;
    private ApEncounter encounter;

    private String reportKey;
    private String reportHtml;
    private String reportStatus;
    private String severity;

    private String testName;
    private String testCode;
    private String orderId;
    private Long reportDate;

    private String facilityName;

    private String authenticatedUserName;
    private String authenticatedUserEmail;
}
