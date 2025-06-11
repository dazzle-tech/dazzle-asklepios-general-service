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
import com.asklepios.backend_service.model.generated.pojo.ApRoom;
import com.asklepios.backend_service.model.generated.entity.ApRoomEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApRoomDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApRoom getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_room where key = '"+key+"'");) {
ApRoom record = new ApRoom();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setName(rs.getString("name"));
record.setFloor(rs.getString("floor"));
record.setLocationDetails(rs.getString("location_details"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApRoom record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_room set key = ?, facility_key = ?, department_key = ?, name = ?, floor = ?, location_details = ?, type_lkey = ?, gender_lkey = ?, is_valid = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getDepartmentKey());
ps.setString(4, record.getName());
ps.setString(5, record.getFloor());
ps.setString(6, record.getLocationDetails());
ps.setString(7, record.getTypeLkey());
ps.setString(8, record.getGenderLkey());
ps.setBoolean(9, record.getIsValid());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApRoom record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_room set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApRoom> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_room where "+ where);) {
List<ApRoom> list = new ArrayList<ApRoom>();
while(rs.next()){
ApRoom record = new ApRoom();
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setName(rs.getString("name"));
record.setFloor(rs.getString("floor"));
record.setLocationDetails(rs.getString("location_details"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApRoom record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_room values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getDepartmentKey());
ps.setString(4, record.getName());
ps.setString(5, record.getFloor());
ps.setString(6, record.getLocationDetails());
ps.setString(7, record.getTypeLkey());
ps.setString(8, record.getGenderLkey());
ps.setBoolean(9, record.getIsValid());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApRoomEntity entity, String lang) {
        Class<?> myClass = ApRoomEntity.class;
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
public void translateObject(ApRoomEntity entity, String lang) {
        ApRoomEntity translated = (ApRoomEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}