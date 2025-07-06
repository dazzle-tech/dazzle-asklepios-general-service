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
public class ApWarehouseEntity implements Serializable {

	private String key;
	private String departmentKey;
	private String warehouseName;
	private String warehouseId;
	private Boolean isdefault = false;
	private Boolean closeWarehouse = false;
	private String locationKey;
	private String capacity;
	private BigDecimal workingHoursFromTime;
	private BigDecimal workingHoursToTime;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApWarehouseEntity translatedObject;

}