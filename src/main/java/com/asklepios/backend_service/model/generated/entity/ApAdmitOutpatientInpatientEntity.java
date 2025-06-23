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
public class ApAdmitOutpatientInpatientEntity implements Serializable {

	private String key;
	private String toEncounterKey;
	private String fromEncounterKey;
	private String inpatientDepartmentKey;
	private String physicianKey;
	private String admissionNotes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private ApAdmitOutpatientInpatientEntity translatedObject;

}