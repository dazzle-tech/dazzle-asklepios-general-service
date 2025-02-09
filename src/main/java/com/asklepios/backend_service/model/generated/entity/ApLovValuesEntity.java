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
public class ApLovValuesEntity implements Serializable {

	private String key;
	private String lovKey;
	private String lovCode;
	private String valueCode;
	private String lovDisplayVale;
	private String loveCustomCode;
	private String valueDescription;
	private String valueColor;
	private String valueIcon;
	private BigDecimal valueOrder;
	private Boolean isdefault = false;
	private Boolean seededData = false;
	private Boolean forInternalUser = false;
	private String specificForScreenId;
	private String parentValueId;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApLovValuesEntity translatedObject;

}