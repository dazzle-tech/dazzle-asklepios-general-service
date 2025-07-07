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
public class ApOperationIntraoperativeMonitoringEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private BigDecimal period;
	private BigDecimal spo2;
	private BigDecimal bpSystolic;
	private BigDecimal bpDiastolic;
	private BigDecimal respiratoryRate;
	private BigDecimal temperature;
	private BigDecimal etco2;
	private BigDecimal fluidsGiven;
	private BigDecimal bloodGiven;
	private BigDecimal urineOutput;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String encounterKey;
	private String patientKey;
	private ApOperationIntraoperativeMonitoringEntity translatedObject;

}