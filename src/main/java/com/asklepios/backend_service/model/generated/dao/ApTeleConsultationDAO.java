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
import com.asklepios.backend_service.model.generated.pojo.ApTeleConsultation;
import com.asklepios.backend_service.model.generated.entity.ApTeleConsultationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApTeleConsultationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApTeleConsultation getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tele_consultation where id = '"+key+"'");) {
ApTeleConsultation record = new ApTeleConsultation();
if(rs.next()){
record.setId(rs.getString("id"));
record.setQuestionToConsultant(rs.getString("question_to_consultant"));
record.setConsultantFacilityId(rs.getString("consultant_facility_id"));
record.setConsultantDepartmentId(rs.getString("consultant_department_id"));
record.setSpecialtyLkey(rs.getString("specialty_lkey"));
record.setUrgencyLkey(rs.getString("urgency_lkey"));
record.setExpectedResponse(rs.getString("expected_response"));
record.setNotes(rs.getString("notes"));
record.setExpectedResponseTime(rs.getBigDecimal("expected_response_time"));
record.setStartedAt(rs.getBigDecimal("started_at"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setRejectedReason(rs.getString("rejected_reason"));
record.setPatientId(rs.getString("patient_id"));
record.setEncounterId(rs.getString("encounter_id"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setStartedBy(rs.getString("started_by"));
record.setRequestedAt(rs.getBigDecimal("requested_at"));
record.setRequestedBy(rs.getString("requested_by"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApTeleConsultation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tele_consultation set id = ?, question_to_consultant = ?, consultant_facility_id = ?, consultant_department_id = ?, specialty_lkey = ?, urgency_lkey = ?, expected_response = ?, notes = ?, expected_response_time = ?, started_at = ?, rejected_at = ?, status_lkey = ?, rejected_reason = ?, patient_id = ?, encounter_id = ?, rejected_by = ?, started_by = ?, requested_at = ?, requested_by = ? where id = ?");
) {

ps.setString(1, record.getId());
ps.setString(2, record.getQuestionToConsultant());
ps.setString(3, record.getConsultantFacilityId());
ps.setString(4, record.getConsultantDepartmentId());
ps.setString(5, record.getSpecialtyLkey());
ps.setString(6, record.getUrgencyLkey());
ps.setString(7, record.getExpectedResponse());
ps.setString(8, record.getNotes());
ps.setBigDecimal(9, record.getExpectedResponseTime());
ps.setBigDecimal(10, record.getStartedAt());
ps.setBigDecimal(11, record.getRejectedAt());
ps.setString(12, record.getStatusLkey());
ps.setString(13, record.getRejectedReason());
ps.setString(14, record.getPatientId());
ps.setString(15, record.getEncounterId());
ps.setString(16, record.getRejectedBy());
ps.setString(17, record.getStartedBy());
ps.setBigDecimal(18, record.getRequestedAt());
ps.setString(19, record.getRequestedBy());
ps.setString(20, record.getId());
ps.executeUpdate();
}
}
public void deleteRecord(ApTeleConsultation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tele_consultation set  deleted_at = '"+System.currentTimeMillis()+"' where id = ?");
) {
ps.setString(1, record.getId());
ps.executeUpdate();
}
}
public List<ApTeleConsultation> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tele_consultation where "+ where);) {
List<ApTeleConsultation> list = new ArrayList<ApTeleConsultation>();
while(rs.next()){
ApTeleConsultation record = new ApTeleConsultation();
record.setId(rs.getString("id"));
record.setQuestionToConsultant(rs.getString("question_to_consultant"));
record.setConsultantFacilityId(rs.getString("consultant_facility_id"));
record.setConsultantDepartmentId(rs.getString("consultant_department_id"));
record.setSpecialtyLkey(rs.getString("specialty_lkey"));
record.setUrgencyLkey(rs.getString("urgency_lkey"));
record.setExpectedResponse(rs.getString("expected_response"));
record.setNotes(rs.getString("notes"));
record.setExpectedResponseTime(rs.getBigDecimal("expected_response_time"));
record.setStartedAt(rs.getBigDecimal("started_at"));
record.setRejectedAt(rs.getBigDecimal("rejected_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setRejectedReason(rs.getString("rejected_reason"));
record.setPatientId(rs.getString("patient_id"));
record.setEncounterId(rs.getString("encounter_id"));
record.setRejectedBy(rs.getString("rejected_by"));
record.setStartedBy(rs.getString("started_by"));
record.setRequestedAt(rs.getBigDecimal("requested_at"));
record.setRequestedBy(rs.getString("requested_by"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApTeleConsultation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_tele_consultation values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getId() != null && !record.getId().isEmpty()) {updateRecord(record); return record.getId();}
String key = "" + System.nanoTime();
record.setId(key);

ps.setString(1, key);
ps.setString(2, record.getQuestionToConsultant());
ps.setString(3, record.getConsultantFacilityId());
ps.setString(4, record.getConsultantDepartmentId());
ps.setString(5, record.getSpecialtyLkey());
ps.setString(6, record.getUrgencyLkey());
ps.setString(7, record.getExpectedResponse());
ps.setString(8, record.getNotes());
ps.setBigDecimal(9, record.getExpectedResponseTime());
ps.setBigDecimal(10, record.getStartedAt());
ps.setBigDecimal(11, record.getRejectedAt());
ps.setString(12, record.getStatusLkey());
ps.setString(13, record.getRejectedReason());
ps.setString(14, record.getPatientId());
ps.setString(15, record.getEncounterId());
ps.setString(16, record.getRejectedBy());
ps.setString(17, record.getStartedBy());
ps.setBigDecimal(18, record.getRequestedAt());
ps.setString(19, record.getRequestedBy());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApTeleConsultationEntity entity, String lang) {
        Class<?> myClass = ApTeleConsultationEntity.class;
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
public void translateObject(ApTeleConsultationEntity entity, String lang) {
        ApTeleConsultationEntity translated = (ApTeleConsultationEntity) publicServices.getObjectTranslation(entity.getId(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}