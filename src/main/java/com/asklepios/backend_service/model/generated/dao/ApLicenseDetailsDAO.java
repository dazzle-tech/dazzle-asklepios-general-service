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
import com.asklepios.backend_service.model.generated.pojo.ApLicenseDetails;
import com.asklepios.backend_service.model.generated.entity.ApLicenseDetailsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApLicenseDetailsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApLicenseDetails getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_license_details where key = '"+key+"'");) {
ApLicenseDetails record = new ApLicenseDetails();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTenantId(rs.getString("tenant_id"));
record.setLicenseId(rs.getString("license_id"));
record.setFacilityCount(rs.getBigDecimal("facility_count"));
record.setBedCount(rs.getBigDecimal("bed_count"));
record.setUserCount(rs.getBigDecimal("user_count"));
record.setModules(rs.getString("modules"));
record.setFacilityUsageCount(rs.getBigDecimal("facility_usage_count"));
record.setBedUsageCount(rs.getBigDecimal("bed_usage_count"));
record.setUserUsageCount(rs.getBigDecimal("user_usage_count"));
record.setLastUsageDate(rs.getDate("last_usage_date"));
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
public void updateRecord(ApLicenseDetails record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_license_details set key = ?, tenant_id = ?, license_id = ?, facility_count = ?, bed_count = ?, user_count = ?, modules = ?, facility_usage_count = ?, bed_usage_count = ?, user_usage_count = ?, last_usage_date = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTenantId());
ps.setString(3, record.getLicenseId());
ps.setBigDecimal(4, record.getFacilityCount());
ps.setBigDecimal(5, record.getBedCount());
ps.setBigDecimal(6, record.getUserCount());
ps.setString(7, record.getModules());
ps.setBigDecimal(8, record.getFacilityUsageCount());
ps.setBigDecimal(9, record.getBedUsageCount());
ps.setBigDecimal(10, record.getUserUsageCount());
if (record.getLastUsageDate() != null) ps.setDate(11, new java.sql.Date(record.getLastUsageDate().getTime()));
else ps.setDate(11, null); 
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
public void deleteRecord(ApLicenseDetails record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_license_details set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApLicenseDetails> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_license_details where "+ where);) {
List<ApLicenseDetails> list = new ArrayList<ApLicenseDetails>();
while(rs.next()){
ApLicenseDetails record = new ApLicenseDetails();
record.setKey(rs.getString("key"));
record.setTenantId(rs.getString("tenant_id"));
record.setLicenseId(rs.getString("license_id"));
record.setFacilityCount(rs.getBigDecimal("facility_count"));
record.setBedCount(rs.getBigDecimal("bed_count"));
record.setUserCount(rs.getBigDecimal("user_count"));
record.setModules(rs.getString("modules"));
record.setFacilityUsageCount(rs.getBigDecimal("facility_usage_count"));
record.setBedUsageCount(rs.getBigDecimal("bed_usage_count"));
record.setUserUsageCount(rs.getBigDecimal("user_usage_count"));
record.setLastUsageDate(rs.getDate("last_usage_date"));
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
public String saveRecord(ApLicenseDetails record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_license_details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTenantId());
ps.setString(3, record.getLicenseId());
ps.setBigDecimal(4, record.getFacilityCount());
ps.setBigDecimal(5, record.getBedCount());
ps.setBigDecimal(6, record.getUserCount());
ps.setString(7, record.getModules());
ps.setBigDecimal(8, record.getFacilityUsageCount());
ps.setBigDecimal(9, record.getBedUsageCount());
ps.setBigDecimal(10, record.getUserUsageCount());
if (record.getLastUsageDate() != null) ps.setDate(11, new java.sql.Date(record.getLastUsageDate().getTime()));
else ps.setDate(11, null); 
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
public void populateLovFields(ApLicenseDetailsEntity entity, String lang) {
        Class<?> myClass = ApLicenseDetailsEntity.class;
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
public void translateObject(ApLicenseDetailsEntity entity, String lang) {
        ApLicenseDetailsEntity translated = (ApLicenseDetailsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}