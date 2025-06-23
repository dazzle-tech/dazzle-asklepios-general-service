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
public class ApPractitionerEntity implements Serializable {

	private String key;
	private String primaryFacilityKey;
	private String practitionerFullName;
	private String genderLkey;
	private ApLovValues genderLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String departmentKey;
	private String practitionerFirstName;
	private String practitionerLastName;
	private String practitionerEmail;
	private String practitionerPhoneNumber;
	private String specialtyLkey;
	private ApLovValues specialtyLvalue;
	private String subSpecialtyLkey;
	private ApLovValues subSpecialtyLvalue;
	private String defaultMedicalLicense;
	private String secondaryMedicalLicense;
	private String educationalLevelLkey;
	private ApLovValues educationalLevelLvalue;
	private String professionalMembershipAndCertification;
	private Boolean appointable = false;
	private String linkedUser;
	private Date defaultLicenseValidUntil = new Date();
	private Date secondaryLicenseValidUntil = new Date();
	private Date dob = new Date();
	private String jobRoleLkey;
	private ApLovValues jobRoleLvalue;
	private ApPractitionerEntity translatedObject;

}