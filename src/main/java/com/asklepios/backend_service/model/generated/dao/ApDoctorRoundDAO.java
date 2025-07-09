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
import com.asklepios.backend_service.model.generated.pojo.ApDoctorRound;
import com.asklepios.backend_service.model.generated.entity.ApDoctorRoundEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDoctorRoundDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDoctorRound getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_doctor_round where key = '"+key+"'");) {
ApDoctorRound record = new ApDoctorRound();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setRoundStartTime(rs.getBigDecimal("round_start_time"));
record.setPractitionerKey(rs.getString("practitioner_key"));
record.setShiftLkey(rs.getString("shift_lkey"));
record.setInitialNote(rs.getString("initial_note"));
record.setProgressNote(rs.getString("progress_note"));
record.setSpecialEventNote(rs.getString("special_event_note"));
record.setPrimaryDiagnosis(rs.getString("primary_diagnosis"));
record.setMajor(rs.getBoolean("major"));
record.setSuspected(rs.getBoolean("suspected"));
record.setClinicalImpression(rs.getString("clinical_impression"));
record.setSecondaryDiagnoses(rs.getString("secondary_diagnoses"));
record.setPatientStatusLkey(rs.getString("patient_status_lkey"));
record.setComplicationsNoted(rs.getString("complications_noted"));
record.setSummaryStatement(rs.getString("summary_statement"));
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
public void updateRecord(ApDoctorRound record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_doctor_round set key = ?, patient_key = ?, encounter_key = ?, round_start_time = ?, practitioner_key = ?, shift_lkey = ?, initial_note = ?, progress_note = ?, special_event_note = ?, primary_diagnosis = ?, major = ?, suspected = ?, clinical_impression = ?, secondary_diagnoses = ?, patient_status_lkey = ?, complications_noted = ?, summary_statement = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setBigDecimal(4, record.getRoundStartTime());
ps.setString(5, record.getPractitionerKey());
ps.setString(6, record.getShiftLkey());
ps.setString(7, record.getInitialNote());
ps.setString(8, record.getProgressNote());
ps.setString(9, record.getSpecialEventNote());
ps.setString(10, record.getPrimaryDiagnosis());
ps.setBoolean(11, record.getMajor());
ps.setBoolean(12, record.getSuspected());
ps.setString(13, record.getClinicalImpression());
ps.setString(14, record.getSecondaryDiagnoses());
ps.setString(15, record.getPatientStatusLkey());
ps.setString(16, record.getComplicationsNoted());
ps.setString(17, record.getSummaryStatement());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setString(24, record.getStatusLkey());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDoctorRound record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_doctor_round set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDoctorRound> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_doctor_round where "+ where);) {
List<ApDoctorRound> list = new ArrayList<ApDoctorRound>();
while(rs.next()){
ApDoctorRound record = new ApDoctorRound();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setRoundStartTime(rs.getBigDecimal("round_start_time"));
record.setPractitionerKey(rs.getString("practitioner_key"));
record.setShiftLkey(rs.getString("shift_lkey"));
record.setInitialNote(rs.getString("initial_note"));
record.setProgressNote(rs.getString("progress_note"));
record.setSpecialEventNote(rs.getString("special_event_note"));
record.setPrimaryDiagnosis(rs.getString("primary_diagnosis"));
record.setMajor(rs.getBoolean("major"));
record.setSuspected(rs.getBoolean("suspected"));
record.setClinicalImpression(rs.getString("clinical_impression"));
record.setSecondaryDiagnoses(rs.getString("secondary_diagnoses"));
record.setPatientStatusLkey(rs.getString("patient_status_lkey"));
record.setComplicationsNoted(rs.getString("complications_noted"));
record.setSummaryStatement(rs.getString("summary_statement"));
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
public String saveRecord(ApDoctorRound record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_doctor_round values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setBigDecimal(4, record.getRoundStartTime());
ps.setString(5, record.getPractitionerKey());
ps.setString(6, record.getShiftLkey());
ps.setString(7, record.getInitialNote());
ps.setString(8, record.getProgressNote());
ps.setString(9, record.getSpecialEventNote());
ps.setString(10, record.getPrimaryDiagnosis());
ps.setBoolean(11, record.getMajor());
ps.setBoolean(12, record.getSuspected());
ps.setString(13, record.getClinicalImpression());
ps.setString(14, record.getSecondaryDiagnoses());
ps.setString(15, record.getPatientStatusLkey());
ps.setString(16, record.getComplicationsNoted());
ps.setString(17, record.getSummaryStatement());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setString(24, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDoctorRoundEntity entity, String lang) {
        Class<?> myClass = ApDoctorRoundEntity.class;
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
public void translateObject(ApDoctorRoundEntity entity, String lang) {
        ApDoctorRoundEntity translated = (ApDoctorRoundEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}