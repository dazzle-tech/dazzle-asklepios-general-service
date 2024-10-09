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
public class ApGlobalSettingsEntity implements Serializable {

	private String key;
	private String facilityKey;
	private String settingKey;
	private String settingValue;
	private String settingCategory;
	private Boolean requireRestart = false;
	private Boolean requirePasscode = false;
	private Boolean fixedValue = false;
	private Boolean forAdminUse = false;
	private Boolean hiddenSetting = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApGlobalSettingsEntity translatedObject;

}