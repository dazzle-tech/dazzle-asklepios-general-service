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
public class ApProcedureRegistrationEntity implements Serializable {

	private String key;
	private String procedureKey;
	private String consentFormLkey;
	private ApLovValues consentFormLvalue;
	private BigDecimal dateTime;
	private String practitionersKey;
	private String departmentKey;
	private String requestedBy;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String encounterKey;
	private ApProcedureRegistrationEntity translatedObject;

}