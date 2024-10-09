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
public class ApPhysicalExamAreaEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String physicalExamAreaLkey;
	private ApLovValues physicalExamAreaLvalue;
	private String physicalExamAreaDetailLkey;
	private ApLovValues physicalExamAreaDetailLvalue;
	private String notes;
	private String sourceOfAnswerLkey;
	private ApLovValues sourceOfAnswerLvalue;
	private Boolean pass = false;
	private String passReasonLkey;
	private ApLovValues passReasonLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPhysicalExamAreaEntity translatedObject;

}