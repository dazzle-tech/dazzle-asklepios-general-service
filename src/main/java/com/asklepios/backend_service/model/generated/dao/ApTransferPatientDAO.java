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
import com.asklepios.backend_service.model.generated.pojo.ApTransferPatient;
import com.asklepios.backend_service.model.generated.entity.ApTransferPatientEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApTransferPatientDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApTransferPatient getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_transfer_patient where key = '"+key+"'");) {
ApTransferPatient record = new ApTransferPatient();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setFromInpatientDepartmentKey(rs.getString("from_inpatient_department_key"));
record.setToInpatientDepartmentKey(rs.getString("to_inpatient_department_key"));
record.setReasonForTransfer(rs.getString("reason_for_transfer"));
record.setUrgentTransfer(rs.getBoolean("urgent_transfer"));
record.setPlannedTransfer(rs.getBoolean("planned_transfer"));
record.setTransferNotes(rs.getString("transfer_notes"));
record.setFinalVitalsBeforeTransfer(rs.getBoolean("final_vitals_before_transfer"));
record.setIvLinesDripsChecked(rs.getBoolean("iv_lines_drips_checked"));
record.setMedicationAdministeredPreTransfer(rs.getBoolean("medication_administered_pre_transfer"));
record.setBelongingsSentWithPatient(rs.getBoolean("belongings_sent_with_patient"));
record.setClinicalHandoverDone(rs.getBoolean("clinical_handover_done"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setFromRoom(rs.getString("from_room"));
record.setToRoom(rs.getString("to_room"));
record.setFromBed(rs.getString("from_bed"));
record.setToBed(rs.getString("to_bed"));
record.setConfirmedBy(rs.getString("confirmed_by"));
record.setConfirmedAt(rs.getBigDecimal("confirmed_at"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApTransferPatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_transfer_patient set key = ?, patient_key = ?, encounter_key = ?, from_inpatient_department_key = ?, to_inpatient_department_key = ?, reason_for_transfer = ?, urgent_transfer = ?, planned_transfer = ?, transfer_notes = ?, final_vitals_before_transfer = ?, iv_lines_drips_checked = ?, medication_administered_pre_transfer = ?, belongings_sent_with_patient = ?, clinical_handover_done = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, status_lkey = ?, cancellation_reason = ?, from_room = ?, to_room = ?, from_bed = ?, to_bed = ?, confirmed_by = ?, confirmed_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getFromInpatientDepartmentKey());
ps.setString(5, record.getToInpatientDepartmentKey());
ps.setString(6, record.getReasonForTransfer());
ps.setBoolean(7, record.getUrgentTransfer());
ps.setBoolean(8, record.getPlannedTransfer());
ps.setString(9, record.getTransferNotes());
ps.setBoolean(10, record.getFinalVitalsBeforeTransfer());
ps.setBoolean(11, record.getIvLinesDripsChecked());
ps.setBoolean(12, record.getMedicationAdministeredPreTransfer());
ps.setBoolean(13, record.getBelongingsSentWithPatient());
ps.setBoolean(14, record.getClinicalHandoverDone());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getStatusLkey());
ps.setString(22, record.getCancellationReason());
ps.setString(23, record.getFromRoom());
ps.setString(24, record.getToRoom());
ps.setString(25, record.getFromBed());
ps.setString(26, record.getToBed());
ps.setString(27, record.getConfirmedBy());
ps.setBigDecimal(28, record.getConfirmedAt());
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApTransferPatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_transfer_patient set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApTransferPatient> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_transfer_patient where "+ where);) {
List<ApTransferPatient> list = new ArrayList<ApTransferPatient>();
while(rs.next()){
ApTransferPatient record = new ApTransferPatient();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setFromInpatientDepartmentKey(rs.getString("from_inpatient_department_key"));
record.setToInpatientDepartmentKey(rs.getString("to_inpatient_department_key"));
record.setReasonForTransfer(rs.getString("reason_for_transfer"));
record.setUrgentTransfer(rs.getBoolean("urgent_transfer"));
record.setPlannedTransfer(rs.getBoolean("planned_transfer"));
record.setTransferNotes(rs.getString("transfer_notes"));
record.setFinalVitalsBeforeTransfer(rs.getBoolean("final_vitals_before_transfer"));
record.setIvLinesDripsChecked(rs.getBoolean("iv_lines_drips_checked"));
record.setMedicationAdministeredPreTransfer(rs.getBoolean("medication_administered_pre_transfer"));
record.setBelongingsSentWithPatient(rs.getBoolean("belongings_sent_with_patient"));
record.setClinicalHandoverDone(rs.getBoolean("clinical_handover_done"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setFromRoom(rs.getString("from_room"));
record.setToRoom(rs.getString("to_room"));
record.setFromBed(rs.getString("from_bed"));
record.setToBed(rs.getString("to_bed"));
record.setConfirmedBy(rs.getString("confirmed_by"));
record.setConfirmedAt(rs.getBigDecimal("confirmed_at"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApTransferPatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_transfer_patient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getFromInpatientDepartmentKey());
ps.setString(5, record.getToInpatientDepartmentKey());
ps.setString(6, record.getReasonForTransfer());
ps.setBoolean(7, record.getUrgentTransfer());
ps.setBoolean(8, record.getPlannedTransfer());
ps.setString(9, record.getTransferNotes());
ps.setBoolean(10, record.getFinalVitalsBeforeTransfer());
ps.setBoolean(11, record.getIvLinesDripsChecked());
ps.setBoolean(12, record.getMedicationAdministeredPreTransfer());
ps.setBoolean(13, record.getBelongingsSentWithPatient());
ps.setBoolean(14, record.getClinicalHandoverDone());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getStatusLkey());
ps.setString(22, record.getCancellationReason());
ps.setString(23, record.getFromRoom());
ps.setString(24, record.getToRoom());
ps.setString(25, record.getFromBed());
ps.setString(26, record.getToBed());
ps.setString(27, record.getConfirmedBy());
ps.setBigDecimal(28, record.getConfirmedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApTransferPatientEntity entity, String lang) {
        Class<?> myClass = ApTransferPatientEntity.class;
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
public void translateObject(ApTransferPatientEntity entity, String lang) {
        ApTransferPatientEntity translated = (ApTransferPatientEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}