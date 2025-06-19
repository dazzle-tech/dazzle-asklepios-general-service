package com.asklepios.backend_service.model.generated.entity;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;

@Getter
@Setter
@Slf4j
public class ApPostProcedureVitalsEntity implements Serializable {

	private String key;
	private String procedureKey;
	private BigDecimal bloodPressureSystolic;
	private BigDecimal bloodPressureDiastolic;
	private BigDecimal heartRate;
	private BigDecimal temperature;
	private BigDecimal oxygenSaturation;
	private String painScoreLkey;
	private ApLovValues painScoreLvalue;
	private String painDescription;
	private String recoveryNotes;
	private String additionalObservations;
	private Boolean equipmentCountDone = false;
	private String countStatusLkey;
	private ApLovValues countStatusLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApPostProcedureVitalsEntity translatedObject;

}