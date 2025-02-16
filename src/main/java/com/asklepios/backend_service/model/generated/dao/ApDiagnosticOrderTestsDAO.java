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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTests;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticOrderTestsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticOrderTestsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticOrderTests getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests where key = '"+key+"'");) {
ApDiagnosticOrderTests record = new ApDiagnosticOrderTests();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setOrderKey(rs.getString("order_key"));
record.setTestKey(rs.getString("test_key"));
record.setReceivedLabLkey(rs.getString("received_lab_lkey"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setProcessingStatusLkey(rs.getString("processing_status_lkey"));
record.setSubmitDate(rs.getBigDecimal("submit_date"));
record.setOrderTypeLkey(rs.getString("order_type_lkey"));
record.setAcceptedAt(rs.getBigDecimal("accepted_at"));
record.setAcceptedBy(rs.getString("accepted_by"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setRejectedReason(rs.getString("rejected_reason"));

} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticOrderTests record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests set key = ?, patient_key = ?, visit_key = ?, status_lkey = ?, order_key = ?, test_key = ?, received_lab_lkey = ?, reason_lkey = ?, priority_lkey = ?, notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, processing_status_lkey = ?, submit_date = ?, order_type_lkey = ?, accepted_at = ?, accepted_by = ?, rejected_at = ?, rejected_by = ?, rejected_reason = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getOrderKey());
ps.setString(6, record.getTestKey());
ps.setString(7, record.getReceivedLabLkey());
ps.setString(8, record.getReasonLkey());
ps.setString(9, record.getPriorityLkey());
ps.setString(10, record.getNotes());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.setString(18, record.getProcessingStatusLkey());
ps.setBigDecimal(19, record.getSubmitDate());
ps.setString(20, record.getOrderTypeLkey());
ps.setBigDecimal(21, record.getAcceptedAt());
ps.setString(22, record.getAcceptedBy());
ps.setBigDecimal(23, record.getRejectedAt());
ps.setString(24, record.getRejectedBy());
ps.setString(25, record.getRejectedReason());
ps.setString(26, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticOrderTests record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticOrderTests> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests where "+ where);) {
List<ApDiagnosticOrderTests> list = new ArrayList<ApDiagnosticOrderTests>();
while(rs.next()){
ApDiagnosticOrderTests record = new ApDiagnosticOrderTests();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setOrderKey(rs.getString("order_key"));
record.setTestKey(rs.getString("test_key"));
record.setReceivedLabLkey(rs.getString("received_lab_lkey"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setProcessingStatusLkey(rs.getString("processing_status_lkey"));
record.setSubmitDate(rs.getBigDecimal("submit_date"));
record.setOrderTypeLkey(rs.getString("order_type_lkey"));
record.setAcceptedAt(rs.getBigDecimal("accepted_at"));
record.setAcceptedBy(rs.getString("accepted_by"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setRejectedReason(rs.getString("rejected_reason"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticOrderTests record) throws SQLException {
try (
Connection con = DS.getConnection();

PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_order_tests values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getOrderKey());
ps.setString(6, record.getTestKey());
ps.setString(7, record.getReceivedLabLkey());
ps.setString(8, record.getReasonLkey());
ps.setString(9, record.getPriorityLkey());
ps.setString(10, record.getNotes());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.setString(18, record.getProcessingStatusLkey());
ps.setBigDecimal(19, record.getSubmitDate());
ps.setString(20, record.getOrderTypeLkey());
ps.setBigDecimal(21, record.getAcceptedAt());
ps.setString(22, record.getAcceptedBy());
ps.setBigDecimal(23, record.getRejectedAt());
ps.setString(24, record.getRejectedBy());
ps.setString(25, record.getRejectedReason());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticOrderTestsEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticOrderTestsEntity.class;
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
public void translateObject(ApDiagnosticOrderTestsEntity entity, String lang) {
        ApDiagnosticOrderTestsEntity translated = (ApDiagnosticOrderTestsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}