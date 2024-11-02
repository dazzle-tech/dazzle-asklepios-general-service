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
import com.asklepios.backend_service.model.generated.pojo.ApUser;
import com.asklepios.backend_service.model.generated.entity.ApUserEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApUserDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApUser getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_user where key = '"+key+"'");) {
ApUser record = new ApUser();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setUsername(rs.getString("username"));
record.setPassword(rs.getString("password"));
record.setFullName(rs.getString("full_name"));
record.setVerified(rs.getString("verified"));
record.setLastGeneratedOtp(rs.getString("last_generated_otp"));
record.setPasscode(rs.getString("passcode"));
record.setTenantKey(rs.getString("tenant_key"));
record.setOrganizationKey(rs.getString("organization_key"));
record.setAccessRoleKey(rs.getString("access_role_key"));
record.setEmail(rs.getString("email"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentKey(rs.getString("department_key"));
record.setFirstName(rs.getString("first_name"));
record.setSecondName(rs.getString("second_name"));
record.setLastName(rs.getString("last_name"));
record.setPhoneNumber(rs.getBigDecimal("phone_number"));
record.setSexAtBirthLkey(rs.getString("sex_at_birth_lkey"));
record.setDob(rs.getDate("dob"));
record.setJobRoleKey(rs.getString("job_role_key"));
record.setJobDescription(rs.getString("job_description"));
record.setMustChangePassword(rs.getBoolean("must_change_password"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApUser record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_user set key = ?, username = ?, password = ?, full_name = ?, verified = ?, last_generated_otp = ?, passcode = ?, tenant_key = ?, organization_key = ?, access_role_key = ?, email = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, department_key = ?, first_name = ?, second_name = ?, last_name = ?, phone_number = ?, sex_at_birth_lkey = ?, dob = ?, job_role_key = ?, job_description = ?, must_change_password = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getUsername());
ps.setString(3, record.getPassword());
ps.setString(4, record.getFullName());
ps.setString(5, record.getVerified());
ps.setString(6, record.getLastGeneratedOtp());
ps.setString(7, record.getPasscode());
ps.setString(8, record.getTenantKey());
ps.setString(9, record.getOrganizationKey());
ps.setString(10, record.getAccessRoleKey());
ps.setString(11, record.getEmail());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setBoolean(18, record.getIsValid());
ps.setString(19, record.getDepartmentKey());
ps.setString(20, record.getFirstName());
ps.setString(21, record.getSecondName());
ps.setString(22, record.getLastName());
ps.setBigDecimal(23, record.getPhoneNumber());
ps.setString(24, record.getSexAtBirthLkey());
if (record.getDob() != null) ps.setDate(25, new java.sql.Date(record.getDob().getTime()));
else ps.setDate(25, null); 
ps.setString(26, record.getJobRoleKey());
ps.setString(27, record.getJobDescription());
ps.setBoolean(28, record.getMustChangePassword());
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApUser record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_user set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApUser> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_user where "+ where);) {
List<ApUser> list = new ArrayList<ApUser>();
while(rs.next()){
ApUser record = new ApUser();
record.setKey(rs.getString("key"));
record.setUsername(rs.getString("username"));
record.setPassword(rs.getString("password"));
record.setFullName(rs.getString("full_name"));
record.setVerified(rs.getString("verified"));
record.setLastGeneratedOtp(rs.getString("last_generated_otp"));
record.setPasscode(rs.getString("passcode"));
record.setTenantKey(rs.getString("tenant_key"));
record.setOrganizationKey(rs.getString("organization_key"));
record.setAccessRoleKey(rs.getString("access_role_key"));
record.setEmail(rs.getString("email"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentKey(rs.getString("department_key"));
record.setFirstName(rs.getString("first_name"));
record.setSecondName(rs.getString("second_name"));
record.setLastName(rs.getString("last_name"));
record.setPhoneNumber(rs.getBigDecimal("phone_number"));
record.setSexAtBirthLkey(rs.getString("sex_at_birth_lkey"));
record.setDob(rs.getDate("dob"));
record.setJobRoleKey(rs.getString("job_role_key"));
record.setJobDescription(rs.getString("job_description"));
record.setMustChangePassword(rs.getBoolean("must_change_password"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApUser record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getUsername());
ps.setString(3, record.getPassword());
ps.setString(4, record.getFullName());
ps.setString(5, record.getVerified());
ps.setString(6, record.getLastGeneratedOtp());
ps.setString(7, record.getPasscode());
ps.setString(8, record.getTenantKey());
ps.setString(9, record.getOrganizationKey());
ps.setString(10, record.getAccessRoleKey());
ps.setString(11, record.getEmail());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setBoolean(18, record.getIsValid());
ps.setString(19, record.getDepartmentKey());
ps.setString(20, record.getFirstName());
ps.setString(21, record.getSecondName());
ps.setString(22, record.getLastName());
ps.setBigDecimal(23, record.getPhoneNumber());
ps.setString(24, record.getSexAtBirthLkey());
if (record.getDob() != null) ps.setDate(25, new java.sql.Date(record.getDob().getTime()));
else ps.setDate(25, null); 
ps.setString(26, record.getJobRoleKey());
ps.setString(27, record.getJobDescription());
ps.setBoolean(28, record.getMustChangePassword());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApUserEntity entity, String lang) {
        Class<?> myClass = ApUserEntity.class;
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
public void translateObject(ApUserEntity entity, String lang) {
        ApUserEntity translated = (ApUserEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}