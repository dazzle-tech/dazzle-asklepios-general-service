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
import com.asklepios.backend_service.model.generated.pojo.ApProcedurePerformance;
import com.asklepios.backend_service.model.generated.entity.ApProcedurePerformanceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApProcedurePerformanceDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApProcedurePerformance getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_performance where key = '"+key+"'");) {
ApProcedurePerformance record = new ApProcedurePerformance();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setActualStartTime(rs.getBigDecimal("actual_start_time"));
record.setAnesthesiaUsed(rs.getBoolean("anesthesia_used"));
record.setAnesthesiaTypeLkey(rs.getString("anesthesia_type_lkey"));
record.setAnesthesiaStartTime(rs.getBigDecimal("anesthesia_start_time"));
record.setAnesthesiaEndTime(rs.getBigDecimal("anesthesia_end_time"));
record.setAnesthesiaAdministeredBy(rs.getString("anesthesia_administered_by"));
record.setTimeOut(rs.getBoolean("time_out"));
record.setProcedureOutcomeLkey(rs.getString("procedure_outcome_lkey"));
record.setObservations(rs.getString("observations"));
record.setComplicationTypeLkey(rs.getString("complication_type_lkey"));
record.setComplicationSeverityLkey(rs.getString("complication_severity_lkey"));
record.setActionsTaken(rs.getString("actions_taken"));
record.setActualEndTime(rs.getBigDecimal("actual_end_time"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setHomeInstructionLkey(rs.getString("home_instruction_lkey"));
record.setHomeInstructionNotes(rs.getString("home_instruction_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApProcedurePerformance record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_performance set key = ?, actual_start_time = ?, anesthesia_used = ?, anesthesia_type_lkey = ?, anesthesia_start_time = ?, anesthesia_end_time = ?, anesthesia_administered_by = ?, time_out = ?, procedure_outcome_lkey = ?, observations = ?, complication_type_lkey = ?, complication_severity_lkey = ?, actions_taken = ?, actual_end_time = ?, additional_notes = ?, home_instruction_lkey = ?, home_instruction_notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setBigDecimal(2, record.getActualStartTime());
ps.setBoolean(3, record.getAnesthesiaUsed());
ps.setString(4, record.getAnesthesiaTypeLkey());
ps.setBigDecimal(5, record.getAnesthesiaStartTime());
ps.setBigDecimal(6, record.getAnesthesiaEndTime());
ps.setString(7, record.getAnesthesiaAdministeredBy());
ps.setBoolean(8, record.getTimeOut());
ps.setString(9, record.getProcedureOutcomeLkey());
ps.setString(10, record.getObservations());
ps.setString(11, record.getComplicationTypeLkey());
ps.setString(12, record.getComplicationSeverityLkey());
ps.setString(13, record.getActionsTaken());
ps.setBigDecimal(14, record.getActualEndTime());
ps.setString(15, record.getAdditionalNotes());
ps.setString(16, record.getHomeInstructionLkey());
ps.setString(17, record.getHomeInstructionNotes());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsvalid());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApProcedurePerformance record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_performance set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApProcedurePerformance> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_performance where "+ where);) {
List<ApProcedurePerformance> list = new ArrayList<ApProcedurePerformance>();
while(rs.next()){
ApProcedurePerformance record = new ApProcedurePerformance();
record.setKey(rs.getString("key"));
record.setActualStartTime(rs.getBigDecimal("actual_start_time"));
record.setAnesthesiaUsed(rs.getBoolean("anesthesia_used"));
record.setAnesthesiaTypeLkey(rs.getString("anesthesia_type_lkey"));
record.setAnesthesiaStartTime(rs.getBigDecimal("anesthesia_start_time"));
record.setAnesthesiaEndTime(rs.getBigDecimal("anesthesia_end_time"));
record.setAnesthesiaAdministeredBy(rs.getString("anesthesia_administered_by"));
record.setTimeOut(rs.getBoolean("time_out"));
record.setProcedureOutcomeLkey(rs.getString("procedure_outcome_lkey"));
record.setObservations(rs.getString("observations"));
record.setComplicationTypeLkey(rs.getString("complication_type_lkey"));
record.setComplicationSeverityLkey(rs.getString("complication_severity_lkey"));
record.setActionsTaken(rs.getString("actions_taken"));
record.setActualEndTime(rs.getBigDecimal("actual_end_time"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setHomeInstructionLkey(rs.getString("home_instruction_lkey"));
record.setHomeInstructionNotes(rs.getString("home_instruction_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApProcedurePerformance record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_procedure_performance values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setBigDecimal(2, record.getActualStartTime());
ps.setBoolean(3, record.getAnesthesiaUsed());
ps.setString(4, record.getAnesthesiaTypeLkey());
ps.setBigDecimal(5, record.getAnesthesiaStartTime());
ps.setBigDecimal(6, record.getAnesthesiaEndTime());
ps.setString(7, record.getAnesthesiaAdministeredBy());
ps.setBoolean(8, record.getTimeOut());
ps.setString(9, record.getProcedureOutcomeLkey());
ps.setString(10, record.getObservations());
ps.setString(11, record.getComplicationTypeLkey());
ps.setString(12, record.getComplicationSeverityLkey());
ps.setString(13, record.getActionsTaken());
ps.setBigDecimal(14, record.getActualEndTime());
ps.setString(15, record.getAdditionalNotes());
ps.setString(16, record.getHomeInstructionLkey());
ps.setString(17, record.getHomeInstructionNotes());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApProcedurePerformanceEntity entity, String lang) {
        Class<?> myClass = ApProcedurePerformanceEntity.class;
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
public void translateObject(ApProcedurePerformanceEntity entity, String lang) {
        ApProcedurePerformanceEntity translated = (ApProcedurePerformanceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}