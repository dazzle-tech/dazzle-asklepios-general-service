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
import com.asklepios.backend_service.model.generated.pojo.ApLicense;
import com.asklepios.backend_service.model.generated.entity.ApLicenseEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApLicenseDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApLicense getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_license where key = '"+key+"'");) {
ApLicense record = new ApLicense();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTenantId(rs.getString("tenant_id"));
record.setLicenseId(rs.getString("license_id"));
record.setLicenseKey(rs.getString("license_key"));
record.setLicenseType(rs.getString("license_type"));
record.setStartDate(rs.getDate("start_date"));
record.setEndDate(rs.getDate("end_date"));
record.setActivestatus(rs.getBoolean("activestatus"));
record.setUuidHwKeys(rs.getString("uuid_hw_keys"));
record.setFacilityAddress(rs.getString("facility_address"));
record.setFacilityLogoFile(rs.getString("facility_logo_file"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApLicense record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_license set key = ?, tenant_id = ?, license_id = ?, license_key = ?, license_type = ?, start_date = ?, end_date = ?, activestatus = ?, uuid_hw_keys = ?, facility_address = ?, facility_logo_file = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTenantId());
ps.setString(3, record.getLicenseId());
ps.setString(4, record.getLicenseKey());
ps.setString(5, record.getLicenseType());
if (record.getStartDate() != null) ps.setDate(6, new java.sql.Date(record.getStartDate().getTime()));
else ps.setDate(6, null); 
if (record.getEndDate() != null) ps.setDate(7, new java.sql.Date(record.getEndDate().getTime()));
else ps.setDate(7, null); 
ps.setBoolean(8, record.getActivestatus());
ps.setString(9, record.getUuidHwKeys());
ps.setString(10, record.getFacilityAddress());
ps.setString(11, record.getFacilityLogoFile());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setBoolean(18, record.getIsValid());
ps.setString(19, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApLicense record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_license set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApLicense> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_license where "+ where);) {
List<ApLicense> list = new ArrayList<ApLicense>();
while(rs.next()){
ApLicense record = new ApLicense();
record.setKey(rs.getString("key"));
record.setTenantId(rs.getString("tenant_id"));
record.setLicenseId(rs.getString("license_id"));
record.setLicenseKey(rs.getString("license_key"));
record.setLicenseType(rs.getString("license_type"));
record.setStartDate(rs.getDate("start_date"));
record.setEndDate(rs.getDate("end_date"));
record.setActivestatus(rs.getBoolean("activestatus"));
record.setUuidHwKeys(rs.getString("uuid_hw_keys"));
record.setFacilityAddress(rs.getString("facility_address"));
record.setFacilityLogoFile(rs.getString("facility_logo_file"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApLicense record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_license values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTenantId());
ps.setString(3, record.getLicenseId());
ps.setString(4, record.getLicenseKey());
ps.setString(5, record.getLicenseType());
if (record.getStartDate() != null) ps.setDate(6, new java.sql.Date(record.getStartDate().getTime()));
else ps.setDate(6, null); 
if (record.getEndDate() != null) ps.setDate(7, new java.sql.Date(record.getEndDate().getTime()));
else ps.setDate(7, null); 
ps.setBoolean(8, record.getActivestatus());
ps.setString(9, record.getUuidHwKeys());
ps.setString(10, record.getFacilityAddress());
ps.setString(11, record.getFacilityLogoFile());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setBoolean(18, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApLicenseEntity entity, String lang) {
        Class<?> myClass = ApLicenseEntity.class;
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
public void translateObject(ApLicenseEntity entity, String lang) {
        ApLicenseEntity translated = (ApLicenseEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}