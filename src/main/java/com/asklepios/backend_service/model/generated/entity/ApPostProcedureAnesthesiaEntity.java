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
public class ApPostProcedureAnesthesiaEntity implements Serializable {

	private String key;
	private String procedureKey;
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
	private BigDecimal aldreteScore;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApPostProcedureAnesthesiaEntity translatedObject;

}