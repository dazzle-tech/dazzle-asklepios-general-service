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
public class ApTeleConsultationEntity implements Serializable {

	private String id;
	private String questionToConsultant;
	private String consultantFacilityId;
	private String consultantDepartmentId;
	private String specialtyLkey;
	private ApLovValues specialtyLvalue;
	private String urgencyLkey;
	private ApLovValues urgencyLvalue;
	private String expectedResponse;
	private String notes;
	private BigDecimal expectedResponseTime;
	private BigDecimal startedAt;
	private BigDecimal rejectedAt;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String rejectedReason;
	private String patientId;
	private String encounterId;
	private String rejectedBy;
	private String startedBy;
	private BigDecimal requestedAt;
	private String requestedBy;
	private BigDecimal callStartedAt;
	private String callStartedBy;
	private BigDecimal callColsedAt;
	private String callColsedBy;
	private ApTeleConsultationEntity translatedObject;

}