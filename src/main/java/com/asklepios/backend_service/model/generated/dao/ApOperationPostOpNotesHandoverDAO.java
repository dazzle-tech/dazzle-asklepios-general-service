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
import com.asklepios.backend_service.model.generated.pojo.ApOperationPostOpNotesHandover;
import com.asklepios.backend_service.model.generated.entity.ApOperationPostOpNotesHandoverEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationPostOpNotesHandoverDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationPostOpNotesHandover getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_post_op_notes_handover where key = '"+key+"'");) {
ApOperationPostOpNotesHandover record = new ApOperationPostOpNotesHandover();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setIndications(rs.getString("indications"));
record.setOperativeFindings(rs.getString("operative_findings"));
record.setOperationPerformedSummary(rs.getString("operation_performed_summary"));
record.setVariationsFromPlan(rs.getString("variations_from_plan"));
record.setPostOpDestinationKey(rs.getString("post_op_destination_key"));
record.setOxygenRequired(rs.getBoolean("oxygen_required"));
record.setOxygenFlowRate(rs.getBigDecimal("oxygen_flow_rate"));
record.setSpecialInstructions(rs.getString("special_instructions"));
record.setHandoverTime(rs.getBigDecimal("handover_time"));
record.setVerbalSummaryGiven(rs.getBoolean("verbal_summary_given"));
record.setHandoverNotes(rs.getString("handover_notes"));
record.setCompletedAt(rs.getBigDecimal("completed_at"));
record.setRecoveryConditionLkey(rs.getString("recovery_condition_lkey"));
record.setSurgeryStatusLkey(rs.getString("surgery_status_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationPostOpNotesHandover record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_post_op_notes_handover set key = ?, operation_request_key = ?, indications = ?, operative_findings = ?, operation_performed_summary = ?, variations_from_plan = ?, post_op_destination_key = ?, oxygen_required = ?, oxygen_flow_rate = ?, special_instructions = ?, handover_time = ?, verbal_summary_given = ?, handover_notes = ?, completed_at = ?, recovery_condition_lkey = ?, surgery_status_lkey = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getIndications());
ps.setString(4, record.getOperativeFindings());
ps.setString(5, record.getOperationPerformedSummary());
ps.setString(6, record.getVariationsFromPlan());
ps.setString(7, record.getPostOpDestinationKey());
ps.setBoolean(8, record.getOxygenRequired());
ps.setBigDecimal(9, record.getOxygenFlowRate());
ps.setString(10, record.getSpecialInstructions());
ps.setBigDecimal(11, record.getHandoverTime());
ps.setBoolean(12, record.getVerbalSummaryGiven());
ps.setString(13, record.getHandoverNotes());
ps.setBigDecimal(14, record.getCompletedAt());
ps.setString(15, record.getRecoveryConditionLkey());
ps.setString(16, record.getSurgeryStatusLkey());
ps.setString(17, record.getCreatedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setString(19, record.getUpdatedBy());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setString(21, record.getDeletedBy());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsvalid());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationPostOpNotesHandover record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_post_op_notes_handover set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationPostOpNotesHandover> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_post_op_notes_handover where "+ where);) {
List<ApOperationPostOpNotesHandover> list = new ArrayList<ApOperationPostOpNotesHandover>();
while(rs.next()){
ApOperationPostOpNotesHandover record = new ApOperationPostOpNotesHandover();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setIndications(rs.getString("indications"));
record.setOperativeFindings(rs.getString("operative_findings"));
record.setOperationPerformedSummary(rs.getString("operation_performed_summary"));
record.setVariationsFromPlan(rs.getString("variations_from_plan"));
record.setPostOpDestinationKey(rs.getString("post_op_destination_key"));
record.setOxygenRequired(rs.getBoolean("oxygen_required"));
record.setOxygenFlowRate(rs.getBigDecimal("oxygen_flow_rate"));
record.setSpecialInstructions(rs.getString("special_instructions"));
record.setHandoverTime(rs.getBigDecimal("handover_time"));
record.setVerbalSummaryGiven(rs.getBoolean("verbal_summary_given"));
record.setHandoverNotes(rs.getString("handover_notes"));
record.setCompletedAt(rs.getBigDecimal("completed_at"));
record.setRecoveryConditionLkey(rs.getString("recovery_condition_lkey"));
record.setSurgeryStatusLkey(rs.getString("surgery_status_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationPostOpNotesHandover record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_post_op_notes_handover values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getIndications());
ps.setString(4, record.getOperativeFindings());
ps.setString(5, record.getOperationPerformedSummary());
ps.setString(6, record.getVariationsFromPlan());
ps.setString(7, record.getPostOpDestinationKey());
ps.setBoolean(8, record.getOxygenRequired());
ps.setBigDecimal(9, record.getOxygenFlowRate());
ps.setString(10, record.getSpecialInstructions());
ps.setBigDecimal(11, record.getHandoverTime());
ps.setBoolean(12, record.getVerbalSummaryGiven());
ps.setString(13, record.getHandoverNotes());
ps.setBigDecimal(14, record.getCompletedAt());
ps.setString(15, record.getRecoveryConditionLkey());
ps.setString(16, record.getSurgeryStatusLkey());
ps.setString(17, record.getCreatedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setString(19, record.getUpdatedBy());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setString(21, record.getDeletedBy());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationPostOpNotesHandoverEntity entity, String lang) {
        Class<?> myClass = ApOperationPostOpNotesHandoverEntity.class;
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
public void translateObject(ApOperationPostOpNotesHandoverEntity entity, String lang) {
        ApOperationPostOpNotesHandoverEntity translated = (ApOperationPostOpNotesHandoverEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}