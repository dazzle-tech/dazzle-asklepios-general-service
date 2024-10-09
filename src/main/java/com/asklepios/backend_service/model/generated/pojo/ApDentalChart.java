package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApDentalChartEntity;

@Getter
@Setter
@Slf4j
public class ApDentalChart extends ApDentalChartEntity implements Serializable {
    List<ApDentalChartTooth> chartTeeth;
    List<ApDentalChartProgressNote> progressNotes;
    ApClinicalDocumentation progressNote;
    List<ApToothAction> chartActions;
}