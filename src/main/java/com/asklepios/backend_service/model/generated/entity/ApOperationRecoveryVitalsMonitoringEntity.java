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
public class ApOperationRecoveryVitalsMonitoringEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private BigDecimal recordedTime;
	private BigDecimal bloodPressureSystolic;
	private BigDecimal bloodPressureDiastolic;
	private BigDecimal heartRate;
	private BigDecimal temperature;
	private BigDecimal oxygenSaturation;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApOperationRecoveryVitalsMonitoringEntity translatedObject;

}