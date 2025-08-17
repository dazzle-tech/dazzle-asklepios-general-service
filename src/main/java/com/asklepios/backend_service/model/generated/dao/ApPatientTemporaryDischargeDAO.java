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
import com.asklepios.backend_service.model.generated.pojo.ApPatientTemporaryDischarge;
import com.asklepios.backend_service.model.generated.entity.ApPatientTemporaryDischargeEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientTemporaryDischargeDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientTemporaryDischarge getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_temporary_discharge where key = '"+key+"'");) {
ApPatientTemporaryDischarge record = new ApPatientTemporaryDischarge();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setReasonForTemporaryDischarge(rs.getString("reason_for_temporary_discharge"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setExpectedReturnAt(rs.getBigDecimal("expected_return_at"));
record.setConsentTaken(rs.getBoolean("consent_taken"));
record.setBillingApprovalStatusLkey(rs.getString("billing_approval_status_lkey"));
record.setReturnAt(rs.getBigDecimal("return_at"));
record.setBedRetained(rs.getBoolean("bed_retained"));
record.setComments(rs.getString("comments"));
record.setRoomKey(rs.getString("room_key"));
record.setBedKey(rs.getString("bed_key"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setFromRoom(rs.getString("from_room"));
record.setFromBed(rs.getString("from_bed"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientTemporaryDischarge record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_temporary_discharge set key = ?, encounter_key = ?, patient_key = ?, reason_for_temporary_discharge = ?, type_lkey = ?, expected_return_at = ?, consent_taken = ?, billing_approval_status_lkey = ?, return_at = ?, bed_retained = ?, comments = ?, room_key = ?, bed_key = ?, notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, from_room = ?, from_bed = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setString(4, record.getReasonForTemporaryDischarge());
ps.setString(5, record.getTypeLkey());
ps.setBigDecimal(6, record.getExpectedReturnAt());
ps.setBoolean(7, record.getConsentTaken());
ps.setString(8, record.getBillingApprovalStatusLkey());
ps.setBigDecimal(9, record.getReturnAt());
ps.setBoolean(10, record.getBedRetained());
ps.setString(11, record.getComments());
ps.setString(12, record.getRoomKey());
ps.setString(13, record.getBedKey());
ps.setString(14, record.getNotes());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getFromRoom());
ps.setString(22, record.getFromBed());
ps.setString(23, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientTemporaryDischarge record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_temporary_discharge set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientTemporaryDischarge> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_temporary_discharge where "+ where);) {
List<ApPatientTemporaryDischarge> list = new ArrayList<ApPatientTemporaryDischarge>();
while(rs.next()){
ApPatientTemporaryDischarge record = new ApPatientTemporaryDischarge();
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setReasonForTemporaryDischarge(rs.getString("reason_for_temporary_discharge"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setExpectedReturnAt(rs.getBigDecimal("expected_return_at"));
record.setConsentTaken(rs.getBoolean("consent_taken"));
record.setBillingApprovalStatusLkey(rs.getString("billing_approval_status_lkey"));
record.setReturnAt(rs.getBigDecimal("return_at"));
record.setBedRetained(rs.getBoolean("bed_retained"));
record.setComments(rs.getString("comments"));
record.setRoomKey(rs.getString("room_key"));
record.setBedKey(rs.getString("bed_key"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setFromRoom(rs.getString("from_room"));
record.setFromBed(rs.getString("from_bed"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientTemporaryDischarge record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_temporary_discharge values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setString(4, record.getReasonForTemporaryDischarge());
ps.setString(5, record.getTypeLkey());
ps.setBigDecimal(6, record.getExpectedReturnAt());
ps.setBoolean(7, record.getConsentTaken());
ps.setString(8, record.getBillingApprovalStatusLkey());
ps.setBigDecimal(9, record.getReturnAt());
ps.setBoolean(10, record.getBedRetained());
ps.setString(11, record.getComments());
ps.setString(12, record.getRoomKey());
ps.setString(13, record.getBedKey());
ps.setString(14, record.getNotes());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getFromRoom());
ps.setString(22, record.getFromBed());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientTemporaryDischargeEntity entity, String lang) {
        Class<?> myClass = ApPatientTemporaryDischargeEntity.class;
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
public void translateObject(ApPatientTemporaryDischargeEntity entity, String lang) {
        ApPatientTemporaryDischargeEntity translated = (ApPatientTemporaryDischargeEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}