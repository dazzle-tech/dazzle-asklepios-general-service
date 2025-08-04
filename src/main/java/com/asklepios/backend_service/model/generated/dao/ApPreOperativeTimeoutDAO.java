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
import com.asklepios.backend_service.model.generated.pojo.ApPreOperativeTimeout;
import com.asklepios.backend_service.model.generated.entity.ApPreOperativeTimeoutEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPreOperativeTimeoutDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPreOperativeTimeout getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operative_timeout where key = '"+key+"'");) {
ApPreOperativeTimeout record = new ApPreOperativeTimeout();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setInitiatedBy(rs.getString("initiated_by"));
record.setPatientIdentityConfirmed(rs.getBoolean("patient_identity_confirmed"));
record.setSurgicalSiteConfirmed(rs.getBoolean("surgical_site_confirmed"));
record.setProcedureConfirmed(rs.getBoolean("procedure_confirmed"));
record.setConsentFormPresent(rs.getBoolean("consent_form_present"));
record.setAnesthesiaMachineChecked(rs.getBoolean("anesthesia_machine_checked"));
record.setMedicationPrepared(rs.getBoolean("medication_prepared"));
record.setAllergyRiskReviewed(rs.getBoolean("allergy_risk_reviewed"));
record.setDifficultAirwayRisk(rs.getBoolean("difficult_airway_risk"));
record.setAsaClassification(rs.getBoolean("asa_classification"));
record.setBloodLossExpected(rs.getBoolean("blood_loss_expected"));
record.setBloodUnitsAvailable(rs.getBoolean("blood_units_available"));
record.setEquipmentAvailable(rs.getBoolean("equipment_available"));
record.setImagingDisplayed(rs.getBoolean("imaging_displayed"));
record.setInstrumentCountPrepared(rs.getBoolean("instrument_count_prepared"));
record.setTeamIntroductionComplete(rs.getBoolean("team_introduction_complete"));
record.setSpecialConcerns(rs.getString("special_concerns"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setTimeoutStartTime(rs.getBigDecimal("timeout_start_time"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPreOperativeTimeout record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operative_timeout set key = ?, operation_request_key = ?, initiated_by = ?, patient_identity_confirmed = ?, surgical_site_confirmed = ?, procedure_confirmed = ?, consent_form_present = ?, anesthesia_machine_checked = ?, medication_prepared = ?, allergy_risk_reviewed = ?, difficult_airway_risk = ?, asa_classification = ?, blood_loss_expected = ?, blood_units_available = ?, equipment_available = ?, imaging_displayed = ?, instrument_count_prepared = ?, team_introduction_complete = ?, special_concerns = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, encounter_key = ?, patient_key = ?, timeout_start_time = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getInitiatedBy());
ps.setBoolean(4, record.getPatientIdentityConfirmed());
ps.setBoolean(5, record.getSurgicalSiteConfirmed());
ps.setBoolean(6, record.getProcedureConfirmed());
ps.setBoolean(7, record.getConsentFormPresent());
ps.setBoolean(8, record.getAnesthesiaMachineChecked());
ps.setBoolean(9, record.getMedicationPrepared());
ps.setBoolean(10, record.getAllergyRiskReviewed());
ps.setBoolean(11, record.getDifficultAirwayRisk());
ps.setBoolean(12, record.getAsaClassification());
ps.setBoolean(13, record.getBloodLossExpected());
ps.setBoolean(14, record.getBloodUnitsAvailable());
ps.setBoolean(15, record.getEquipmentAvailable());
ps.setBoolean(16, record.getImagingDisplayed());
ps.setBoolean(17, record.getInstrumentCountPrepared());
ps.setBoolean(18, record.getTeamIntroductionComplete());
ps.setString(19, record.getSpecialConcerns());
ps.setString(20, record.getCreatedBy());
ps.setString(21, record.getUpdatedBy());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getCreatedAt());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setBigDecimal(25, record.getDeletedAt());
ps.setString(26, record.getEncounterKey());
ps.setString(27, record.getPatientKey());
ps.setBigDecimal(28, record.getTimeoutStartTime());
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPreOperativeTimeout record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operative_timeout set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPreOperativeTimeout> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operative_timeout where "+ where);) {
List<ApPreOperativeTimeout> list = new ArrayList<ApPreOperativeTimeout>();
while(rs.next()){
ApPreOperativeTimeout record = new ApPreOperativeTimeout();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setInitiatedBy(rs.getString("initiated_by"));
record.setPatientIdentityConfirmed(rs.getBoolean("patient_identity_confirmed"));
record.setSurgicalSiteConfirmed(rs.getBoolean("surgical_site_confirmed"));
record.setProcedureConfirmed(rs.getBoolean("procedure_confirmed"));
record.setConsentFormPresent(rs.getBoolean("consent_form_present"));
record.setAnesthesiaMachineChecked(rs.getBoolean("anesthesia_machine_checked"));
record.setMedicationPrepared(rs.getBoolean("medication_prepared"));
record.setAllergyRiskReviewed(rs.getBoolean("allergy_risk_reviewed"));
record.setDifficultAirwayRisk(rs.getBoolean("difficult_airway_risk"));
record.setAsaClassification(rs.getBoolean("asa_classification"));
record.setBloodLossExpected(rs.getBoolean("blood_loss_expected"));
record.setBloodUnitsAvailable(rs.getBoolean("blood_units_available"));
record.setEquipmentAvailable(rs.getBoolean("equipment_available"));
record.setImagingDisplayed(rs.getBoolean("imaging_displayed"));
record.setInstrumentCountPrepared(rs.getBoolean("instrument_count_prepared"));
record.setTeamIntroductionComplete(rs.getBoolean("team_introduction_complete"));
record.setSpecialConcerns(rs.getString("special_concerns"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setTimeoutStartTime(rs.getBigDecimal("timeout_start_time"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPreOperativeTimeout record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_pre_operative_timeout values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getInitiatedBy());
ps.setBoolean(4, record.getPatientIdentityConfirmed());
ps.setBoolean(5, record.getSurgicalSiteConfirmed());
ps.setBoolean(6, record.getProcedureConfirmed());
ps.setBoolean(7, record.getConsentFormPresent());
ps.setBoolean(8, record.getAnesthesiaMachineChecked());
ps.setBoolean(9, record.getMedicationPrepared());
ps.setBoolean(10, record.getAllergyRiskReviewed());
ps.setBoolean(11, record.getDifficultAirwayRisk());
ps.setBoolean(12, record.getAsaClassification());
ps.setBoolean(13, record.getBloodLossExpected());
ps.setBoolean(14, record.getBloodUnitsAvailable());
ps.setBoolean(15, record.getEquipmentAvailable());
ps.setBoolean(16, record.getImagingDisplayed());
ps.setBoolean(17, record.getInstrumentCountPrepared());
ps.setBoolean(18, record.getTeamIntroductionComplete());
ps.setString(19, record.getSpecialConcerns());
ps.setString(20, record.getCreatedBy());
ps.setString(21, record.getUpdatedBy());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getCreatedAt());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setBigDecimal(25, record.getDeletedAt());
ps.setString(26, record.getEncounterKey());
ps.setString(27, record.getPatientKey());
ps.setBigDecimal(28, record.getTimeoutStartTime());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPreOperativeTimeoutEntity entity, String lang) {
        Class<?> myClass = ApPreOperativeTimeoutEntity.class;
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
public void translateObject(ApPreOperativeTimeoutEntity entity, String lang) {
        ApPreOperativeTimeoutEntity translated = (ApPreOperativeTimeoutEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}