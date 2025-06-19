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
import com.asklepios.backend_service.model.generated.pojo.ApPostProcedureVitals;
import com.asklepios.backend_service.model.generated.entity.ApPostProcedureVitalsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPostProcedureVitalsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPostProcedureVitals getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_procedure_vitals where key = '"+key+"'");) {
ApPostProcedureVitals record = new ApPostProcedureVitals();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setBloodPressureSystolic(rs.getBigDecimal("blood_pressure_systolic"));
record.setBloodPressureDiastolic(rs.getBigDecimal("blood_pressure_diastolic"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setOxygenSaturation(rs.getBigDecimal("oxygen_saturation"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setPainDescription(rs.getString("pain_description"));
record.setRecoveryNotes(rs.getString("recovery_notes"));
record.setAdditionalObservations(rs.getString("additional_observations"));
record.setEquipmentCountDone(rs.getBoolean("equipment_count_done"));
record.setCountStatusLkey(rs.getString("count_status_lkey"));
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
public void updateRecord(ApPostProcedureVitals record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_procedure_vitals set key = ?, procedure_key = ?, blood_pressure_systolic = ?, blood_pressure_diastolic = ?, heart_rate = ?, temperature = ?, oxygen_saturation = ?, pain_score_lkey = ?, pain_description = ?, recovery_notes = ?, additional_observations = ?, equipment_count_done = ?, count_status_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureKey());
ps.setBigDecimal(3, record.getBloodPressureSystolic());
ps.setBigDecimal(4, record.getBloodPressureDiastolic());
ps.setBigDecimal(5, record.getHeartRate());
ps.setBigDecimal(6, record.getTemperature());
ps.setBigDecimal(7, record.getOxygenSaturation());
ps.setString(8, record.getPainScoreLkey());
ps.setString(9, record.getPainDescription());
ps.setString(10, record.getRecoveryNotes());
ps.setString(11, record.getAdditionalObservations());
ps.setBoolean(12, record.getEquipmentCountDone());
ps.setString(13, record.getCountStatusLkey());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsvalid());
ps.setString(21, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPostProcedureVitals record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_procedure_vitals set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPostProcedureVitals> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_procedure_vitals where "+ where);) {
List<ApPostProcedureVitals> list = new ArrayList<ApPostProcedureVitals>();
while(rs.next()){
ApPostProcedureVitals record = new ApPostProcedureVitals();
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setBloodPressureSystolic(rs.getBigDecimal("blood_pressure_systolic"));
record.setBloodPressureDiastolic(rs.getBigDecimal("blood_pressure_diastolic"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setOxygenSaturation(rs.getBigDecimal("oxygen_saturation"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setPainDescription(rs.getString("pain_description"));
record.setRecoveryNotes(rs.getString("recovery_notes"));
record.setAdditionalObservations(rs.getString("additional_observations"));
record.setEquipmentCountDone(rs.getBoolean("equipment_count_done"));
record.setCountStatusLkey(rs.getString("count_status_lkey"));
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
public String saveRecord(ApPostProcedureVitals record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_post_procedure_vitals values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureKey());
ps.setBigDecimal(3, record.getBloodPressureSystolic());
ps.setBigDecimal(4, record.getBloodPressureDiastolic());
ps.setBigDecimal(5, record.getHeartRate());
ps.setBigDecimal(6, record.getTemperature());
ps.setBigDecimal(7, record.getOxygenSaturation());
ps.setString(8, record.getPainScoreLkey());
ps.setString(9, record.getPainDescription());
ps.setString(10, record.getRecoveryNotes());
ps.setString(11, record.getAdditionalObservations());
ps.setBoolean(12, record.getEquipmentCountDone());
ps.setString(13, record.getCountStatusLkey());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPostProcedureVitalsEntity entity, String lang) {
        Class<?> myClass = ApPostProcedureVitalsEntity.class;
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
public void translateObject(ApPostProcedureVitalsEntity entity, String lang) {
        ApPostProcedureVitalsEntity translated = (ApPostProcedureVitalsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}