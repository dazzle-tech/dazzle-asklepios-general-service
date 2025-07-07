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
import com.asklepios.backend_service.model.generated.pojo.ApOperationRequests;
import com.asklepios.backend_service.model.generated.entity.ApOperationRequestsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationRequestsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationRequests getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_requests where key = '"+key+"'");) {
ApOperationRequests record = new ApOperationRequests();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setOperationKey(rs.getString("operation_key"));
record.setOperationTypeLkey(rs.getString("operation_type_lkey"));
record.setOperationLevelLkey(rs.getString("operation_level_lkey"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setDiagnosisKey(rs.getString("diagnosis_key"));
record.setRequestStatus(rs.getString("request_status"));
record.setBodyPartLkey(rs.getString("body_part_lkey"));
record.setSideOfProcedureLkey(rs.getString("side_of_procedure_lkey"));
record.setPlannedAnesthesiaTypeLkey(rs.getString("planned_anesthesia_type_lkey"));
record.setNeedBloodProducts(rs.getBoolean("need_blood_products"));
record.setImplantOrDeviceExpected(rs.getBoolean("implant_or_device_expected"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setCancelledBy(rs.getString("cancelled_by"));
record.setCancelledAt(rs.getBigDecimal("cancelled_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setOperationDateTime(rs.getBigDecimal("operation_date_time"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setSubmitedAt(rs.getBigDecimal("submited_at"));
record.setSubmitedBy(rs.getString("submited_by"));
record.setOperationStatusLkey(rs.getString("operation_status_lkey"));
record.setStartedAt(rs.getBigDecimal("started_at"));
record.setStartedBy(rs.getString("started_by"));
record.setIncreaseByMinutes(rs.getBigDecimal("increase_by_minutes"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationRequests record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_requests set key = ?, facility_key = ?, department_key = ?, operation_key = ?, operation_type_lkey = ?, operation_level_lkey = ?, priority_lkey = ?, diagnosis_key = ?, request_status = ?, body_part_lkey = ?, side_of_procedure_lkey = ?, planned_anesthesia_type_lkey = ?, need_blood_products = ?, implant_or_device_expected = ?, notes = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, cancelled_by = ?, cancelled_at = ?, deleted_by = ?, deleted_at = ?, is_valid = ?, operation_date_time = ?, status_lkey = ?, encounter_key = ?, patient_key = ?, submited_at = ?, submited_by = ?, operation_status_lkey = ?, started_at = ?, started_by = ?, increase_by_minutes = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getDepartmentKey());
ps.setString(4, record.getOperationKey());
ps.setString(5, record.getOperationTypeLkey());
ps.setString(6, record.getOperationLevelLkey());
ps.setString(7, record.getPriorityLkey());
ps.setString(8, record.getDiagnosisKey());
ps.setString(9, record.getRequestStatus());
ps.setString(10, record.getBodyPartLkey());
ps.setString(11, record.getSideOfProcedureLkey());
ps.setString(12, record.getPlannedAnesthesiaTypeLkey());
ps.setBoolean(13, record.getNeedBloodProducts());
ps.setBoolean(14, record.getImplantOrDeviceExpected());
ps.setString(15, record.getNotes());
ps.setString(16, record.getCreatedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setString(18, record.getUpdatedBy());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setString(20, record.getCancelledBy());
ps.setBigDecimal(21, record.getCancelledAt());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.setBigDecimal(25, record.getOperationDateTime());
ps.setString(26, record.getStatusLkey());
ps.setString(27, record.getEncounterKey());
ps.setString(28, record.getPatientKey());
ps.setBigDecimal(29, record.getSubmitedAt());
ps.setString(30, record.getSubmitedBy());
ps.setString(31, record.getOperationStatusLkey());
ps.setBigDecimal(32, record.getStartedAt());
ps.setString(33, record.getStartedBy());
ps.setBigDecimal(34, record.getIncreaseByMinutes());
ps.setString(35, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationRequests record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_requests set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationRequests> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_requests where "+ where);) {
List<ApOperationRequests> list = new ArrayList<ApOperationRequests>();
while(rs.next()){
ApOperationRequests record = new ApOperationRequests();
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setOperationKey(rs.getString("operation_key"));
record.setOperationTypeLkey(rs.getString("operation_type_lkey"));
record.setOperationLevelLkey(rs.getString("operation_level_lkey"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setDiagnosisKey(rs.getString("diagnosis_key"));
record.setRequestStatus(rs.getString("request_status"));
record.setBodyPartLkey(rs.getString("body_part_lkey"));
record.setSideOfProcedureLkey(rs.getString("side_of_procedure_lkey"));
record.setPlannedAnesthesiaTypeLkey(rs.getString("planned_anesthesia_type_lkey"));
record.setNeedBloodProducts(rs.getBoolean("need_blood_products"));
record.setImplantOrDeviceExpected(rs.getBoolean("implant_or_device_expected"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setCancelledBy(rs.getString("cancelled_by"));
record.setCancelledAt(rs.getBigDecimal("cancelled_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setOperationDateTime(rs.getBigDecimal("operation_date_time"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setSubmitedAt(rs.getBigDecimal("submited_at"));
record.setSubmitedBy(rs.getString("submited_by"));
record.setOperationStatusLkey(rs.getString("operation_status_lkey"));
record.setStartedAt(rs.getBigDecimal("started_at"));
record.setStartedBy(rs.getString("started_by"));
record.setIncreaseByMinutes(rs.getBigDecimal("increase_by_minutes"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationRequests record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_requests values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getDepartmentKey());
ps.setString(4, record.getOperationKey());
ps.setString(5, record.getOperationTypeLkey());
ps.setString(6, record.getOperationLevelLkey());
ps.setString(7, record.getPriorityLkey());
ps.setString(8, record.getDiagnosisKey());
ps.setString(9, record.getRequestStatus());
ps.setString(10, record.getBodyPartLkey());
ps.setString(11, record.getSideOfProcedureLkey());
ps.setString(12, record.getPlannedAnesthesiaTypeLkey());
ps.setBoolean(13, record.getNeedBloodProducts());
ps.setBoolean(14, record.getImplantOrDeviceExpected());
ps.setString(15, record.getNotes());
ps.setString(16, record.getCreatedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setString(18, record.getUpdatedBy());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setString(20, record.getCancelledBy());
ps.setBigDecimal(21, record.getCancelledAt());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.setBigDecimal(25, record.getOperationDateTime());
ps.setString(26, record.getStatusLkey());
ps.setString(27, record.getEncounterKey());
ps.setString(28, record.getPatientKey());
ps.setBigDecimal(29, record.getSubmitedAt());
ps.setString(30, record.getSubmitedBy());
ps.setString(31, record.getOperationStatusLkey());
ps.setBigDecimal(32, record.getStartedAt());
ps.setString(33, record.getStartedBy());
ps.setBigDecimal(34, record.getIncreaseByMinutes());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationRequestsEntity entity, String lang) {
        Class<?> myClass = ApOperationRequestsEntity.class;
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
public void translateObject(ApOperationRequestsEntity entity, String lang) {
        ApOperationRequestsEntity translated = (ApOperationRequestsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}