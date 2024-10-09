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
import com.asklepios.backend_service.model.generated.pojo.ApToothAction;
import com.asklepios.backend_service.model.generated.entity.ApToothActionEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApToothActionDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApToothAction getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tooth_action where key = '"+key+"'");) {
ApToothAction record = new ApToothAction();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setToothKey(rs.getString("tooth_key"));
record.setActionKey(rs.getString("action_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setNote(rs.getString("note"));
record.setSurfaceLkey(rs.getString("surface_lkey"));
record.setExisting(rs.getBoolean("existing"));
record.setImageName(rs.getString("image_name"));
record.setToothNumber(rs.getString("tooth_number"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApToothAction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tooth_action set key = ?, tooth_key = ?, action_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, note = ?, surface_lkey = ?, existing = ?, image_name = ?, tooth_number = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getToothKey());
ps.setString(3, record.getActionKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setBoolean(10, record.getIsValid());
ps.setString(11, record.getNote());
ps.setString(12, record.getSurfaceLkey());
ps.setBoolean(13, record.getExisting());
ps.setString(14, record.getImageName());
ps.setString(15, record.getToothNumber());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApToothAction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tooth_action set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApToothAction> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tooth_action where "+ where);) {
List<ApToothAction> list = new ArrayList<ApToothAction>();
while(rs.next()){
ApToothAction record = new ApToothAction();
record.setKey(rs.getString("key"));
record.setToothKey(rs.getString("tooth_key"));
record.setActionKey(rs.getString("action_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setNote(rs.getString("note"));
record.setSurfaceLkey(rs.getString("surface_lkey"));
record.setExisting(rs.getBoolean("existing"));
record.setImageName(rs.getString("image_name"));
record.setToothNumber(rs.getString("tooth_number"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApToothAction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_tooth_action values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getToothKey());
ps.setString(3, record.getActionKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setBoolean(10, record.getIsValid());
ps.setString(11, record.getNote());
ps.setString(12, record.getSurfaceLkey());
ps.setBoolean(13, record.getExisting());
ps.setString(14, record.getImageName());
ps.setString(15, record.getToothNumber());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApToothActionEntity entity, String lang) {
        Class<?> myClass = ApToothActionEntity.class;
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
public void translateObject(ApToothActionEntity entity, String lang) {
        ApToothActionEntity translated = (ApToothActionEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}