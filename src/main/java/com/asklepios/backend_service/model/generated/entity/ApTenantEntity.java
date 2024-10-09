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
public class ApTenantEntity implements Serializable {

	private String key;
	private String tenantId;
	private String tenantName;
	private String tenantType;
	private Date tenantRegistrationDate = new Date();
	private Date tenantExpiryDate = new Date();
	private String tenantEmailAddress;
	private String tenantBriefDesc;
	private String tenantSecurityToken;
	private Boolean tenantDataGlobal = false;
	private String tenantSchemaName;
	private String tenantDbConnstr;
	private String tenantDbAdminUser;
	private String tenantLogoPath;
	private String tenantBackgroundPath;
	private String tenantSlogan;
	private String tenantLoginText;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApTenantEntity translatedObject;

}