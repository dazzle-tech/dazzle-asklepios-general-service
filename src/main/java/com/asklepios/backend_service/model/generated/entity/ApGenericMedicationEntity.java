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
public class ApGenericMedicationEntity implements Serializable {

	private String key;
	private String genericName;
	private String manufacturerLkey;
	private ApLovValues manufacturerLvalue;
	private String usageInstructions;
	private String dosageFormLkey;
	private ApLovValues dosageFormLvalue;
	private Boolean expiresAfterOpening = false;
	private String expiresAfterOpeningValue;
	private Boolean singlePatientUse = false;
	private BigDecimal price;
	private String currencyLkey;
	private ApLovValues currencyLvalue;
	private String priceListKey;
	private BigDecimal cost;
	private String storageRequirements;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String code;
	private String roaLkey;
	private ApLovValues roaLvalue;
	private ApGenericMedicationEntity translatedObject;

}