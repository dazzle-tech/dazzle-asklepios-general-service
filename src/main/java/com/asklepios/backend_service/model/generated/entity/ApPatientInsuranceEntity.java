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
public class ApPatientInsuranceEntity implements Serializable {

	private String key;
	private String patientKey;
	private String insuranceProviderLkey;
	private ApLovValues insuranceProviderLvalue;
	private Boolean primaryInsurance = false;
	private String insurancePolicyNumber;
	private String groupNumber;
	private String insurancePlanTypeLkey;
	private ApLovValues insurancePlanTypeLvalue;
	private String authorizationNumbers;
	private Date expirationDate = new Date();
	private Boolean coPayment = false;
	private BigDecimal coPaymentValue;
	private Boolean coInsurance = false;
	private BigDecimal coInsuranceValue;
	private Boolean deductibles = false;
	private BigDecimal deductiblesValue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String policyHolder;
	private ApPatientInsuranceEntity translatedObject;

}