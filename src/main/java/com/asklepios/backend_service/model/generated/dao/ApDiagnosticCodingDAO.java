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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticCoding;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticCodingEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticCodingDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticCoding getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_coding where key = '"+key+"'");) {
ApDiagnosticCoding record = new ApDiagnosticCoding();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setDiagnosticsKey(rs.getString("diagnostics_key"));
record.setCodeTypeLkey(rs.getString("code_type_lkey"));
record.setInternationalCodeKey(rs.getString("international_code_key"));
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
public void updateRecord(ApDiagnosticCoding record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_coding set key = ?, diagnostics_key = ?, code_type_lkey = ?, international_code_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getDiagnosticsKey());
ps.setString(3, record.getCodeTypeLkey());
ps.setString(4, record.getInternationalCodeKey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setString(11, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticCoding record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_coding set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticCoding> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_coding where "+ where);) {
List<ApDiagnosticCoding> list = new ArrayList<ApDiagnosticCoding>();
while(rs.next()){
ApDiagnosticCoding record = new ApDiagnosticCoding();
record.setKey(rs.getString("key"));
record.setDiagnosticsKey(rs.getString("diagnostics_key"));
record.setCodeTypeLkey(rs.getString("code_type_lkey"));
record.setInternationalCodeKey(rs.getString("international_code_key"));
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
public String saveRecord(ApDiagnosticCoding record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_coding values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getDiagnosticsKey());
ps.setString(3, record.getCodeTypeLkey());
ps.setString(4, record.getInternationalCodeKey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticCodingEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticCodingEntity.class;
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
public void translateObject(ApDiagnosticCodingEntity entity, String lang) {
        ApDiagnosticCodingEntity translated = (ApDiagnosticCodingEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}