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
public class ApUserEntity implements Serializable {

	private String key;
	private String username;
	private String password;
	private String fullName;
	private String verified;
	private String lastGeneratedOtp;
	private String passcode;
	private String tenantKey;
	private String organizationKey;
	private String accessRoleKey;
	private String email;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String departmentKey;
	private String firstName;
	private String secondName;
	private String lastName;
	private Date dob = new Date();
	private String sexAtBirthLkey;
	private ApLovValues sexAtBirthLvalue;
	private BigDecimal phoneNumber;
	private String jobDescription;
	private String jobRoleLkey;
	private ApLovValues jobRoleLvalue;
	private String jobRoleKey;
	private Boolean mustChangePassword = false;
	private ApUserEntity translatedObject;


}