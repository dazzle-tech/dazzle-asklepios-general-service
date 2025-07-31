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
public class ApInventoryTransactionProductEntity implements Serializable {

	private String key;
	private String inventoryTransKey;
	private String productKey;
	private BigDecimal newQuentity;
	private String lotserialnumber;
	private BigDecimal newCost;
	private String currencyLkey;
	private ApLovValues currencyLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private Boolean isEffectedWarehouse = false;
	private Date expiryDate = new Date();
	private String notes;
	private String transUomKey;
	private BigDecimal newQuentityBaseUom;
	private ApInventoryTransactionProductEntity translatedObject;

}