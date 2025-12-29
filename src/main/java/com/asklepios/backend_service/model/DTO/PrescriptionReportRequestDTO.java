
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

    private List<PredefinedInstruction> predefinedInstructions;
    private List<CustomInstruction> customInstructions;

    // -----------------------------
    // Nested DTOs (same file)
    // -----------------------------

    @Data
    public static class PredefinedInstruction {
        private Long id;
        private String dose;
        private String unit;
        private String rout;
        private String frequency;
    }

    @Data
    public static class CustomInstruction {
        private Object prescriptionMedicationsKey; // supports String/Long/BigDecimal
        private String dose;
        private LovValue unitLvalue;
        private LovValue frequencyLvalue;
    }

    @Data
    public static class LovValue {
        private String lovDisplayVale;
    }
}
