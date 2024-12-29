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
import com.asklepios.backend_service.model.generated.pojo.ApAppointment;
import com.asklepios.backend_service.model.generated.entity.ApAppointmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApAppointmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApAppointment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_appointment where key = '"+key+"'");) {
ApAppointment record = new ApAppointment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setResourceTypeLkey(rs.getString("resource_type_lkey"));
record.setResourceKey(rs.getString("resource_key"));
record.setVisitTypeLkey(rs.getString("visit_type_lkey"));
record.setDurationLkey(rs.getString("duration_lkey"));
record.setAppointmentStart(rs.getString("appointment_start"));
record.setAppointmentEnd(rs.getString("appointment_end"));
record.setInstructions(rs.getString("instructions"));
record.setNotes(rs.getString("notes"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setIsReminder(rs.getString("is_reminder"));
record.setReminderLkey(rs.getString("reminder_lkey"));
record.setConsentForm(rs.getString("consent_form"));
record.setReferingPhysicianLkey(rs.getString("refering_physician_lkey"));
record.setExternalPhysician(rs.getString("external_physician"));
record.setProcedureLevelLkey(rs.getString("procedure_level_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setResourceLkey(rs.getString("resource_lkey"));
record.setInstructionsLkey(rs.getString("instructions_lkey"));
record.setAppointmentStatus(rs.getString("appointment_status"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setReasonValue(rs.getString("reason_value"));
record.setOtherReason(rs.getString("other_reason"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApAppointment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_appointment set key = ?, patient_key = ?, facility_key = ?, resource_type_lkey = ?, resource_key = ?, visit_type_lkey = ?, duration_lkey = ?, appointment_start = ?, appointment_end = ?, instructions = ?, notes = ?, priority_lkey = ?, is_reminder = ?, reminder_lkey = ?, consent_form = ?, refering_physician_lkey = ?, external_physician = ?, procedure_level_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, resource_lkey = ?, instructions_lkey = ?, appointment_status = ?, reason_lkey = ?, reason_value = ?, other_reason = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getResourceTypeLkey());
ps.setString(5, record.getResourceKey());
ps.setString(6, record.getVisitTypeLkey());
ps.setString(7, record.getDurationLkey());
ps.setString(8, record.getAppointmentStart());
ps.setString(9, record.getAppointmentEnd());
ps.setString(10, record.getInstructions());
ps.setString(11, record.getNotes());
ps.setString(12, record.getPriorityLkey());
ps.setString(13, record.getIsReminder());
ps.setString(14, record.getReminderLkey());
ps.setString(15, record.getConsentForm());
ps.setString(16, record.getReferingPhysicianLkey());
ps.setString(17, record.getExternalPhysician());
ps.setString(18, record.getProcedureLevelLkey());
ps.setString(19, record.getCreatedBy());
ps.setString(20, record.getUpdatedBy());
ps.setString(21, record.getDeletedBy());
ps.setBigDecimal(22, record.getCreatedAt());
ps.setBigDecimal(23, record.getUpdatedAt());
ps.setBigDecimal(24, record.getDeletedAt());
ps.setBoolean(25, record.getIsValid());
ps.setString(26, record.getResourceLkey());
ps.setString(27, record.getInstructionsLkey());
ps.setString(28, record.getAppointmentStatus());
ps.setString(29, record.getReasonLkey());
ps.setString(30, record.getReasonValue());
ps.setString(31, record.getOtherReason());
ps.setString(32, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApAppointment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_appointment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApAppointment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_appointment where "+ where);) {
List<ApAppointment> list = new ArrayList<ApAppointment>();
while(rs.next()){
ApAppointment record = new ApAppointment();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setResourceTypeLkey(rs.getString("resource_type_lkey"));
record.setResourceKey(rs.getString("resource_key"));
record.setVisitTypeLkey(rs.getString("visit_type_lkey"));
record.setDurationLkey(rs.getString("duration_lkey"));
record.setAppointmentStart(rs.getString("appointment_start"));
record.setAppointmentEnd(rs.getString("appointment_end"));
record.setInstructions(rs.getString("instructions"));
record.setNotes(rs.getString("notes"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setIsReminder(rs.getString("is_reminder"));
record.setReminderLkey(rs.getString("reminder_lkey"));
record.setConsentForm(rs.getString("consent_form"));
record.setReferingPhysicianLkey(rs.getString("refering_physician_lkey"));
record.setExternalPhysician(rs.getString("external_physician"));
record.setProcedureLevelLkey(rs.getString("procedure_level_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setResourceLkey(rs.getString("resource_lkey"));
record.setInstructionsLkey(rs.getString("instructions_lkey"));
record.setAppointmentStatus(rs.getString("appointment_status"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setReasonValue(rs.getString("reason_value"));
record.setOtherReason(rs.getString("other_reason"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApAppointment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_appointment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getResourceTypeLkey());
ps.setString(5, record.getResourceKey());
ps.setString(6, record.getVisitTypeLkey());
ps.setString(7, record.getDurationLkey());
ps.setString(8, record.getAppointmentStart());
ps.setString(9, record.getAppointmentEnd());
ps.setString(10, record.getInstructions());
ps.setString(11, record.getNotes());
ps.setString(12, record.getPriorityLkey());
ps.setString(13, record.getIsReminder());
ps.setString(14, record.getReminderLkey());
ps.setString(15, record.getConsentForm());
ps.setString(16, record.getReferingPhysicianLkey());
ps.setString(17, record.getExternalPhysician());
ps.setString(18, record.getProcedureLevelLkey());
ps.setString(19, record.getCreatedBy());
ps.setString(20, record.getUpdatedBy());
ps.setString(21, record.getDeletedBy());
ps.setBigDecimal(22, record.getCreatedAt());
ps.setBigDecimal(23, record.getUpdatedAt());
ps.setBigDecimal(24, record.getDeletedAt());
ps.setBoolean(25, record.getIsValid());
ps.setString(26, record.getResourceLkey());
ps.setString(27, record.getInstructionsLkey());
ps.setString(28, record.getAppointmentStatus());
ps.setString(29, record.getReasonLkey());
ps.setString(30, record.getReasonValue());
ps.setString(31, record.getOtherReason());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApAppointmentEntity entity, String lang) {
        Class<?> myClass = ApAppointmentEntity.class;
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
public void translateObject(ApAppointmentEntity entity, String lang) {
        ApAppointmentEntity translated = (ApAppointmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}