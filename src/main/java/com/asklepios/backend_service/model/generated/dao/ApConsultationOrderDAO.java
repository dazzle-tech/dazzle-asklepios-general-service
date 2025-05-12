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
import com.asklepios.backend_service.model.generated.pojo.ApConsultationOrder;
import com.asklepios.backend_service.model.generated.entity.ApConsultationOrderEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApConsultationOrderDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApConsultationOrder getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_consultation_order where key = '"+key+"'");) {
ApConsultationOrder record = new ApConsultationOrder();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setConsultantSpecialtyLkey(rs.getString("consultant_specialty_lkey"));
record.setCityLkey(rs.getString("city_lkey"));
record.setPreferredConsultantKey(rs.getString("preferred_consultant_key"));
record.setConsultationMethodLkey(rs.getString("consultation_method_lkey"));
record.setConsultationTypeLkey(rs.getString("consultation_type_lkey"));
record.setConsultationContent(rs.getString("consultation_content"));
record.setNotes(rs.getString("notes"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setViewResponse(rs.getString("view_response"));
record.setResposeStatusLkey(rs.getString("respose_status_lkey"));
record.setSubmissionDate(rs.getBigDecimal("submission_date"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCancellationReason(rs.getString("cancellation_reason"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApConsultationOrder record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_consultation_order set key = ?, patient_key = ?, visit_key = ?, consultant_specialty_lkey = ?, city_lkey = ?, preferred_consultant_key = ?, consultation_method_lkey = ?, consultation_type_lkey = ?, consultation_content = ?, notes = ?, status_lkey = ?, view_response = ?, respose_status_lkey = ?, submission_date = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, cancellation_reason = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getConsultantSpecialtyLkey());
ps.setString(5, record.getCityLkey());
ps.setString(6, record.getPreferredConsultantKey());
ps.setString(7, record.getConsultationMethodLkey());
ps.setString(8, record.getConsultationTypeLkey());
ps.setString(9, record.getConsultationContent());
ps.setString(10, record.getNotes());
ps.setString(11, record.getStatusLkey());
ps.setString(12, record.getViewResponse());
ps.setString(13, record.getResposeStatusLkey());
ps.setBigDecimal(14, record.getSubmissionDate());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setString(22, record.getCancellationReason());
ps.setString(23, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApConsultationOrder record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_consultation_order set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApConsultationOrder> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_consultation_order where "+ where);) {
List<ApConsultationOrder> list = new ArrayList<ApConsultationOrder>();
while(rs.next()){
ApConsultationOrder record = new ApConsultationOrder();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setConsultantSpecialtyLkey(rs.getString("consultant_specialty_lkey"));
record.setCityLkey(rs.getString("city_lkey"));
record.setPreferredConsultantKey(rs.getString("preferred_consultant_key"));
record.setConsultationMethodLkey(rs.getString("consultation_method_lkey"));
record.setConsultationTypeLkey(rs.getString("consultation_type_lkey"));
record.setConsultationContent(rs.getString("consultation_content"));
record.setNotes(rs.getString("notes"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setViewResponse(rs.getString("view_response"));
record.setResposeStatusLkey(rs.getString("respose_status_lkey"));
record.setSubmissionDate(rs.getBigDecimal("submission_date"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCancellationReason(rs.getString("cancellation_reason"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApConsultationOrder record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_consultation_order values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getConsultantSpecialtyLkey());
ps.setString(5, record.getCityLkey());
ps.setString(6, record.getPreferredConsultantKey());
ps.setString(7, record.getConsultationMethodLkey());
ps.setString(8, record.getConsultationTypeLkey());
ps.setString(9, record.getConsultationContent());
ps.setString(10, record.getNotes());
ps.setString(11, record.getStatusLkey());
ps.setString(12, record.getViewResponse());
ps.setString(13, record.getResposeStatusLkey());
ps.setBigDecimal(14, record.getSubmissionDate());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setString(22, record.getCancellationReason());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApConsultationOrderEntity entity, String lang) {
        Class<?> myClass = ApConsultationOrderEntity.class;
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
public void translateObject(ApConsultationOrderEntity entity, String lang) {
        ApConsultationOrderEntity translated = (ApConsultationOrderEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}