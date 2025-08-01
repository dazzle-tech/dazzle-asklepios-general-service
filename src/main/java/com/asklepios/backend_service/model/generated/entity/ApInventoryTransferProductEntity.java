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
public class ApInventoryTransferProductEntity implements Serializable {

	private String key;
	private String transferKey;
	private String productKey;
	private BigDecimal quentityRequested;
	private BigDecimal quentityApproved;
	private String lotserialnumber;
	private Boolean isEffectedWarehouse = false;
	private String notes;
	private String transUomKey;
	private BigDecimal quentityRequestedBaseUom;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private BigDecimal quentityApprovedBaseUom;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String rejectedReason;
	private ApInventoryTransferProductEntity translatedObject;

}