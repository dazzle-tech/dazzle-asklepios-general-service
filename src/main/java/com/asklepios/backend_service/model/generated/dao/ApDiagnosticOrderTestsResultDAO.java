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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTestsResult;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticOrderTestsResultEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticOrderTestsResultDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticOrderTestsResult getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests_result where key = '"+key+"'");) {
ApDiagnosticOrderTestsResult record = new ApDiagnosticOrderTestsResult();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setOrderKey(rs.getString("order_key"));
record.setMedicalTestKey(rs.getString("medical_test_key"));
record.setOrderTestKey(rs.getString("order_test_key"));
record.setNormalRangeKey(rs.getString("normal_range_key"));
record.setResultType(rs.getString("result_type"));
record.setResultLkey(rs.getString("result_lkey"));
record.setResultValueNumber(rs.getBigDecimal("result_value_number"));
record.setMarker(rs.getString("marker"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setProcessingStatusLkey(rs.getString("processing_status_lkey"));
record.setOrderTypeLkey(rs.getString("order_type_lkey"));
record.setApprovedAt(rs.getBigDecimal("approved_at"));
record.setApprovedBy(rs.getString("approved_by"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setRejectedReason(rs.getString("rejected_reason"));
record.setReviewAt(rs.getBigDecimal("review_at"));
record.setReviewBy(rs.getString("review_by"));
record.setResultText(rs.getString("result_text"));
record.setTestProfileKey(rs.getString("test_profile_key"));
record.setIsProfile(rs.getBoolean("is_profile"));
record.setNormalRangeValue(rs.getString("normal_range_value"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticOrderTestsResult record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests_result set key = ?, patient_key = ?, visit_key = ?, status_lkey = ?, order_key = ?, medical_test_key = ?, order_test_key = ?, normal_range_key = ?, result_type = ?, result_lkey = ?, result_value_number = ?, marker = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, processing_status_lkey = ?, order_type_lkey = ?, approved_at = ?, approved_by = ?, rejected_at = ?, rejected_by = ?, rejected_reason = ?, review_at = ?, review_by = ?, result_text = ?, test_profile_key = ?, is_profile = ?, normal_range_value = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getOrderKey());
ps.setString(6, record.getMedicalTestKey());
ps.setString(7, record.getOrderTestKey());
ps.setString(8, record.getNormalRangeKey());
ps.setString(9, record.getResultType());
ps.setString(10, record.getResultLkey());
ps.setBigDecimal(11, record.getResultValueNumber());
ps.setString(12, record.getMarker());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setBoolean(19, record.getIsValid());
ps.setString(20, record.getProcessingStatusLkey());
ps.setString(21, record.getOrderTypeLkey());
ps.setBigDecimal(22, record.getApprovedAt());
ps.setString(23, record.getApprovedBy());
ps.setBigDecimal(24, record.getRejectedAt());
ps.setString(25, record.getRejectedBy());
ps.setString(26, record.getRejectedReason());
ps.setBigDecimal(27, record.getReviewAt());
ps.setString(28, record.getReviewBy());
ps.setString(29, record.getResultText());
ps.setString(30, record.getTestProfileKey());
ps.setBoolean(31, record.getIsProfile());
ps.setString(32, record.getNormalRangeValue());
ps.setString(33, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticOrderTestsResult record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_order_tests_result set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticOrderTestsResult> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_order_tests_result where "+ where);) {
List<ApDiagnosticOrderTestsResult> list = new ArrayList<ApDiagnosticOrderTestsResult>();
while(rs.next()){
ApDiagnosticOrderTestsResult record = new ApDiagnosticOrderTestsResult();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setOrderKey(rs.getString("order_key"));
record.setMedicalTestKey(rs.getString("medical_test_key"));
record.setOrderTestKey(rs.getString("order_test_key"));
record.setNormalRangeKey(rs.getString("normal_range_key"));
record.setResultType(rs.getString("result_type"));
record.setResultLkey(rs.getString("result_lkey"));
record.setResultValueNumber(rs.getBigDecimal("result_value_number"));
record.setMarker(rs.getString("marker"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setProcessingStatusLkey(rs.getString("processing_status_lkey"));
record.setOrderTypeLkey(rs.getString("order_type_lkey"));
record.setApprovedAt(rs.getBigDecimal("approved_at"));
record.setApprovedBy(rs.getString("approved_by"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setRejectedReason(rs.getString("rejected_reason"));
record.setReviewAt(rs.getBigDecimal("review_at"));
record.setReviewBy(rs.getString("review_by"));
record.setResultText(rs.getString("result_text"));
record.setTestProfileKey(rs.getString("test_profile_key"));
record.setIsProfile(rs.getBoolean("is_profile"));
record.setNormalRangeValue(rs.getString("normal_range_value"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticOrderTestsResult record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_order_tests_result values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
ps.setString(8, record.getNormalRangeKey());
ps.setString(9, record.getResultType());
ps.setString(10, record.getResultLkey());
ps.setBigDecimal(11, record.getResultValueNumber());
ps.setString(12, record.getMarker());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setBoolean(19, record.getIsValid());
ps.setString(20, record.getProcessingStatusLkey());
ps.setString(21, record.getOrderTypeLkey());
ps.setBigDecimal(22, record.getApprovedAt());
ps.setString(23, record.getApprovedBy());
ps.setBigDecimal(24, record.getRejectedAt());
ps.setString(25, record.getRejectedBy());
ps.setString(26, record.getRejectedReason());
ps.setBigDecimal(27, record.getReviewAt());
ps.setString(28, record.getReviewBy());
ps.setString(29, record.getResultText());
ps.setString(30, record.getTestProfileKey());
ps.setBoolean(31, record.getIsProfile());
ps.setString(32, record.getNormalRangeValue());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticOrderTestsResultEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticOrderTestsResultEntity.class;
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
public void translateObject(ApDiagnosticOrderTestsResultEntity entity, String lang) {
        ApDiagnosticOrderTestsResultEntity translated = (ApDiagnosticOrderTestsResultEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}