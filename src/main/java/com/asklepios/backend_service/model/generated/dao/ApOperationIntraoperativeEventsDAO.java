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
import com.asklepios.backend_service.model.generated.pojo.ApOperationIntraoperativeEvents;
import com.asklepios.backend_service.model.generated.entity.ApOperationIntraoperativeEventsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationIntraoperativeEventsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationIntraoperativeEvents getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_intraoperative_events where key = '"+key+"'");) {
ApOperationIntraoperativeEvents record = new ApOperationIntraoperativeEvents();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setOperationNotes(rs.getString("operation_notes"));
record.setConversionOccurred(rs.getBoolean("conversion_occurred"));
record.setConversionType(rs.getBoolean("conversion_type"));
record.setConversionTypeNote(rs.getString("conversion_type_note"));
record.setIncisionType(rs.getString("incision_type"));
record.setEstimatedBloodLossMl(rs.getBigDecimal("estimated_blood_loss_ml"));
record.setSurgicalComplicationLkey(rs.getString("surgical_complication_lkey"));
record.setSurgicalComplicationNotes(rs.getString("surgical_complication_notes"));
record.setSpecimensTaken(rs.getString("specimens_taken"));
record.setSafetyPauseTaken(rs.getBoolean("safety_pause_taken"));
record.setFirstCountTime(rs.getBigDecimal("first_count_time"));
record.setFirstCountByKey(rs.getString("first_count_by_key"));
record.setSecondCountTime(rs.getBigDecimal("second_count_time"));
record.setSecondCountByKey(rs.getString("second_count_by_key"));
record.setFinalCountVerified(rs.getBoolean("final_count_verified"));
record.setCountDiscrepancy(rs.getBoolean("count_discrepancy"));
record.setCountDiscrepancyAction(rs.getString("count_discrepancy_action"));
record.setUnexpectedEventOccurred(rs.getBoolean("unexpected_event_occurred"));
record.setEventDescription(rs.getString("event_description"));
record.setTeamResponse(rs.getString("team_response"));
record.setEventOutcome(rs.getString("event_outcome"));
record.setComplicationSeverityLkey(rs.getString("complication_severity_lkey"));
record.setSkinClosureTime(rs.getBigDecimal("skin_closure_time"));
record.setSurgeryEndTime(rs.getBigDecimal("surgery_end_time"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
record.setUrineOutput(rs.getBigDecimal("urine_output"));
record.setActualOperationPerformed(rs.getString("actual_operation_performed"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationIntraoperativeEvents record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_intraoperative_events set key = ?, operation_request_key = ?, operation_notes = ?, conversion_occurred = ?, conversion_type = ?, conversion_type_note = ?, incision_type = ?, estimated_blood_loss_ml = ?, surgical_complication_lkey = ?, surgical_complication_notes = ?, specimens_taken = ?, safety_pause_taken = ?, first_count_time = ?, first_count_by_key = ?, second_count_time = ?, second_count_by_key = ?, final_count_verified = ?, count_discrepancy = ?, count_discrepancy_action = ?, unexpected_event_occurred = ?, event_description = ?, team_response = ?, event_outcome = ?, complication_severity_lkey = ?, skin_closure_time = ?, surgery_end_time = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ?, urine_output = ?, actual_operation_performed = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getOperationNotes());
ps.setBoolean(4, record.getConversionOccurred());
ps.setBoolean(5, record.getConversionType());
ps.setString(6, record.getConversionTypeNote());
ps.setString(7, record.getIncisionType());
ps.setBigDecimal(8, record.getEstimatedBloodLossMl());
ps.setString(9, record.getSurgicalComplicationLkey());
ps.setString(10, record.getSurgicalComplicationNotes());
ps.setString(11, record.getSpecimensTaken());
ps.setBoolean(12, record.getSafetyPauseTaken());
ps.setBigDecimal(13, record.getFirstCountTime());
ps.setString(14, record.getFirstCountByKey());
ps.setBigDecimal(15, record.getSecondCountTime());
ps.setString(16, record.getSecondCountByKey());
ps.setBoolean(17, record.getFinalCountVerified());
ps.setBoolean(18, record.getCountDiscrepancy());
ps.setString(19, record.getCountDiscrepancyAction());
ps.setBoolean(20, record.getUnexpectedEventOccurred());
ps.setString(21, record.getEventDescription());
ps.setString(22, record.getTeamResponse());
ps.setString(23, record.getEventOutcome());
ps.setString(24, record.getComplicationSeverityLkey());
ps.setBigDecimal(25, record.getSkinClosureTime());
ps.setBigDecimal(26, record.getSurgeryEndTime());
ps.setString(27, record.getCreatedBy());
ps.setBigDecimal(28, record.getCreatedAt());
ps.setString(29, record.getUpdatedBy());
ps.setBigDecimal(30, record.getUpdatedAt());
ps.setString(31, record.getDeletedBy());
ps.setBigDecimal(32, record.getDeletedAt());
ps.setBoolean(33, record.getIsvalid());
ps.setBigDecimal(34, record.getUrineOutput());
ps.setString(35, record.getActualOperationPerformed());
ps.setString(36, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationIntraoperativeEvents record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_intraoperative_events set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationIntraoperativeEvents> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_intraoperative_events where "+ where);) {
List<ApOperationIntraoperativeEvents> list = new ArrayList<ApOperationIntraoperativeEvents>();
while(rs.next()){
ApOperationIntraoperativeEvents record = new ApOperationIntraoperativeEvents();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setOperationNotes(rs.getString("operation_notes"));
record.setConversionOccurred(rs.getBoolean("conversion_occurred"));
record.setConversionType(rs.getBoolean("conversion_type"));
record.setConversionTypeNote(rs.getString("conversion_type_note"));
record.setIncisionType(rs.getString("incision_type"));
record.setEstimatedBloodLossMl(rs.getBigDecimal("estimated_blood_loss_ml"));
record.setSurgicalComplicationLkey(rs.getString("surgical_complication_lkey"));
record.setSurgicalComplicationNotes(rs.getString("surgical_complication_notes"));
record.setSpecimensTaken(rs.getString("specimens_taken"));
record.setSafetyPauseTaken(rs.getBoolean("safety_pause_taken"));
record.setFirstCountTime(rs.getBigDecimal("first_count_time"));
record.setFirstCountByKey(rs.getString("first_count_by_key"));
record.setSecondCountTime(rs.getBigDecimal("second_count_time"));
record.setSecondCountByKey(rs.getString("second_count_by_key"));
record.setFinalCountVerified(rs.getBoolean("final_count_verified"));
record.setCountDiscrepancy(rs.getBoolean("count_discrepancy"));
record.setCountDiscrepancyAction(rs.getString("count_discrepancy_action"));
record.setUnexpectedEventOccurred(rs.getBoolean("unexpected_event_occurred"));
record.setEventDescription(rs.getString("event_description"));
record.setTeamResponse(rs.getString("team_response"));
record.setEventOutcome(rs.getString("event_outcome"));
record.setComplicationSeverityLkey(rs.getString("complication_severity_lkey"));
record.setSkinClosureTime(rs.getBigDecimal("skin_closure_time"));
record.setSurgeryEndTime(rs.getBigDecimal("surgery_end_time"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
record.setUrineOutput(rs.getBigDecimal("urine_output"));
record.setActualOperationPerformed(rs.getString("actual_operation_performed"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationIntraoperativeEvents record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_intraoperative_events values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getOperationNotes());
ps.setBoolean(4, record.getConversionOccurred());
ps.setBoolean(5, record.getConversionType());
ps.setString(6, record.getConversionTypeNote());
ps.setString(7, record.getIncisionType());
ps.setBigDecimal(8, record.getEstimatedBloodLossMl());
ps.setString(9, record.getSurgicalComplicationLkey());
ps.setString(10, record.getSurgicalComplicationNotes());
ps.setString(11, record.getSpecimensTaken());
ps.setBoolean(12, record.getSafetyPauseTaken());
ps.setBigDecimal(13, record.getFirstCountTime());
ps.setString(14, record.getFirstCountByKey());
ps.setBigDecimal(15, record.getSecondCountTime());
ps.setString(16, record.getSecondCountByKey());
ps.setBoolean(17, record.getFinalCountVerified());
ps.setBoolean(18, record.getCountDiscrepancy());
ps.setString(19, record.getCountDiscrepancyAction());
ps.setBoolean(20, record.getUnexpectedEventOccurred());
ps.setString(21, record.getEventDescription());
ps.setString(22, record.getTeamResponse());
ps.setString(23, record.getEventOutcome());
ps.setString(24, record.getComplicationSeverityLkey());
ps.setBigDecimal(25, record.getSkinClosureTime());
ps.setBigDecimal(26, record.getSurgeryEndTime());
ps.setString(27, record.getCreatedBy());
ps.setBigDecimal(28, record.getCreatedAt());
ps.setString(29, record.getUpdatedBy());
ps.setBigDecimal(30, record.getUpdatedAt());
ps.setString(31, record.getDeletedBy());
ps.setBigDecimal(32, record.getDeletedAt());
ps.setBoolean(33, record.getIsvalid());
ps.setBigDecimal(34, record.getUrineOutput());
ps.setString(35, record.getActualOperationPerformed());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationIntraoperativeEventsEntity entity, String lang) {
        Class<?> myClass = ApOperationIntraoperativeEventsEntity.class;
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
public void translateObject(ApOperationIntraoperativeEventsEntity entity, String lang) {
        ApOperationIntraoperativeEventsEntity translated = (ApOperationIntraoperativeEventsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}