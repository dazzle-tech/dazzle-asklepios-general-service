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
import com.asklepios.backend_service.model.generated.pojo.ApProcedure;
import com.asklepios.backend_service.model.generated.entity.ApProcedureEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApProcedureDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApProcedure getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure where key = '"+key+"'");) {
ApProcedure record = new ApProcedure();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureNameKey(rs.getString("procedure_name_key"));
record.setProcedureId(rs.getString("procedure_id"));
record.setProcedureLevelLkey(rs.getString("procedure_level_lkey"));
record.setCategoryKey(rs.getString("category_key"));
record.setIndications(rs.getString("indications"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setScheduledDateTime(rs.getBigDecimal("scheduled_date_time"));
record.setNotes(rs.getString("notes"));
record.setDepartmentKey(rs.getString("department_key"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setFacilityKey(rs.getString("facility_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setBodyPartLkey(rs.getString("body_part_lkey"));
record.setSideLkey(rs.getString("side_lkey"));
record.setCurrentDepartment(rs.getBoolean("current_department"));
record.setPatientKey(rs.getString("patient_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApProcedure record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure set key = ?, procedure_name_key = ?, procedure_id = ?, procedure_level_lkey = ?, category_key = ?, indications = ?, priority_lkey = ?, status_lkey = ?, scheduled_date_time = ?, notes = ?, department_key = ?, cancellation_reason = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, facility_key = ?, encounter_key = ?, body_part_lkey = ?, side_lkey = ?, current_department = ?, patient_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureNameKey());
ps.setString(3, record.getProcedureId());
ps.setString(4, record.getProcedureLevelLkey());
ps.setString(5, record.getCategoryKey());
ps.setString(6, record.getIndications());
ps.setString(7, record.getPriorityLkey());
ps.setString(8, record.getStatusLkey());
ps.setBigDecimal(9, record.getScheduledDateTime());
ps.setString(10, record.getNotes());
ps.setString(11, record.getDepartmentKey());
ps.setString(12, record.getCancellationReason());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setString(19, record.getFacilityKey());
ps.setString(20, record.getEncounterKey());
ps.setString(21, record.getBodyPartLkey());
ps.setString(22, record.getSideLkey());
ps.setBoolean(23, record.getCurrentDepartment());
ps.setString(24, record.getPatientKey());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApProcedure record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApProcedure> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure where "+ where);) {
List<ApProcedure> list = new ArrayList<ApProcedure>();
while(rs.next()){
ApProcedure record = new ApProcedure();
record.setKey(rs.getString("key"));
record.setProcedureNameKey(rs.getString("procedure_name_key"));
record.setProcedureId(rs.getString("procedure_id"));
record.setProcedureLevelLkey(rs.getString("procedure_level_lkey"));
record.setCategoryKey(rs.getString("category_key"));
record.setIndications(rs.getString("indications"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setScheduledDateTime(rs.getBigDecimal("scheduled_date_time"));
record.setNotes(rs.getString("notes"));
record.setDepartmentKey(rs.getString("department_key"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setFacilityKey(rs.getString("facility_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setBodyPartLkey(rs.getString("body_part_lkey"));
record.setSideLkey(rs.getString("side_lkey"));
record.setCurrentDepartment(rs.getBoolean("current_department"));
record.setPatientKey(rs.getString("patient_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApProcedure record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_procedure values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureNameKey());
ps.setString(3, record.getProcedureId());
ps.setString(4, record.getProcedureLevelLkey());
ps.setString(5, record.getCategoryKey());
ps.setString(6, record.getIndications());
ps.setString(7, record.getPriorityLkey());
ps.setString(8, record.getStatusLkey());
ps.setBigDecimal(9, record.getScheduledDateTime());
ps.setString(10, record.getNotes());
ps.setString(11, record.getDepartmentKey());
ps.setString(12, record.getCancellationReason());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setString(19, record.getFacilityKey());
ps.setString(20, record.getEncounterKey());
ps.setString(21, record.getBodyPartLkey());
ps.setString(22, record.getSideLkey());
ps.setBoolean(23, record.getCurrentDepartment());
ps.setString(24, record.getPatientKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApProcedureEntity entity, String lang) {
        Class<?> myClass = ApProcedureEntity.class;
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
public void translateObject(ApProcedureEntity entity, String lang) {
        ApProcedureEntity translated = (ApProcedureEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}