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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTestsSamples;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticOrderTestsSamplesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticOrderTestsSamplesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticOrderTestsSamples getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests_samples where key = '"+key+"'");) {
ApDiagnosticOrderTestsSamples record = new ApDiagnosticOrderTestsSamples();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOrderKey(rs.getString("order_key"));
record.setTestKey(rs.getString("test_key"));
record.setNotes(rs.getString("notes"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setQuantity(rs.getBigDecimal("quantity"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setSampleCollectedAt(rs.getBigDecimal("sample_collected_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticOrderTestsSamples record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests_samples set key = ?, order_key = ?, test_key = ?, notes = ?, unit_lkey = ?, quantity = ?, created_by = ?, updated_by = ?, deleted_by = ?, sample_collected_at = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOrderKey());
ps.setString(3, record.getTestKey());
ps.setString(4, record.getNotes());
ps.setString(5, record.getUnitLkey());
ps.setBigDecimal(6, record.getQuantity());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getSampleCollectedAt());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticOrderTestsSamples record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests_samples set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticOrderTestsSamples> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests_samples where "+ where);) {
List<ApDiagnosticOrderTestsSamples> list = new ArrayList<ApDiagnosticOrderTestsSamples>();
while(rs.next()){
ApDiagnosticOrderTestsSamples record = new ApDiagnosticOrderTestsSamples();
record.setKey(rs.getString("key"));
record.setOrderKey(rs.getString("order_key"));
record.setTestKey(rs.getString("test_key"));
record.setNotes(rs.getString("notes"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setQuantity(rs.getBigDecimal("quantity"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setSampleCollectedAt(rs.getBigDecimal("sample_collected_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticOrderTestsSamples record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_order_tests_samples values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOrderKey());
ps.setString(3, record.getTestKey());
ps.setString(4, record.getNotes());
ps.setString(5, record.getUnitLkey());
ps.setBigDecimal(6, record.getQuantity());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getSampleCollectedAt());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticOrderTestsSamplesEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticOrderTestsSamplesEntity.class;
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
public void translateObject(ApDiagnosticOrderTestsSamplesEntity entity, String lang) {
        ApDiagnosticOrderTestsSamplesEntity translated = (ApDiagnosticOrderTestsSamplesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}