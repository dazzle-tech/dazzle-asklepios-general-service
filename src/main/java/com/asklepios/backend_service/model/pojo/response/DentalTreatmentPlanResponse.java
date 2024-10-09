package com.asklepios.backend_service.model.pojo.response;

import com.asklepios.backend_service.model.generated.pojo.ApDentalChart;
import com.asklepios.backend_service.model.generated.pojo.ApDentalPlannedTreatment;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DentalTreatmentPlanResponse implements Serializable {
    List<ApDentalPlannedTreatment> draftTreatments;
    List<ApDentalPlannedTreatment> currentTreatments;
}
