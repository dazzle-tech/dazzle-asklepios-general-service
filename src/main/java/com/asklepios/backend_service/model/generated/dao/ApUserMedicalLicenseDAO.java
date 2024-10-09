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
import com.asklepios.backend_service.model.generated.pojo.ApUserMedicalLicense;
import com.asklepios.backend_service.model.generated.entity.ApUserMedicalLicenseEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApUserMedicalLicenseDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApUserMedicalLicense getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_user_medical_license where key = '"+key+"'");) {
ApUserMedicalLicense record = new ApUserMedicalLicense();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setLicenseName(rs.getString("license_name"));
record.setLicenseNumber(rs.getString("license_number"));
record.setValidTo(rs.getDate("valid_to"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setUserKey(rs.getString("user_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApUserMedicalLicense record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_user_medical_license set key = ?, license_name = ?, license_number = ?, valid_to = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, user_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getLicenseName());
ps.setString(3, record.getLicenseNumber());
if (record.getValidTo() != null) ps.setDate(4, new java.sql.Date(record.getValidTo().getTime()));
else ps.setDate(4, null); 
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getUserKey());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApUserMedicalLicense record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_user_medical_license set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApUserMedicalLicense> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_user_medical_license where "+ where);) {
List<ApUserMedicalLicense> list = new ArrayList<ApUserMedicalLicense>();
while(rs.next()){
ApUserMedicalLicense record = new ApUserMedicalLicense();
record.setKey(rs.getString("key"));
record.setLicenseName(rs.getString("license_name"));
record.setLicenseNumber(rs.getString("license_number"));
record.setValidTo(rs.getDate("valid_to"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setUserKey(rs.getString("user_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApUserMedicalLicense record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_user_medical_license values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getLicenseName());
ps.setString(3, record.getLicenseNumber());
if (record.getValidTo() != null) ps.setDate(4, new java.sql.Date(record.getValidTo().getTime()));
else ps.setDate(4, null); 
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getUserKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApUserMedicalLicenseEntity entity, String lang) {
        Class<?> myClass = ApUserMedicalLicenseEntity.class;
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
public void translateObject(ApUserMedicalLicenseEntity entity, String lang) {
        ApUserMedicalLicenseEntity translated = (ApUserMedicalLicenseEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}