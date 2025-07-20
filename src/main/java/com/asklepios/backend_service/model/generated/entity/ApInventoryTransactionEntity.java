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
public class ApInventoryTransactionEntity implements Serializable {

	private String key;
	private String transTypeLkey;
	private ApLovValues transTypeLvalue;
	private String warehouseKey;
	private String transReasonLkey;
	private ApLovValues transReasonLvalue;
	private String remarks;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String transId;
	private BigDecimal docNum;
	private ApInventoryTransactionEntity translatedObject;

}