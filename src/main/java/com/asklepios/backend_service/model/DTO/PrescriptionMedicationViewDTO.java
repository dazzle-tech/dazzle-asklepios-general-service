// DTO (للـ response)
package com.asklepios.backend_service.model.DTO;

import com.asklepios.backend_service.model.generated.pojo.ApPrescriptionMedications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicationViewDTO {
    private ApPrescriptionMedications medication;
    private String medicationName;
}
