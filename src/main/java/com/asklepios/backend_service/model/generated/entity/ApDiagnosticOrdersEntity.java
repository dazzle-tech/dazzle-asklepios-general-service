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
public class ApDiagnosticOrdersEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String orderId;
	private Boolean saveDraft = false;
	private String submittedBy;
	private BigDecimal submittedAt;
	private Boolean isUrgent = false;
	private ApDiagnosticOrdersEntity translatedObject;

}