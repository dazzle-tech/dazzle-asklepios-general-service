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
import com.asklepios.backend_service.model.generated.pojo.ApElectrocardiogramEcg;
import com.asklepios.backend_service.model.generated.entity.ApElectrocardiogramEcgEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApElectrocardiogramEcgDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApElectrocardiogramEcg getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_electrocardiogram_ecg where key = '"+key+"'");) {
ApElectrocardiogramEcg record = new ApElectrocardiogramEcg();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setIndication(rs.getString("indication"));
record.setEcgLeadType(rs.getString("ecg_lead_type"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setPrInterval(rs.getBigDecimal("pr_interval"));
record.setQrsDuration(rs.getBigDecimal("qrs_duration"));
record.setQtInterval(rs.getBigDecimal("qt_interval"));
record.setStSegmentChangesLkey(rs.getString("st_segment_changes_lkey"));
record.setWaveAbnormalitiesLkey(rs.getString("wave_abnormalities_lkey"));
record.setRhythmAnalysis(rs.getString("rhythm_analysis"));
record.setEcgInterpretation(rs.getString("ecg_interpretation"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApElectrocardiogramEcg record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_electrocardiogram_ecg set key = ?, patient_key = ?, encounter_key = ?, indication = ?, ecg_lead_type = ?, heart_rate = ?, pr_interval = ?, qrs_duration = ?, qt_interval = ?, st_segment_changes_lkey = ?, wave_abnormalities_lkey = ?, rhythm_analysis = ?, ecg_interpretation = ?, cancellation_reason = ?, status_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getIndication());
ps.setString(5, record.getEcgLeadType());
ps.setBigDecimal(6, record.getHeartRate());
ps.setBigDecimal(7, record.getPrInterval());
ps.setBigDecimal(8, record.getQrsDuration());
ps.setBigDecimal(9, record.getQtInterval());
ps.setString(10, record.getStSegmentChangesLkey());
ps.setString(11, record.getWaveAbnormalitiesLkey());
ps.setString(12, record.getRhythmAnalysis());
ps.setString(13, record.getEcgInterpretation());
ps.setString(14, record.getCancellationReason());
ps.setString(15, record.getStatusLkey());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setString(22, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApElectrocardiogramEcg record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_electrocardiogram_ecg set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApElectrocardiogramEcg> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_electrocardiogram_ecg where "+ where);) {
List<ApElectrocardiogramEcg> list = new ArrayList<ApElectrocardiogramEcg>();
while(rs.next()){
ApElectrocardiogramEcg record = new ApElectrocardiogramEcg();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setIndication(rs.getString("indication"));
record.setEcgLeadType(rs.getString("ecg_lead_type"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setPrInterval(rs.getBigDecimal("pr_interval"));
record.setQrsDuration(rs.getBigDecimal("qrs_duration"));
record.setQtInterval(rs.getBigDecimal("qt_interval"));
record.setStSegmentChangesLkey(rs.getString("st_segment_changes_lkey"));
record.setWaveAbnormalitiesLkey(rs.getString("wave_abnormalities_lkey"));
record.setRhythmAnalysis(rs.getString("rhythm_analysis"));
record.setEcgInterpretation(rs.getString("ecg_interpretation"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApElectrocardiogramEcg record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_electrocardiogram_ecg values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getIndication());
ps.setString(5, record.getEcgLeadType());
ps.setBigDecimal(6, record.getHeartRate());
ps.setBigDecimal(7, record.getPrInterval());
ps.setBigDecimal(8, record.getQrsDuration());
ps.setBigDecimal(9, record.getQtInterval());
ps.setString(10, record.getStSegmentChangesLkey());
ps.setString(11, record.getWaveAbnormalitiesLkey());
ps.setString(12, record.getRhythmAnalysis());
ps.setString(13, record.getEcgInterpretation());
ps.setString(14, record.getCancellationReason());
ps.setString(15, record.getStatusLkey());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApElectrocardiogramEcgEntity entity, String lang) {
        Class<?> myClass = ApElectrocardiogramEcgEntity.class;
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
public void translateObject(ApElectrocardiogramEcgEntity entity, String lang) {
        ApElectrocardiogramEcgEntity translated = (ApElectrocardiogramEcgEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}