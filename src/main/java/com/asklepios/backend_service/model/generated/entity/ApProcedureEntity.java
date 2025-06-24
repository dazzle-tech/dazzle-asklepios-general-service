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
public class ApProcedureEntity implements Serializable {

	private String key;
	private String procedureNameKey;
	private String procedureId;
	private String procedureLevelLkey;
	private ApLovValues procedureLevelLvalue;
	private String categoryKey;
	private String indications;
	private String priorityLkey;
	private ApLovValues priorityLvalue;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private BigDecimal scheduledDateTime;
	private String notes;
	private String departmentKey;
	private String cancellationReason;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String facilityKey;
	private String encounterKey;
	private String bodyPartLkey;
	private ApLovValues bodyPartLvalue;
	private String sideLkey;
	private ApLovValues sideLvalue;
	private Boolean currentDepartment = false;
	private String patientKey;
	private ApProcedureEntity translatedObject;

}