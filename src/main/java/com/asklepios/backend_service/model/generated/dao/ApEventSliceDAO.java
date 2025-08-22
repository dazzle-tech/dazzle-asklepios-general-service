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
import com.asklepios.backend_service.model.generated.pojo.ApEventSlice;
import com.asklepios.backend_service.model.generated.entity.ApEventSliceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApEventSliceDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApEventSlice getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_event_slice where key = '"+key+"'");) {
ApEventSlice record = new ApEventSlice();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setSliceKey(rs.getString("slice_key"));
record.setEventType(rs.getString("event_type"));
record.setLinkedEventKey(rs.getString("linked_event_key"));
record.setDayOfWeek(rs.getString("day_of_week"));
record.setEventDate(rs.getDate("event_date"));
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
public void updateRecord(ApEventSlice record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_event_slice set key = ?, slice_key = ?, event_type = ?, linked_event_key = ?, day_of_week = ?, event_date = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getSliceKey());
ps.setString(3, record.getEventType());
ps.setString(4, record.getLinkedEventKey());
ps.setString(5, record.getDayOfWeek());
if (record.getEventDate() != null) ps.setDate(6, new java.sql.Date(record.getEventDate().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.setString(14, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApEventSlice record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_event_slice set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApEventSlice> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_event_slice where "+ where);) {
List<ApEventSlice> list = new ArrayList<ApEventSlice>();
while(rs.next()){
ApEventSlice record = new ApEventSlice();
record.setKey(rs.getString("key"));
record.setSliceKey(rs.getString("slice_key"));
record.setEventType(rs.getString("event_type"));
record.setLinkedEventKey(rs.getString("linked_event_key"));
record.setDayOfWeek(rs.getString("day_of_week"));
record.setEventDate(rs.getDate("event_date"));
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
public String saveRecord(ApEventSlice record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_event_slice values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getSliceKey());
ps.setString(3, record.getEventType());
ps.setString(4, record.getLinkedEventKey());
ps.setString(5, record.getDayOfWeek());
if (record.getEventDate() != null) ps.setDate(6, new java.sql.Date(record.getEventDate().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApEventSliceEntity entity, String lang) {
        Class<?> myClass = ApEventSliceEntity.class;
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
public void translateObject(ApEventSliceEntity entity, String lang) {
        ApEventSliceEntity translated = (ApEventSliceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}