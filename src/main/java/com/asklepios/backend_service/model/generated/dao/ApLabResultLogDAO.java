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
import com.asklepios.backend_service.model.generated.pojo.ApLabResultLog;
import com.asklepios.backend_service.model.generated.entity.ApLabResultLogEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApLabResultLogDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApLabResultLog getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_lab_result_log where key = '"+key+"'");) {
ApLabResultLog record = new ApLabResultLog();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setResultKey(rs.getString("result_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setResultValue(rs.getString("result_value"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApLabResultLog record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_lab_result_log set key = ?, result_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, result_value = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getResultKey());
ps.setString(3, record.getCreatedBy());
ps.setString(4, record.getUpdatedBy());
ps.setString(5, record.getDeletedBy());
ps.setBigDecimal(6, record.getCreatedAt());
ps.setBigDecimal(7, record.getUpdatedAt());
ps.setBigDecimal(8, record.getDeletedAt());
ps.setBoolean(9, record.getIsValid());
ps.setString(10, record.getResultValue());
ps.setString(11, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApLabResultLog record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_lab_result_log set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApLabResultLog> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_lab_result_log where "+ where);) {
List<ApLabResultLog> list = new ArrayList<ApLabResultLog>();
while(rs.next()){
ApLabResultLog record = new ApLabResultLog();
record.setKey(rs.getString("key"));
record.setResultKey(rs.getString("result_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setResultValue(rs.getString("result_value"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApLabResultLog record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_lab_result_log values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getResultKey());
ps.setString(3, record.getCreatedBy());
ps.setString(4, record.getUpdatedBy());
ps.setString(5, record.getDeletedBy());
ps.setBigDecimal(6, record.getCreatedAt());
ps.setBigDecimal(7, record.getUpdatedAt());
ps.setBigDecimal(8, record.getDeletedAt());
ps.setBoolean(9, record.getIsValid());
ps.setString(10, record.getResultValue());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApLabResultLogEntity entity, String lang) {
        Class<?> myClass = ApLabResultLogEntity.class;
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
public void translateObject(ApLabResultLogEntity entity, String lang) {
        ApLabResultLogEntity translated = (ApLabResultLogEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}