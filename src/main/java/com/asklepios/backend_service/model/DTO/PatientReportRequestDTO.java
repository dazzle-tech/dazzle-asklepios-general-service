// FILE: PatientReportRequestDTO.java
package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientReportRequestDTO {

    private ApPatient patient;

    private String facilityName;
    private String authenticatedUserName;
    private String authenticatedUserEmail;

    private String profilePictureBase64;
    private String profilePictureUrl;

    private List<SecondaryDocument> secondaryDocuments;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SecondaryDocument {
        private String documentNo;
        private String documentType;
        private String documentCountry;
    }
}
