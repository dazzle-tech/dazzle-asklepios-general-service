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
import com.asklepios.backend_service.model.generated.pojo.ApResourceAvailabilitySlice;
import com.asklepios.backend_service.model.generated.entity.ApResourceAvailabilitySliceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApResourceAvailabilitySliceDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApResourceAvailabilitySlice getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_resource_availability_slice where key = '"+key+"'");) {
ApResourceAvailabilitySlice record = new ApResourceAvailabilitySlice();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setResourceKey(rs.getString("resource_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setDayOfWeek(rs.getString("day_of_week"));
record.setStartTimeMinutes(rs.getString("start_time_minutes"));
record.setEndTimeMinutes(rs.getString("end_time_minutes"));
record.setSliceDurationMinutes(rs.getString("slice_duration_minutes"));
record.setIsbocked(rs.getString("isbocked"));
record.setIsbreak(rs.getBoolean("isbreak"));
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
public void updateRecord(ApResourceAvailabilitySlice record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_resource_availability_slice set key = ?, resource_key = ?, facility_key = ?, department_key = ?, day_of_week = ?, start_time_minutes = ?, end_time_minutes = ?, slice_duration_minutes = ?, isbocked = ?, isbreak = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getResourceKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getDepartmentKey());
ps.setString(5, record.getDayOfWeek());
ps.setString(6, record.getStartTimeMinutes());
ps.setString(7, record.getEndTimeMinutes());
ps.setString(8, record.getSliceDurationMinutes());
ps.setString(9, record.getIsbocked());
ps.setBoolean(10, record.getIsbreak());
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
public void deleteRecord(ApResourceAvailabilitySlice record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_resource_availability_slice set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApResourceAvailabilitySlice> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_resource_availability_slice where "+ where);) {
List<ApResourceAvailabilitySlice> list = new ArrayList<ApResourceAvailabilitySlice>();
while(rs.next()){
ApResourceAvailabilitySlice record = new ApResourceAvailabilitySlice();
record.setKey(rs.getString("key"));
record.setResourceKey(rs.getString("resource_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setDayOfWeek(rs.getString("day_of_week"));
record.setStartTimeMinutes(rs.getString("start_time_minutes"));
record.setEndTimeMinutes(rs.getString("end_time_minutes"));
record.setSliceDurationMinutes(rs.getString("slice_duration_minutes"));
record.setIsbocked(rs.getString("isbocked"));
record.setIsbreak(rs.getBoolean("isbreak"));
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
public String saveRecord(ApResourceAvailabilitySlice record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_resource_availability_slice values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getResourceKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getDepartmentKey());
ps.setString(5, record.getDayOfWeek());
ps.setString(6, record.getStartTimeMinutes());
ps.setString(7, record.getEndTimeMinutes());
ps.setString(8, record.getSliceDurationMinutes());
ps.setString(9, record.getIsbocked());
ps.setBoolean(10, record.getIsbreak());
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
public void populateLovFields(ApResourceAvailabilitySliceEntity entity, String lang) {
        Class<?> myClass = ApResourceAvailabilitySliceEntity.class;
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
public void translateObject(ApResourceAvailabilitySliceEntity entity, String lang) {
        ApResourceAvailabilitySliceEntity translated = (ApResourceAvailabilitySliceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}