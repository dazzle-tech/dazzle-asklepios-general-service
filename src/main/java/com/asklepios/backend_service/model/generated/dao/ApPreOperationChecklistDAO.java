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
import com.asklepios.backend_service.model.generated.pojo.ApPreOperationChecklist;
import com.asklepios.backend_service.model.generated.entity.ApPreOperationChecklistEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPreOperationChecklistDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPreOperationChecklist getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operation_checklist where key = '"+key+"'");) {
ApPreOperationChecklist record = new ApPreOperationChecklist();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setOperationKey(rs.getString("operation_key"));
record.setPatientIdentityVerified(rs.getBoolean("patient_identity_verified"));
record.setConsentSurgerySigned(rs.getBoolean("consent_surgery_signed"));
record.setConsentAnesthesiaSigned(rs.getBoolean("consent_anesthesia_signed"));
record.setSurgicalProcedureConfirmed(rs.getBoolean("surgical_procedure_confirmed"));
record.setSiteOfSurgeryMarked(rs.getBoolean("site_of_surgery_marked"));
record.setNpoStatusConfirmed(rs.getBoolean("npo_status_confirmed"));
record.setPreOpVitalsRecorded(rs.getBoolean("pre_op_vitals_recorded"));
record.setPatientBathed(rs.getBoolean("patient_bathed"));
record.setJewelryRemoved(rs.getBoolean("jewelry_removed"));
record.setDenturesRemovedOrNoted(rs.getBoolean("dentures_removed_or_noted"));
record.setProsthesisNotedOrRemoved(rs.getBoolean("prosthesis_noted_or_removed"));
record.setClothingReplaced(rs.getBoolean("clothing_replaced"));
record.setAllergiesReviewed(rs.getBoolean("allergies_reviewed"));
record.setPreOpMedsGiven(rs.getBoolean("pre_op_meds_given"));
record.setChronicMedsManaged(rs.getBoolean("chronic_meds_managed"));
record.setAnticoagulantsManaged(rs.getBoolean("anticoagulants_managed"));
record.setIvAccessSecured(rs.getBoolean("iv_access_secured"));
record.setIvFluidsStarted(rs.getBoolean("iv_fluids_started"));
record.setBloodProductsPrepared(rs.getBoolean("blood_products_prepared"));
record.setEmrUpdated(rs.getBoolean("emr_updated"));
record.setLabsImagingReviewed(rs.getBoolean("labs_imaging_reviewed"));
record.setConsentFormsAvailable(rs.getBoolean("consent_forms_available"));
record.setPersonalBelongingsSecured(rs.getBoolean("personal_belongings_secured"));
record.setInterpreterArranged(rs.getBoolean("interpreter_arranged"));
record.setVoidedOrCatheterPresent(rs.getBoolean("voided_or_catheter_present"));
record.setBedInLowestPosition(rs.getBoolean("bed_in_lowest_position"));
record.setTransferModeArranged(rs.getBoolean("transfer_mode_arranged"));
record.setHandoffToOrNursePrepared(rs.getBoolean("handoff_to_or_nurse_prepared"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPreOperationChecklist record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operation_checklist set key = ?, encounter_key = ?, patient_key = ?, operation_key = ?, patient_identity_verified = ?, consent_surgery_signed = ?, consent_anesthesia_signed = ?, surgical_procedure_confirmed = ?, site_of_surgery_marked = ?, npo_status_confirmed = ?, pre_op_vitals_recorded = ?, patient_bathed = ?, jewelry_removed = ?, dentures_removed_or_noted = ?, prosthesis_noted_or_removed = ?, clothing_replaced = ?, allergies_reviewed = ?, pre_op_meds_given = ?, chronic_meds_managed = ?, anticoagulants_managed = ?, iv_access_secured = ?, iv_fluids_started = ?, blood_products_prepared = ?, emr_updated = ?, labs_imaging_reviewed = ?, consent_forms_available = ?, personal_belongings_secured = ?, interpreter_arranged = ?, voided_or_catheter_present = ?, bed_in_lowest_position = ?, transfer_mode_arranged = ?, handoff_to_or_nurse_prepared = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setString(4, record.getOperationKey());
ps.setBoolean(5, record.getPatientIdentityVerified());
ps.setBoolean(6, record.getConsentSurgerySigned());
ps.setBoolean(7, record.getConsentAnesthesiaSigned());
ps.setBoolean(8, record.getSurgicalProcedureConfirmed());
ps.setBoolean(9, record.getSiteOfSurgeryMarked());
ps.setBoolean(10, record.getNpoStatusConfirmed());
ps.setBoolean(11, record.getPreOpVitalsRecorded());
ps.setBoolean(12, record.getPatientBathed());
ps.setBoolean(13, record.getJewelryRemoved());
ps.setBoolean(14, record.getDenturesRemovedOrNoted());
ps.setBoolean(15, record.getProsthesisNotedOrRemoved());
ps.setBoolean(16, record.getClothingReplaced());
ps.setBoolean(17, record.getAllergiesReviewed());
ps.setBoolean(18, record.getPreOpMedsGiven());
ps.setBoolean(19, record.getChronicMedsManaged());
ps.setBoolean(20, record.getAnticoagulantsManaged());
ps.setBoolean(21, record.getIvAccessSecured());
ps.setBoolean(22, record.getIvFluidsStarted());
ps.setBoolean(23, record.getBloodProductsPrepared());
ps.setBoolean(24, record.getEmrUpdated());
ps.setBoolean(25, record.getLabsImagingReviewed());
ps.setBoolean(26, record.getConsentFormsAvailable());
ps.setBoolean(27, record.getPersonalBelongingsSecured());
ps.setBoolean(28, record.getInterpreterArranged());
ps.setBoolean(29, record.getVoidedOrCatheterPresent());
ps.setBoolean(30, record.getBedInLowestPosition());
ps.setBoolean(31, record.getTransferModeArranged());
ps.setBoolean(32, record.getHandoffToOrNursePrepared());
ps.setString(33, record.getCreatedBy());
ps.setBigDecimal(34, record.getCreatedAt());
ps.setString(35, record.getUpdatedBy());
ps.setBigDecimal(36, record.getUpdatedAt());
ps.setString(37, record.getDeletedBy());
ps.setBigDecimal(38, record.getDeletedAt());
ps.setBoolean(39, record.getIsValid());
ps.setString(40, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPreOperationChecklist record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operation_checklist set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPreOperationChecklist> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operation_checklist where "+ where);) {
List<ApPreOperationChecklist> list = new ArrayList<ApPreOperationChecklist>();
while(rs.next()){
ApPreOperationChecklist record = new ApPreOperationChecklist();
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setOperationKey(rs.getString("operation_key"));
record.setPatientIdentityVerified(rs.getBoolean("patient_identity_verified"));
record.setConsentSurgerySigned(rs.getBoolean("consent_surgery_signed"));
record.setConsentAnesthesiaSigned(rs.getBoolean("consent_anesthesia_signed"));
record.setSurgicalProcedureConfirmed(rs.getBoolean("surgical_procedure_confirmed"));
record.setSiteOfSurgeryMarked(rs.getBoolean("site_of_surgery_marked"));
record.setNpoStatusConfirmed(rs.getBoolean("npo_status_confirmed"));
record.setPreOpVitalsRecorded(rs.getBoolean("pre_op_vitals_recorded"));
record.setPatientBathed(rs.getBoolean("patient_bathed"));
record.setJewelryRemoved(rs.getBoolean("jewelry_removed"));
record.setDenturesRemovedOrNoted(rs.getBoolean("dentures_removed_or_noted"));
record.setProsthesisNotedOrRemoved(rs.getBoolean("prosthesis_noted_or_removed"));
record.setClothingReplaced(rs.getBoolean("clothing_replaced"));
record.setAllergiesReviewed(rs.getBoolean("allergies_reviewed"));
record.setPreOpMedsGiven(rs.getBoolean("pre_op_meds_given"));
record.setChronicMedsManaged(rs.getBoolean("chronic_meds_managed"));
record.setAnticoagulantsManaged(rs.getBoolean("anticoagulants_managed"));
record.setIvAccessSecured(rs.getBoolean("iv_access_secured"));
record.setIvFluidsStarted(rs.getBoolean("iv_fluids_started"));
record.setBloodProductsPrepared(rs.getBoolean("blood_products_prepared"));
record.setEmrUpdated(rs.getBoolean("emr_updated"));
record.setLabsImagingReviewed(rs.getBoolean("labs_imaging_reviewed"));
record.setConsentFormsAvailable(rs.getBoolean("consent_forms_available"));
record.setPersonalBelongingsSecured(rs.getBoolean("personal_belongings_secured"));
record.setInterpreterArranged(rs.getBoolean("interpreter_arranged"));
record.setVoidedOrCatheterPresent(rs.getBoolean("voided_or_catheter_present"));
record.setBedInLowestPosition(rs.getBoolean("bed_in_lowest_position"));
record.setTransferModeArranged(rs.getBoolean("transfer_mode_arranged"));
record.setHandoffToOrNursePrepared(rs.getBoolean("handoff_to_or_nurse_prepared"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPreOperationChecklist record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_pre_operation_checklist values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setString(4, record.getOperationKey());
ps.setBoolean(5, record.getPatientIdentityVerified());
ps.setBoolean(6, record.getConsentSurgerySigned());
ps.setBoolean(7, record.getConsentAnesthesiaSigned());
ps.setBoolean(8, record.getSurgicalProcedureConfirmed());
ps.setBoolean(9, record.getSiteOfSurgeryMarked());
ps.setBoolean(10, record.getNpoStatusConfirmed());
ps.setBoolean(11, record.getPreOpVitalsRecorded());
ps.setBoolean(12, record.getPatientBathed());
ps.setBoolean(13, record.getJewelryRemoved());
ps.setBoolean(14, record.getDenturesRemovedOrNoted());
ps.setBoolean(15, record.getProsthesisNotedOrRemoved());
ps.setBoolean(16, record.getClothingReplaced());
ps.setBoolean(17, record.getAllergiesReviewed());
ps.setBoolean(18, record.getPreOpMedsGiven());
ps.setBoolean(19, record.getChronicMedsManaged());
ps.setBoolean(20, record.getAnticoagulantsManaged());
ps.setBoolean(21, record.getIvAccessSecured());
ps.setBoolean(22, record.getIvFluidsStarted());
ps.setBoolean(23, record.getBloodProductsPrepared());
ps.setBoolean(24, record.getEmrUpdated());
ps.setBoolean(25, record.getLabsImagingReviewed());
ps.setBoolean(26, record.getConsentFormsAvailable());
ps.setBoolean(27, record.getPersonalBelongingsSecured());
ps.setBoolean(28, record.getInterpreterArranged());
ps.setBoolean(29, record.getVoidedOrCatheterPresent());
ps.setBoolean(30, record.getBedInLowestPosition());
ps.setBoolean(31, record.getTransferModeArranged());
ps.setBoolean(32, record.getHandoffToOrNursePrepared());
ps.setString(33, record.getCreatedBy());
ps.setBigDecimal(34, record.getCreatedAt());
ps.setString(35, record.getUpdatedBy());
ps.setBigDecimal(36, record.getUpdatedAt());
ps.setString(37, record.getDeletedBy());
ps.setBigDecimal(38, record.getDeletedAt());
ps.setBoolean(39, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPreOperationChecklistEntity entity, String lang) {
        Class<?> myClass = ApPreOperationChecklistEntity.class;
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
public void translateObject(ApPreOperationChecklistEntity entity, String lang) {
        ApPreOperationChecklistEntity translated = (ApPreOperationChecklistEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}