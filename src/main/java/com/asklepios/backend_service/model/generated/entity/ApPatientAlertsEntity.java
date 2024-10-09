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
public class ApPatientAlertsEntity implements Serializable {

	private String key;
	private String patientKey;
	private String alertTypeLkey;
	private ApLovValues alertTypeLvalue;
	private String alertSourceLkey;
	private ApLovValues alertSourceLvalue;
	private String alertDescription;
	private String alertSeverityLkey;
	private ApLovValues alertSeverityLvalue;
	private Boolean isResolved = false;
	private Date alertDate = new Date();
	private Date dateResolved = new Date();
	private String notes;
	private String sourceOfInfoLkey;
	private ApLovValues sourceOfInfoLvalue;
	private String sourceKey;
	private Boolean lifeThreating = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientAlertsEntity translatedObject;

}