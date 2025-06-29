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
public class ApProductsEntity implements Serializable {

	private String key;
	private String typeLkey;
	private ApLovValues typeLvalue;
	private String name;
	private String medicationKey;
	private String code;
	private String barecode;
	private String uomGroupKey;
	private String baseUomKey;
	private String dispenseUomKey;
	private Boolean isBatchManaged = false;
	private Boolean isExpiryDateMandatory = false;
	private Boolean isSerialized = false;
	private Boolean isReusable = false;
	private String inventoryTypeLkey;
	private ApLovValues inventoryTypeLvalue;
	private Date dateDiagnosed = new Date();
	private String problemStatusLkey;
	private ApLovValues problemStatusLvalue;
	private String severityLkey;
	private ApLovValues severityLvalue;
	private BigDecimal shelfLife;
	private BigDecimal leadTime;
	private String erpIntegId;
	private Date startDate = new Date();
	private Date endDate = new Date();
	private BigDecimal maintenanceScheduleTime;
	private BigDecimal maintenanceScheduleLkey;
	private ApLovValues maintenanceScheduleLvalue;
	private Boolean isCritical = false;
	private Boolean isCalibration = false;
	private Boolean isTraining = false;
	private BigDecimal avgCost;
	private String priceBaseUom;
	private Boolean isControlledSubstance = false;
	private Boolean isAllergyRisk = false;
	private String hazardousTag;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApProductsEntity translatedObject;

}