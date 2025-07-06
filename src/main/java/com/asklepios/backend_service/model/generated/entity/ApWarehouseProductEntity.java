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
public class ApWarehouseProductEntity implements Serializable {

	private String key;
	private String warehouseKey;
	private String productKey;
	private BigDecimal quantity;
	private BigDecimal reOrderQuantity;
	private BigDecimal miniOrder;
	private BigDecimal maxOrder;
	private BigDecimal workingHoursFromTime;
	private BigDecimal workingHoursToTime;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApWarehouseProductEntity translatedObject;

}