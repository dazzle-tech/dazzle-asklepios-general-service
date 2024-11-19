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
import com.asklepios.backend_service.model.generated.pojo.ApResourcesAvailabilityTime;
import com.asklepios.backend_service.model.generated.entity.ApResourcesAvailabilityTimeEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApResourcesAvailabilityTimeDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApResourcesAvailabilityTime getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_resources_availability_time where key = '"+key+"'");) {
ApResourcesAvailabilityTime record = new ApResourcesAvailabilityTime();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setResourceKey(rs.getString("resource_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setDayLkey(rs.getString("day_lkey"));
record.setStartTime(rs.getBigDecimal("start_time"));
record.setEndTime(rs.getBigDecimal("end_time"));
record.setIsHasBreak(rs.getBoolean("is_has_break"));
record.setBreakFrom(rs.getBigDecimal("break_from"));
record.setBreakTo(rs.getBigDecimal("break_to"));
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
public void updateRecord(ApResourcesAvailabilityTime record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_resources_availability_time set key = ?, resource_key = ?, facility_key = ?, department_key = ?, day_lkey = ?, start_time = ?, end_time = ?, is_has_break = ?, break_from = ?, break_to = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getResourceKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getDepartmentKey());
ps.setString(5, record.getDayLkey());
ps.setBigDecimal(6, record.getStartTime());
ps.setBigDecimal(7, record.getEndTime());
ps.setBoolean(8, record.getIsHasBreak());
ps.setBigDecimal(9, record.getBreakFrom());
ps.setBigDecimal(10, record.getBreakTo());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.setString(18, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApResourcesAvailabilityTime record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_resources_availability_time set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApResourcesAvailabilityTime> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_resources_availability_time where "+ where);) {
List<ApResourcesAvailabilityTime> list = new ArrayList<ApResourcesAvailabilityTime>();
while(rs.next()){
ApResourcesAvailabilityTime record = new ApResourcesAvailabilityTime();
record.setKey(rs.getString("key"));
record.setResourceKey(rs.getString("resource_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setDayLkey(rs.getString("day_lkey"));
record.setStartTime(rs.getBigDecimal("start_time"));
record.setEndTime(rs.getBigDecimal("end_time"));
record.setIsHasBreak(rs.getBoolean("is_has_break"));
record.setBreakFrom(rs.getBigDecimal("break_from"));
record.setBreakTo(rs.getBigDecimal("break_to"));
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
public String saveRecord(ApResourcesAvailabilityTime record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_resources_availability_time values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getResourceKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getDepartmentKey());
ps.setString(5, record.getDayLkey());
ps.setBigDecimal(6, record.getStartTime());
ps.setBigDecimal(7, record.getEndTime());
ps.setBoolean(8, record.getIsHasBreak());
ps.setBigDecimal(9, record.getBreakFrom());
ps.setBigDecimal(10, record.getBreakTo());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApResourcesAvailabilityTimeEntity entity, String lang) {
        Class<?> myClass = ApResourcesAvailabilityTimeEntity.class;
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
public void translateObject(ApResourcesAvailabilityTimeEntity entity, String lang) {
        ApResourcesAvailabilityTimeEntity translated = (ApResourcesAvailabilityTimeEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}