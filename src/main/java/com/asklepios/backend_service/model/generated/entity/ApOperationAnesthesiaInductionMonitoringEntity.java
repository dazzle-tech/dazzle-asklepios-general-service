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
public class ApOperationAnesthesiaInductionMonitoringEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private BigDecimal weight;
	private BigDecimal fastingDuration;
	private Boolean ivLineEstablished = false;
	private String monitorsConnected;
	private Boolean intubationDone = false;
	private String tubeSize;
	private String tubeType;
	private String securedBy;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String encounterKey;
	private String patientKey;
	private String adverseEventsLkey;
	private ApLovValues adverseEventsLvalue;
	private String adverseEventsNote;
	private String actionsTaken;
	private Boolean surgeonNotified = false;
	private BigDecimal inductionStartTime;
	private String intubationDoneNote;
	private ApOperationAnesthesiaInductionMonitoringEntity translatedObject;

}