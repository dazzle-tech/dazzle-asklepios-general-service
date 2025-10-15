package com.asklepios.backend_service.model.generated.dao;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.lang.System;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.asklepios.backend_service.controller.PublicServices;
import java.lang.reflect.Field;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatient getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient where key = '"+key+"'");) {
ApPatient record = new ApPatient();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientMrn(rs.getString("patient_mrn"));
record.setNamePrefix(rs.getString("name_prefix"));
record.setNameSuffix(rs.getString("name_suffix"));
record.setPatientAlias(rs.getString("patient_alias"));
record.setFirstName(rs.getString("first_name"));
record.setSecondName(rs.getString("second_name"));
record.setThirdName(rs.getString("third_name"));
record.setLastName(rs.getString("last_name"));
record.setFullName(rs.getString("full_name"));
record.setSecondNameOtherLang(rs.getString("second_name_other_lang"));
record.setFirstNameOtherLang(rs.getString("first_name_other_lang"));
record.setThirdNameOtherLang(rs.getString("third_name_other_lang"));
record.setLastNameOtherLang(rs.getString("last_name_other_lang"));
record.setFullNameOtherLang(rs.getString("full_name_other_lang"));
record.setDocumentCountryLkey(rs.getString("document_country_lkey"));
record.setDocumentTypeLkey(rs.getString("document_type_lkey"));
record.setDocumentNo(rs.getString("document_no"));
record.setNoDocument(rs.getBoolean("no_document"));
record.setSpecialCourtesyLkey(rs.getString("special_courtesy_lkey"));
record.setUnknown(rs.getString("unknown"));
record.setPhoneNumber(rs.getString("phone_number"));
record.setMobileNumber(rs.getString("mobile_number"));
record.setEmail(rs.getString("email"));
record.setMaritalStatusLkey(rs.getString("marital_status_lkey"));
record.setNationalityLkey(rs.getString("nationality_lkey"));
record.setPrimaryLanguageLkey(rs.getString("primary_language_lkey"));
record.setReligionLkey(rs.getString("religion_lkey"));
record.setEthnicityLkey(rs.getString("ethnicity_lkey"));
record.setOccupationLkey(rs.getString("occupation_lkey"));
record.setEmergencyContactName(rs.getString("emergency_contact_name"));
record.setEmergencyContactRelationLkey(rs.getString("emergency_contact_relation_lkey"));
record.setEmergencyContactPhone(rs.getString("emergency_contact_phone"));
record.setStreetAddressLine1(rs.getString("street_address_line1"));
record.setStreetAddressLine2(rs.getString("street_address_line2"));
record.setCountryLkey(rs.getString("country_lkey"));
record.setStateProvinceRegionLkey(rs.getString("state_province_region_lkey"));
record.setCityLkey(rs.getString("city_lkey"));
record.setPostalCode(rs.getString("postal_code"));
record.setAdditionalInfo(rs.getString("additional_info"));
record.setLongitude(rs.getString("longitude"));
record.setIsActive(rs.getString("is_active"));
record.setDeathDatetime(rs.getDate("death_datetime"));
record.setDob(rs.getDate("dob"));
record.setMultipleBirth(rs.getBoolean("multiple_birth"));
record.setBirthOrder(rs.getBigDecimal("birth_order"));
record.setNumSiblings(rs.getBigDecimal("num_siblings"));
record.setAccessLevel(rs.getBigDecimal("access_level"));
record.setFacilityKey(rs.getString("facility_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setPatientClassLkey(rs.getString("patient_class_lkey"));
record.setPrivatePatient(rs.getBoolean("private_patient"));
record.setVerificationOtp(rs.getString("verification_otp"));
record.setSecurityAccessLevelLkey(rs.getString("security_access_level_lkey"));
record.setSocialSecurityNumber(rs.getString("social_security_number"));
record.setNoticeOfPrivacyPractice(rs.getBoolean("notice_of_privacy_practice"));
record.setNoticeOfPrivacyPracticeDate(rs.getDate("notice_of_privacy_practice_date"));
record.setPrivacyAuthorization(rs.getBoolean("privacy_authorization"));
record.setPrivacyAuthorizationDate(rs.getDate("privacy_authorization_date"));
record.setConsent(rs.getBoolean("consent"));
record.setConsentDate(rs.getDate("consent_date"));
record.setVerified(rs.getBoolean("verified"));
record.setResponsiblePartyLkey(rs.getString("responsible_party_lkey"));
record.setEducationalLevelLkey(rs.getString("educational_level_lkey"));
record.setPreviousId(rs.getString("previous_id"));
record.setArchivingNumber(rs.getString("archiving_number"));
record.setReceiveSms(rs.getBoolean("receive_sms"));
record.setReceiveEmail(rs.getBoolean("receive_email"));
record.setHomePhone(rs.getString("home_phone"));
record.setWorkPhone(rs.getString("work_phone"));
record.setPreferredContactLkey(rs.getString("preferred_contact_lkey"));
record.setUnknownPatient(rs.getBoolean("unknown_patient"));
record.setIncompletePatient(rs.getBoolean("incomplete_patient"));
record.setExtraDetails(rs.getString("extra_details"));
record.setSecondaryMobileNumber(rs.getString("secondary_mobile_number"));
record.setRoleLkey(rs.getString("role_lkey"));
record.setDistrictLkey(rs.getString("district_lkey"));
record.setCountryId(rs.getString("country_id"));
record.setBloodGroupLkey(rs.getString("blood_group_lkey"));
record.setGenderLkey(rs.getString("gender_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient set key = ?, patient_mrn = ?, name_prefix = ?, name_suffix = ?, patient_alias = ?, first_name = ?, second_name = ?, third_name = ?, last_name = ?, full_name = ?, second_name_other_lang = ?, first_name_other_lang = ?, third_name_other_lang = ?, last_name_other_lang = ?, full_name_other_lang = ?, document_country_lkey = ?, document_type_lkey = ?, document_no = ?, no_document = ?, special_courtesy_lkey = ?, unknown = ?, phone_number = ?, mobile_number = ?, email = ?, marital_status_lkey = ?, nationality_lkey = ?, primary_language_lkey = ?, religion_lkey = ?, ethnicity_lkey = ?, occupation_lkey = ?, emergency_contact_name = ?, emergency_contact_relation_lkey = ?, emergency_contact_phone = ?, street_address_line1 = ?, street_address_line2 = ?, country_lkey = ?, state_province_region_lkey = ?, city_lkey = ?, postal_code = ?, additional_info = ?, longitude = ?, is_active = ?, death_datetime = ?, dob = ?, multiple_birth = ?, birth_order = ?, num_siblings = ?, access_level = ?, facility_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, patient_class_lkey = ?, private_patient = ?, verification_otp = ?, security_access_level_lkey = ?, social_security_number = ?, notice_of_privacy_practice = ?, notice_of_privacy_practice_date = ?, privacy_authorization = ?, privacy_authorization_date = ?, consent = ?, consent_date = ?, verified = ?, responsible_party_lkey = ?, educational_level_lkey = ?, previous_id = ?, archiving_number = ?, receive_sms = ?, receive_email = ?, home_phone = ?, work_phone = ?, preferred_contact_lkey = ?, unknown_patient = ?, incomplete_patient = ?, extra_details = ?, secondary_mobile_number = ?, role_lkey = ?, district_lkey = ?, country_id = ?, blood_group_lkey = ?, gender_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientMrn());
ps.setString(3, record.getNamePrefix());
ps.setString(4, record.getNameSuffix());
ps.setString(5, record.getPatientAlias());
ps.setString(6, record.getFirstName());
ps.setString(7, record.getSecondName());
ps.setString(8, record.getThirdName());
ps.setString(9, record.getLastName());
ps.setString(10, record.getFullName());
ps.setString(11, record.getSecondNameOtherLang());
ps.setString(12, record.getFirstNameOtherLang());
ps.setString(13, record.getThirdNameOtherLang());
ps.setString(14, record.getLastNameOtherLang());
ps.setString(15, record.getFullNameOtherLang());
ps.setString(16, record.getDocumentCountryLkey());
ps.setString(17, record.getDocumentTypeLkey());
ps.setString(18, record.getDocumentNo());
ps.setBoolean(19, record.getNoDocument());
ps.setString(20, record.getSpecialCourtesyLkey());
ps.setString(21, record.getUnknown());
ps.setString(22, record.getPhoneNumber());
ps.setString(23, record.getMobileNumber());
ps.setString(24, record.getEmail());
ps.setString(25, record.getMaritalStatusLkey());
ps.setString(26, record.getNationalityLkey());
ps.setString(27, record.getPrimaryLanguageLkey());
ps.setString(28, record.getReligionLkey());
ps.setString(29, record.getEthnicityLkey());
ps.setString(30, record.getOccupationLkey());
ps.setString(31, record.getEmergencyContactName());
ps.setString(32, record.getEmergencyContactRelationLkey());
ps.setString(33, record.getEmergencyContactPhone());
ps.setString(34, record.getStreetAddressLine1());
ps.setString(35, record.getStreetAddressLine2());
ps.setString(36, record.getCountryLkey());
ps.setString(37, record.getStateProvinceRegionLkey());
ps.setString(38, record.getCityLkey());
ps.setString(39, record.getPostalCode());
ps.setString(40, record.getAdditionalInfo());
ps.setString(41, record.getLongitude());
ps.setString(42, record.getIsActive());
if (record.getDeathDatetime() != null) ps.setDate(43, new java.sql.Date(record.getDeathDatetime().getTime()));
else ps.setDate(43, null); 
if (record.getDob() != null) ps.setDate(44, new java.sql.Date(record.getDob().getTime()));
else ps.setDate(44, null); 
ps.setBoolean(45, record.getMultipleBirth());
ps.setBigDecimal(46, record.getBirthOrder());
ps.setBigDecimal(47, record.getNumSiblings());
ps.setBigDecimal(48, record.getAccessLevel());
ps.setString(49, record.getFacilityKey());
ps.setString(50, record.getCreatedBy());
ps.setString(51, record.getUpdatedBy());
ps.setString(52, record.getDeletedBy());
ps.setBigDecimal(53, record.getCreatedAt());
ps.setBigDecimal(54, record.getUpdatedAt());
ps.setBigDecimal(55, record.getDeletedAt());
ps.setBoolean(56, record.getIsValid());
ps.setString(57, record.getPatientClassLkey());
ps.setBoolean(58, record.getPrivatePatient());
ps.setString(59, record.getVerificationOtp());
ps.setString(60, record.getSecurityAccessLevelLkey());
ps.setString(61, record.getSocialSecurityNumber());
ps.setBoolean(62, record.getNoticeOfPrivacyPractice());
if (record.getNoticeOfPrivacyPracticeDate() != null) ps.setDate(63, new java.sql.Date(record.getNoticeOfPrivacyPracticeDate().getTime()));
else ps.setDate(63, null); 
ps.setBoolean(64, record.getPrivacyAuthorization());
if (record.getPrivacyAuthorizationDate() != null) ps.setDate(65, new java.sql.Date(record.getPrivacyAuthorizationDate().getTime()));
else ps.setDate(65, null); 
ps.setBoolean(66, record.getConsent());
if (record.getConsentDate() != null) ps.setDate(67, new java.sql.Date(record.getConsentDate().getTime()));
else ps.setDate(67, null); 
ps.setBoolean(68, record.getVerified());
ps.setString(69, record.getResponsiblePartyLkey());
ps.setString(70, record.getEducationalLevelLkey());
ps.setString(71, record.getPreviousId());
ps.setString(72, record.getArchivingNumber());
ps.setBoolean(73, record.getReceiveSms());
ps.setBoolean(74, record.getReceiveEmail());
ps.setString(75, record.getHomePhone());
ps.setString(76, record.getWorkPhone());
ps.setString(77, record.getPreferredContactLkey());
ps.setBoolean(78, record.getUnknownPatient());
ps.setBoolean(79, record.getIncompletePatient());
ps.setString(80, record.getExtraDetails());
ps.setString(81, record.getSecondaryMobileNumber());
ps.setString(82, record.getRoleLkey());
ps.setString(83, record.getDistrictLkey());
ps.setString(84, record.getCountryId());
ps.setString(85, record.getBloodGroupLkey());
ps.setString(86, record.getGenderLkey());
ps.setString(87, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatient> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient where "+ where);) {
List<ApPatient> list = new ArrayList<ApPatient>();
while(rs.next()){
ApPatient record = new ApPatient();
record.setKey(rs.getString("key"));
record.setPatientMrn(rs.getString("patient_mrn"));
record.setNamePrefix(rs.getString("name_prefix"));
record.setNameSuffix(rs.getString("name_suffix"));
record.setPatientAlias(rs.getString("patient_alias"));
record.setFirstName(rs.getString("first_name"));
record.setSecondName(rs.getString("second_name"));
record.setThirdName(rs.getString("third_name"));
record.setLastName(rs.getString("last_name"));
record.setFullName(rs.getString("full_name"));
record.setSecondNameOtherLang(rs.getString("second_name_other_lang"));
record.setFirstNameOtherLang(rs.getString("first_name_other_lang"));
record.setThirdNameOtherLang(rs.getString("third_name_other_lang"));
record.setLastNameOtherLang(rs.getString("last_name_other_lang"));
record.setFullNameOtherLang(rs.getString("full_name_other_lang"));
record.setDocumentCountryLkey(rs.getString("document_country_lkey"));
record.setDocumentTypeLkey(rs.getString("document_type_lkey"));
record.setDocumentNo(rs.getString("document_no"));
record.setNoDocument(rs.getBoolean("no_document"));
record.setSpecialCourtesyLkey(rs.getString("special_courtesy_lkey"));
record.setUnknown(rs.getString("unknown"));
record.setPhoneNumber(rs.getString("phone_number"));
record.setMobileNumber(rs.getString("mobile_number"));
record.setEmail(rs.getString("email"));
record.setMaritalStatusLkey(rs.getString("marital_status_lkey"));
record.setNationalityLkey(rs.getString("nationality_lkey"));
record.setPrimaryLanguageLkey(rs.getString("primary_language_lkey"));
record.setReligionLkey(rs.getString("religion_lkey"));
record.setEthnicityLkey(rs.getString("ethnicity_lkey"));
record.setOccupationLkey(rs.getString("occupation_lkey"));
record.setEmergencyContactName(rs.getString("emergency_contact_name"));
record.setEmergencyContactRelationLkey(rs.getString("emergency_contact_relation_lkey"));
record.setEmergencyContactPhone(rs.getString("emergency_contact_phone"));
record.setStreetAddressLine1(rs.getString("street_address_line1"));
record.setStreetAddressLine2(rs.getString("street_address_line2"));
record.setCountryLkey(rs.getString("country_lkey"));
record.setStateProvinceRegionLkey(rs.getString("state_province_region_lkey"));
record.setCityLkey(rs.getString("city_lkey"));
record.setPostalCode(rs.getString("postal_code"));
record.setAdditionalInfo(rs.getString("additional_info"));
record.setLongitude(rs.getString("longitude"));
record.setIsActive(rs.getString("is_active"));
record.setDeathDatetime(rs.getDate("death_datetime"));
record.setDob(rs.getDate("dob"));
record.setMultipleBirth(rs.getBoolean("multiple_birth"));
record.setBirthOrder(rs.getBigDecimal("birth_order"));
record.setNumSiblings(rs.getBigDecimal("num_siblings"));
record.setAccessLevel(rs.getBigDecimal("access_level"));
record.setFacilityKey(rs.getString("facility_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setPatientClassLkey(rs.getString("patient_class_lkey"));
record.setPrivatePatient(rs.getBoolean("private_patient"));
record.setVerificationOtp(rs.getString("verification_otp"));
record.setSecurityAccessLevelLkey(rs.getString("security_access_level_lkey"));
record.setSocialSecurityNumber(rs.getString("social_security_number"));
record.setNoticeOfPrivacyPractice(rs.getBoolean("notice_of_privacy_practice"));
record.setNoticeOfPrivacyPracticeDate(rs.getDate("notice_of_privacy_practice_date"));
record.setPrivacyAuthorization(rs.getBoolean("privacy_authorization"));
record.setPrivacyAuthorizationDate(rs.getDate("privacy_authorization_date"));
record.setConsent(rs.getBoolean("consent"));
record.setConsentDate(rs.getDate("consent_date"));
record.setVerified(rs.getBoolean("verified"));
record.setResponsiblePartyLkey(rs.getString("responsible_party_lkey"));
record.setEducationalLevelLkey(rs.getString("educational_level_lkey"));
record.setPreviousId(rs.getString("previous_id"));
record.setArchivingNumber(rs.getString("archiving_number"));
record.setReceiveSms(rs.getBoolean("receive_sms"));
record.setReceiveEmail(rs.getBoolean("receive_email"));
record.setHomePhone(rs.getString("home_phone"));
record.setWorkPhone(rs.getString("work_phone"));
record.setPreferredContactLkey(rs.getString("preferred_contact_lkey"));
record.setUnknownPatient(rs.getBoolean("unknown_patient"));
record.setIncompletePatient(rs.getBoolean("incomplete_patient"));
record.setExtraDetails(rs.getString("extra_details"));
record.setSecondaryMobileNumber(rs.getString("secondary_mobile_number"));
record.setRoleLkey(rs.getString("role_lkey"));
record.setDistrictLkey(rs.getString("district_lkey"));
record.setCountryId(rs.getString("country_id"));
record.setBloodGroupLkey(rs.getString("blood_group_lkey"));
record.setGenderLkey(rs.getString("gender_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientMrn());
ps.setString(3, record.getNamePrefix());
ps.setString(4, record.getNameSuffix());
ps.setString(5, record.getPatientAlias());
ps.setString(6, record.getFirstName());
ps.setString(7, record.getSecondName());
ps.setString(8, record.getThirdName());
ps.setString(9, record.getLastName());
ps.setString(10, record.getFullName());
ps.setString(11, record.getSecondNameOtherLang());
ps.setString(12, record.getFirstNameOtherLang());
ps.setString(13, record.getThirdNameOtherLang());
ps.setString(14, record.getLastNameOtherLang());
ps.setString(15, record.getFullNameOtherLang());
ps.setString(16, record.getDocumentCountryLkey());
ps.setString(17, record.getDocumentTypeLkey());
ps.setString(18, record.getDocumentNo());
ps.setBoolean(19, record.getNoDocument());
ps.setString(20, record.getSpecialCourtesyLkey());
ps.setString(21, record.getUnknown());
ps.setString(22, record.getPhoneNumber());
ps.setString(23, record.getMobileNumber());
ps.setString(24, record.getEmail());
ps.setString(25, record.getMaritalStatusLkey());
ps.setString(26, record.getNationalityLkey());
ps.setString(27, record.getPrimaryLanguageLkey());
ps.setString(28, record.getReligionLkey());
ps.setString(29, record.getEthnicityLkey());
ps.setString(30, record.getOccupationLkey());
ps.setString(31, record.getEmergencyContactName());
ps.setString(32, record.getEmergencyContactRelationLkey());
ps.setString(33, record.getEmergencyContactPhone());
ps.setString(34, record.getStreetAddressLine1());
ps.setString(35, record.getStreetAddressLine2());
ps.setString(36, record.getCountryLkey());
ps.setString(37, record.getStateProvinceRegionLkey());
ps.setString(38, record.getCityLkey());
ps.setString(39, record.getPostalCode());
ps.setString(40, record.getAdditionalInfo());
ps.setString(41, record.getLongitude());
ps.setString(42, record.getIsActive());
if (record.getDeathDatetime() != null) ps.setDate(43, new java.sql.Date(record.getDeathDatetime().getTime()));
else ps.setDate(43, null); 
if (record.getDob() != null) ps.setDate(44, new java.sql.Date(record.getDob().getTime()));
else ps.setDate(44, null); 
ps.setBoolean(45, record.getMultipleBirth());
ps.setBigDecimal(46, record.getBirthOrder());
ps.setBigDecimal(47, record.getNumSiblings());
ps.setBigDecimal(48, record.getAccessLevel());
ps.setString(49, record.getFacilityKey());
ps.setString(50, record.getCreatedBy());
ps.setString(51, record.getUpdatedBy());
ps.setString(52, record.getDeletedBy());
ps.setBigDecimal(53, record.getCreatedAt());
ps.setBigDecimal(54, record.getUpdatedAt());
ps.setBigDecimal(55, record.getDeletedAt());
ps.setBoolean(56, record.getIsValid());
ps.setString(57, record.getPatientClassLkey());
ps.setBoolean(58, record.getPrivatePatient());
ps.setString(59, record.getVerificationOtp());
ps.setString(60, record.getSecurityAccessLevelLkey());
ps.setString(61, record.getSocialSecurityNumber());
ps.setBoolean(62, record.getNoticeOfPrivacyPractice());
if (record.getNoticeOfPrivacyPracticeDate() != null) ps.setDate(63, new java.sql.Date(record.getNoticeOfPrivacyPracticeDate().getTime()));
else ps.setDate(63, null); 
ps.setBoolean(64, record.getPrivacyAuthorization());
if (record.getPrivacyAuthorizationDate() != null) ps.setDate(65, new java.sql.Date(record.getPrivacyAuthorizationDate().getTime()));
else ps.setDate(65, null); 
ps.setBoolean(66, record.getConsent());
if (record.getConsentDate() != null) ps.setDate(67, new java.sql.Date(record.getConsentDate().getTime()));
else ps.setDate(67, null); 
ps.setBoolean(68, record.getVerified());
ps.setString(69, record.getResponsiblePartyLkey());
ps.setString(70, record.getEducationalLevelLkey());
ps.setString(71, record.getPreviousId());
ps.setString(72, record.getArchivingNumber());
ps.setBoolean(73, record.getReceiveSms());
ps.setBoolean(74, record.getReceiveEmail());
ps.setString(75, record.getHomePhone());
ps.setString(76, record.getWorkPhone());
ps.setString(77, record.getPreferredContactLkey());
ps.setBoolean(78, record.getUnknownPatient());
ps.setBoolean(79, record.getIncompletePatient());
ps.setString(80, record.getExtraDetails());
ps.setString(81, record.getSecondaryMobileNumber());
ps.setString(82, record.getRoleLkey());
ps.setString(83, record.getDistrictLkey());
ps.setString(84, record.getCountryId());
ps.setString(85, record.getBloodGroupLkey());
ps.setString(86, record.getGenderLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientEntity entity, String lang) {
        Class<?> myClass = ApPatientEntity.class;
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.contains("Lkey")) {
                try {
                    Object fieldValue = field.get(entity);
                    if (fieldValue != null) {
                        String _lovKey = fieldValue.toString();
                        Field valueField = myClass.getDeclaredField (fieldName.replaceAll("Lkey", "Lvalue"));
                        valueField.setAccessible(true);
                        if (lang == null) {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey));
                        } else {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey,lang));
                        }                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
public void translateObject(ApPatientEntity entity, String lang) {
        ApPatientEntity translated = (ApPatientEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}