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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestNormalRangeLov;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestNormalRangeLovEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestNormalRangeLovDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTestNormalRangeLov getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_normal_range_lov where key = '"+key+"'");) {
ApDiagnosticTestNormalRangeLov record = new ApDiagnosticTestNormalRangeLov();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setNormalRangeKey(rs.getString("normal_range_key"));
record.setLovLkey(rs.getString("lov_lkey"));
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
public void updateRecord(ApDiagnosticTestNormalRangeLov record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_normal_range_lov set key = ?, test_key = ?, normal_range_key = ?, lov_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTestKey());
ps.setString(3, record.getNormalRangeKey());
ps.setString(4, record.getLovLkey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTestNormalRangeLov record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_normal_range_lov set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTestNormalRangeLov> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_normal_range_lov where "+ where);) {
List<ApDiagnosticTestNormalRangeLov> list = new ArrayList<ApDiagnosticTestNormalRangeLov>();
while(rs.next()){
ApDiagnosticTestNormalRangeLov record = new ApDiagnosticTestNormalRangeLov();
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setNormalRangeKey(rs.getString("normal_range_key"));
record.setLovLkey(rs.getString("lov_lkey"));
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
public String saveRecord(ApDiagnosticTestNormalRangeLov record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test_normal_range_lov values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTestKey());
ps.setString(3, record.getNormalRangeKey());
ps.setString(4, record.getLovLkey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestNormalRangeLovEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestNormalRangeLovEntity.class;
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
public void translateObject(ApDiagnosticTestNormalRangeLovEntity entity, String lang) {
        ApDiagnosticTestNormalRangeLovEntity translated = (ApDiagnosticTestNormalRangeLovEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}