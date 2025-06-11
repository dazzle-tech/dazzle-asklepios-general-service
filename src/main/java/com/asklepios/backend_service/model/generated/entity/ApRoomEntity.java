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
public class ApRoomEntity implements Serializable {

	private String key;
	private String facilityKey;
	private String departmentKey;
	private String name;
	private String floor;
	private String locationDetails;
	private String typeLkey;
	private ApLovValues typeLvalue;
	private String genderLkey;
	private ApLovValues genderLvalue;
	private Boolean isValid = true;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private ApRoomEntity translatedObject;

}