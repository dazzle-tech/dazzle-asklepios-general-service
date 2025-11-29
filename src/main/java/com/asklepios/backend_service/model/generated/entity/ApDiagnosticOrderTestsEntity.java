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
public class ApDiagnosticOrderTestsEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String orderKey;
	private String testKey;
	private BigDecimal receivedLabId;
	private String reasonLkey;
	private ApLovValues reasonLvalue;
	private String priorityLkey;
	private ApLovValues priorityLvalue;
	private String notes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String processingStatusLkey;
	private ApLovValues processingStatusLvalue;
	private BigDecimal submitDate;
	private String orderTypeLkey;
	private ApLovValues orderTypeLvalue;
	private BigDecimal acceptedAt;
	private String acceptedBy;
	private BigDecimal rejectedAt;
	private String rejectedBy;
	private String rejectedReason;
	private BigDecimal patientArrivedAt;
	private String patientArrivedNoteRad;
	private BigDecimal readyAt;
	private BigDecimal approvedAt;
	private String cancellationReason;
	private BigDecimal fromDepartmentId;
	private BigDecimal fromFacilityId;
	private BigDecimal toFacilityId;
	private ApDiagnosticOrderTestsEntity translatedObject;

}