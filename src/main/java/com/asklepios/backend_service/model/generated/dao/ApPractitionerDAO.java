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
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import com.asklepios.backend_service.model.generated.entity.ApPractitionerEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPractitionerDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPractitioner getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_practitioner where key = '"+key+"'");) {
ApPractitioner record = new ApPractitioner();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPrimaryFacilityKey(rs.getString("primary_facility_key"));
record.setPractitionerFullName(rs.getString("practitioner_full_name"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentKey(rs.getString("department_key"));
record.setPractitionerFirstName(rs.getString("practitioner_first_name"));
record.setPractitionerLastName(rs.getString("practitioner_last_name"));
record.setPractitionerEmail(rs.getString("practitioner_email"));
record.setPractitionerPhoneNumber(rs.getString("practitioner_phone_number"));
record.setJobRole(rs.getString("job_role"));
record.setSpecialtyLkey(rs.getString("specialty_lkey"));
record.setSubSpecialtyLkey(rs.getString("sub_specialty_lkey"));
record.setDefaultMedicalLicense(rs.getString("default_medical_license"));
record.setSecondaryMedicalLicense(rs.getString("secondary_medical_license"));
record.setEducationalLevelLkey(rs.getString("educational_level_lkey"));
record.setProfessionalMembershipAndCertification(rs.getString("professional_membership_and_certification"));
record.setAppointable(rs.getBoolean("appointable"));
record.setLinkedUser(rs.getString("linked_user"));
record.setDefaultLicenseValidUntil(rs.getDate("default_license_valid_until"));
record.setSecondaryLicenseValidUntil(rs.getDate("secondary_license_valid_until"));
record.setDob(rs.getDate("dob"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPractitioner record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_practitioner set key = ?, primary_facility_key = ?, practitioner_full_name = ?, gender_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, department_key = ?, practitioner_first_name = ?, practitioner_last_name = ?, practitioner_email = ?, practitioner_phone_number = ?, job_role = ?, specialty_lkey = ?, sub_specialty_lkey = ?, default_medical_license = ?, secondary_medical_license = ?, educational_level_lkey = ?, professional_membership_and_certification = ?, appointable = ?, linked_user = ?, default_license_valid_until = ?, secondary_license_valid_until = ?, dob = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPrimaryFacilityKey());
ps.setString(3, record.getPractitionerFullName());
ps.setString(4, record.getGenderLkey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getDepartmentKey());
ps.setString(13, record.getPractitionerFirstName());
ps.setString(14, record.getPractitionerLastName());
ps.setString(15, record.getPractitionerEmail());
ps.setString(16, record.getPractitionerPhoneNumber());
ps.setString(17, record.getJobRole());
ps.setString(18, record.getSpecialtyLkey());
ps.setString(19, record.getSubSpecialtyLkey());
ps.setString(20, record.getDefaultMedicalLicense());
ps.setString(21, record.getSecondaryMedicalLicense());
ps.setString(22, record.getEducationalLevelLkey());
ps.setString(23, record.getProfessionalMembershipAndCertification());
ps.setBoolean(24, record.getAppointable());
ps.setString(25, record.getLinkedUser());
if (record.getDefaultLicenseValidUntil() != null) ps.setDate(26, new java.sql.Date(record.getDefaultLicenseValidUntil().getTime()));
else ps.setDate(26, null); 
if (record.getSecondaryLicenseValidUntil() != null) ps.setDate(27, new java.sql.Date(record.getSecondaryLicenseValidUntil().getTime()));
else ps.setDate(27, null); 
if (record.getDob() != null) ps.setDate(28, new java.sql.Date(record.getDob().getTime()));
else ps.setDate(28, null); 
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPractitioner record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_practitioner set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPractitioner> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_practitioner where "+ where);) {
List<ApPractitioner> list = new ArrayList<ApPractitioner>();
while(rs.next()){
ApPractitioner record = new ApPractitioner();
record.setKey(rs.getString("key"));
record.setPrimaryFacilityKey(rs.getString("primary_facility_key"));
record.setPractitionerFullName(rs.getString("practitioner_full_name"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentKey(rs.getString("department_key"));
record.setPractitionerFirstName(rs.getString("practitioner_first_name"));
record.setPractitionerLastName(rs.getString("practitioner_last_name"));
record.setPractitionerEmail(rs.getString("practitioner_email"));
record.setPractitionerPhoneNumber(rs.getString("practitioner_phone_number"));
record.setJobRole(rs.getString("job_role"));
record.setSpecialtyLkey(rs.getString("specialty_lkey"));
record.setSubSpecialtyLkey(rs.getString("sub_specialty_lkey"));
record.setDefaultMedicalLicense(rs.getString("default_medical_license"));
record.setSecondaryMedicalLicense(rs.getString("secondary_medical_license"));
record.setEducationalLevelLkey(rs.getString("educational_level_lkey"));
record.setProfessionalMembershipAndCertification(rs.getString("professional_membership_and_certification"));
record.setAppointable(rs.getBoolean("appointable"));
record.setLinkedUser(rs.getString("linked_user"));
record.setDefaultLicenseValidUntil(rs.getDate("default_license_valid_until"));
record.setSecondaryLicenseValidUntil(rs.getDate("secondary_license_valid_until"));
record.setDob(rs.getDate("dob"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPractitioner record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_practitioner values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPrimaryFacilityKey());
ps.setString(3, record.getPractitionerFullName());
ps.setString(4, record.getGenderLkey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getDepartmentKey());
ps.setString(13, record.getPractitionerFirstName());
ps.setString(14, record.getPractitionerLastName());
ps.setString(15, record.getPractitionerEmail());
ps.setString(16, record.getPractitionerPhoneNumber());
ps.setString(17, record.getJobRole());
ps.setString(18, record.getSpecialtyLkey());
ps.setString(19, record.getSubSpecialtyLkey());
ps.setString(20, record.getDefaultMedicalLicense());
ps.setString(21, record.getSecondaryMedicalLicense());
ps.setString(22, record.getEducationalLevelLkey());
ps.setString(23, record.getProfessionalMembershipAndCertification());
ps.setBoolean(24, record.getAppointable());
ps.setString(25, record.getLinkedUser());
if (record.getDefaultLicenseValidUntil() != null) ps.setDate(26, new java.sql.Date(record.getDefaultLicenseValidUntil().getTime()));
else ps.setDate(26, null); 
if (record.getSecondaryLicenseValidUntil() != null) ps.setDate(27, new java.sql.Date(record.getSecondaryLicenseValidUntil().getTime()));
else ps.setDate(27, null); 
if (record.getDob() != null) ps.setDate(28, new java.sql.Date(record.getDob().getTime()));
else ps.setDate(28, null); 
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPractitionerEntity entity, String lang) {
        Class<?> myClass = ApPractitionerEntity.class;
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
public void translateObject(ApPractitionerEntity entity, String lang) {
        ApPractitionerEntity translated = (ApPractitionerEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}