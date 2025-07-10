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
public class ApOperationSurgicalPreparationIncisionEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private String surgicalSitePreppedWith;
	private String sitePrepCompletedKey;
	private BigDecimal siteDriedTime;
	private String positionLkey;
	private ApLovValues positionLvalue;
	private Boolean paddingSafetyApplied = false;
	private Boolean instrumentCountStarted = false;
	private String firstInstrumentCountKey;
	private Boolean implantsReady = false;
	private Boolean implantsBarcodeScanned = false;
	private Boolean sterilityConfirmed = false;
	private Boolean disposableDevicesReady = false;
	private BigDecimal timeOfIncision;
	private String surgicalStartMarkedKey;
	private BigDecimal skinOpenedTime;
	private String estimatedSurgeryDuration;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApOperationSurgicalPreparationIncisionEntity translatedObject;

}