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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTestsRadReport;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticOrderTestsRadReportEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticOrderTestsRadReportDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticOrderTestsRadReport getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests_rad_report where key = '"+key+"'");) {
ApDiagnosticOrderTestsRadReport record = new ApDiagnosticOrderTestsRadReport();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setOrderKey(rs.getString("order_key"));
record.setMedicalTestKey(rs.getString("medical_test_key"));
record.setOrderTestKey(rs.getString("order_test_key"));
record.setReportValue(rs.getString("report_value"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setOrderTypeLkey(rs.getString("order_type_lkey"));
record.setApprovedAt(rs.getBigDecimal("approved_at"));
record.setApprovedBy(rs.getString("approved_by"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setRejectedReason(rs.getString("rejected_reason"));
record.setReviewAt(rs.getBigDecimal("review_at"));
record.setReviewBy(rs.getString("review_by"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticOrderTestsRadReport record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests_rad_report set key = ?, patient_key = ?, visit_key = ?, status_lkey = ?, order_key = ?, medical_test_key = ?, order_test_key = ?, report_value = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, order_type_lkey = ?, approved_at = ?, approved_by = ?, rejected_at = ?, rejected_by = ?, rejected_reason = ?, review_at = ?, review_by = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getOrderKey());
ps.setString(6, record.getMedicalTestKey());
ps.setString(7, record.getOrderTestKey());
ps.setString(8, record.getReportValue());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsValid());
ps.setString(16, record.getOrderTypeLkey());
ps.setBigDecimal(17, record.getApprovedAt());
ps.setString(18, record.getApprovedBy());
ps.setBigDecimal(19, record.getRejectedAt());
ps.setString(20, record.getRejectedBy());
ps.setString(21, record.getRejectedReason());
ps.setBigDecimal(22, record.getReviewAt());
ps.setString(23, record.getReviewBy());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticOrderTestsRadReport record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests_rad_report set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticOrderTestsRadReport> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests_rad_report where "+ where);) {
List<ApDiagnosticOrderTestsRadReport> list = new ArrayList<ApDiagnosticOrderTestsRadReport>();
while(rs.next()){
ApDiagnosticOrderTestsRadReport record = new ApDiagnosticOrderTestsRadReport();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setOrderKey(rs.getString("order_key"));
record.setMedicalTestKey(rs.getString("medical_test_key"));
record.setOrderTestKey(rs.getString("order_test_key"));
record.setReportValue(rs.getString("report_value"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setOrderTypeLkey(rs.getString("order_type_lkey"));
record.setApprovedAt(rs.getBigDecimal("approved_at"));
record.setApprovedBy(rs.getString("approved_by"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setRejectedReason(rs.getString("rejected_reason"));
record.setReviewAt(rs.getBigDecimal("review_at"));
record.setReviewBy(rs.getString("review_by"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticOrderTestsRadReport record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_order_tests_rad_report values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
ps.setString(6, record.getMedicalTestKey());
ps.setString(7, record.getOrderTestKey());
ps.setString(8, record.getReportValue());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsValid());
ps.setString(16, record.getOrderTypeLkey());
ps.setBigDecimal(17, record.getApprovedAt());
ps.setString(18, record.getApprovedBy());
ps.setBigDecimal(19, record.getRejectedAt());
ps.setString(20, record.getRejectedBy());
ps.setString(21, record.getRejectedReason());
ps.setBigDecimal(22, record.getReviewAt());
ps.setString(23, record.getReviewBy());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticOrderTestsRadReportEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticOrderTestsRadReportEntity.class;
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
public void translateObject(ApDiagnosticOrderTestsRadReportEntity entity, String lang) {
        ApDiagnosticOrderTestsRadReportEntity translated = (ApDiagnosticOrderTestsRadReportEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}