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
public class ApOperationDischargeReadinessEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private String activityLkey;
	private ApLovValues activityLvalue;
	private String respirationLkey;
	private ApLovValues respirationLvalue;
	private String circulationLkey;
	private ApLovValues circulationLvalue;
	private String consciousnessLkey;
	private ApLovValues consciousnessLvalue;
	private String oxygenSaturationLkey;
	private ApLovValues oxygenSaturationLvalue;
	private String aldreteScore;
	private Boolean painControlled = false;
	private Boolean vitalsStable = false;
	private Boolean fullyAwake = false;
	private Boolean maintainAirway = false;
	private Boolean siteDressingIntact = false;
	private Boolean nauseaControlled = false;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApOperationDischargeReadinessEntity translatedObject;

}