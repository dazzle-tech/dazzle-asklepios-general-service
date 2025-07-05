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
import com.asklepios.backend_service.model.generated.pojo.ApMedicationReconciliation;
import com.asklepios.backend_service.model.generated.entity.ApMedicationReconciliationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApMedicationReconciliationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApMedicationReconciliation getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medication_reconciliation where key = '"+key+"'");) {
ApMedicationReconciliation record = new ApMedicationReconciliation();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setDosage(rs.getBigDecimal("dosage"));
record.setDosageLkey(rs.getString("dosage_lkey"));
record.setRouteLkey(rs.getString("route_lkey"));
record.setFrequencyLkey(rs.getString("frequency_lkey"));
record.setStartDate(rs.getBigDecimal("start_date"));
record.setLastDoseTaken(rs.getBigDecimal("last_dose_taken"));
record.setIndication(rs.getString("indication"));
record.setSourceOfInfo(rs.getString("source_of_info"));
record.setMedicationAvailableWithPatient(rs.getBoolean("medication_available_with_patient"));
record.setContinueInHospital(rs.getBoolean("continue_in_hospital"));
record.setDiscrepancyIdentified(rs.getBoolean("discrepancy_identified"));
record.setActionTaken(rs.getString("action_taken"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApMedicationReconciliation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medication_reconciliation set key = ?, patient_key = ?, encounter_key = ?, active_ingredient_key = ?, dosage = ?, dosage_lkey = ?, route_lkey = ?, frequency_lkey = ?, start_date = ?, last_dose_taken = ?, indication = ?, source_of_info = ?, medication_available_with_patient = ?, continue_in_hospital = ?, discrepancy_identified = ?, action_taken = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getActiveIngredientKey());
ps.setBigDecimal(5, record.getDosage());
ps.setString(6, record.getDosageLkey());
ps.setString(7, record.getRouteLkey());
ps.setString(8, record.getFrequencyLkey());
ps.setBigDecimal(9, record.getStartDate());
ps.setBigDecimal(10, record.getLastDoseTaken());
ps.setString(11, record.getIndication());
ps.setString(12, record.getSourceOfInfo());
ps.setBoolean(13, record.getMedicationAvailableWithPatient());
ps.setBoolean(14, record.getContinueInHospital());
ps.setBoolean(15, record.getDiscrepancyIdentified());
ps.setString(16, record.getActionTaken());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setString(23, record.getStatusLkey());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApMedicationReconciliation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medication_reconciliation set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApMedicationReconciliation> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medication_reconciliation where "+ where);) {
List<ApMedicationReconciliation> list = new ArrayList<ApMedicationReconciliation>();
while(rs.next()){
ApMedicationReconciliation record = new ApMedicationReconciliation();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setDosage(rs.getBigDecimal("dosage"));
record.setDosageLkey(rs.getString("dosage_lkey"));
record.setRouteLkey(rs.getString("route_lkey"));
record.setFrequencyLkey(rs.getString("frequency_lkey"));
record.setStartDate(rs.getBigDecimal("start_date"));
record.setLastDoseTaken(rs.getBigDecimal("last_dose_taken"));
record.setIndication(rs.getString("indication"));
record.setSourceOfInfo(rs.getString("source_of_info"));
record.setMedicationAvailableWithPatient(rs.getBoolean("medication_available_with_patient"));
record.setContinueInHospital(rs.getBoolean("continue_in_hospital"));
record.setDiscrepancyIdentified(rs.getBoolean("discrepancy_identified"));
record.setActionTaken(rs.getString("action_taken"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApMedicationReconciliation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_medication_reconciliation values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getActiveIngredientKey());
ps.setBigDecimal(5, record.getDosage());
ps.setString(6, record.getDosageLkey());
ps.setString(7, record.getRouteLkey());
ps.setString(8, record.getFrequencyLkey());
ps.setBigDecimal(9, record.getStartDate());
ps.setBigDecimal(10, record.getLastDoseTaken());
ps.setString(11, record.getIndication());
ps.setString(12, record.getSourceOfInfo());
ps.setBoolean(13, record.getMedicationAvailableWithPatient());
ps.setBoolean(14, record.getContinueInHospital());
ps.setBoolean(15, record.getDiscrepancyIdentified());
ps.setString(16, record.getActionTaken());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setString(23, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApMedicationReconciliationEntity entity, String lang) {
        Class<?> myClass = ApMedicationReconciliationEntity.class;
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
public void translateObject(ApMedicationReconciliationEntity entity, String lang) {
        ApMedicationReconciliationEntity translated = (ApMedicationReconciliationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}