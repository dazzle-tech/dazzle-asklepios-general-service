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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestCatalogHeader;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestCatalogHeaderEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestCatalogHeaderDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTestCatalogHeader getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_catalog_header where key = '"+key+"'");) {
ApDiagnosticTestCatalogHeader record = new ApDiagnosticTestCatalogHeader();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setDescription(rs.getString("description"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setDepartmentKey(rs.getString("department_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTestKey(rs.getString("test_key"));
record.setCatalogKey(rs.getString("catalog_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticTestCatalogHeader record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_catalog_header set key = ?, description = ?, type_lkey = ?, department_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, test_key = ?, catalog_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getDescription());
ps.setString(3, record.getTypeLkey());
ps.setString(4, record.getDepartmentKey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getTestKey());
ps.setString(13, record.getCatalogKey());
ps.setString(14, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTestCatalogHeader record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_catalog_header set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTestCatalogHeader> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_catalog_header where "+ where);) {
List<ApDiagnosticTestCatalogHeader> list = new ArrayList<ApDiagnosticTestCatalogHeader>();
while(rs.next()){
ApDiagnosticTestCatalogHeader record = new ApDiagnosticTestCatalogHeader();
record.setKey(rs.getString("key"));
record.setDescription(rs.getString("description"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setDepartmentKey(rs.getString("department_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTestKey(rs.getString("test_key"));
record.setCatalogKey(rs.getString("catalog_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticTestCatalogHeader record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test_catalog_header values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getDescription());
ps.setString(3, record.getTypeLkey());
ps.setString(4, record.getDepartmentKey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getTestKey());
ps.setString(13, record.getCatalogKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestCatalogHeaderEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestCatalogHeaderEntity.class;
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
public void translateObject(ApDiagnosticTestCatalogHeaderEntity entity, String lang) {
        ApDiagnosticTestCatalogHeaderEntity translated = (ApDiagnosticTestCatalogHeaderEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}