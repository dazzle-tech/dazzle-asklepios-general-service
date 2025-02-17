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
public class ApDiagnosticOrderTestsResultEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String orderKey;
	private String medicalTestKey;
	private String orderTestKey;
	private String normalRangeKey;
	private String resultType;
	private String resultLkey;
	private ApLovValues resultLvalue;
	private BigDecimal resultValueNumber;
	private String marker;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String processingStatusLkey;
	private ApLovValues processingStatusLvalue;
	private String orderTypeLkey;
	private ApLovValues orderTypeLvalue;
	private BigDecimal approvedAt;
	private String approvedBy;
	private BigDecimal rejectedAt;
	private String rejectedBy;
	private String rejectedReason;
	private ApDiagnosticOrderTestsResultEntity translatedObject;

}