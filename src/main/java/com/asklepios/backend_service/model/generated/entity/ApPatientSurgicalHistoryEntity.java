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
public class ApPatientSurgicalHistoryEntity implements Serializable {

	private String key;
	private String patientKey;
	private String surgery;
	private BigDecimal dateOfSurgery;
	private String facility;
	private Boolean other = false;
	private String otherDesc;
	private String anesthesiaTypeLkey;
	private ApLovValues anesthesiaTypeLvalue;
	private String complicationsLkey;
	private ApLovValues complicationsLvalue;
	private String adverseReactionsToAnesthesiaLkey;
	private ApLovValues adverseReactionsToAnesthesiaLvalue;
	private Boolean isImplantsOrDevices = false;
	private String implantsOrDevicesDescription;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientSurgicalHistoryEntity translatedObject;

}