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
import com.asklepios.backend_service.model.generated.pojo.ApPatientObservation;
import com.asklepios.backend_service.model.generated.entity.ApPatientObservationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientObservationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientObservation getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_observation where key = '"+key+"'");) {
ApPatientObservation record = new ApPatientObservation();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setObservationDate(rs.getDate("observation_date"));
record.setObservationTypeLkey(rs.getString("observation_type_lkey"));
record.setValue(rs.getString("value"));
record.setValue2(rs.getString("value2"));
record.setUnitofMeasureLkey(rs.getString("unitof_measure_lkey"));
record.setReferencerangeLkey(rs.getString("referencerange_lkey"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setMethodLkey(rs.getString("method_lkey"));
record.setPositionLkey(rs.getString("position_lkey"));
record.setSiteLkey(rs.getString("site_lkey"));
record.setEquipmentUsedLkey(rs.getString("equipment_used_lkey"));
record.setComments(rs.getString("comments"));
record.setSourceRecordKey(rs.getString("source_record_key"));
record.setProviderKey(rs.getString("provider_key"));
record.setProviderName(rs.getString("provider_name"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientObservation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_observation set key = ?, patient_key = ?, visit_key = ?, observation_date = ?, observation_type_lkey = ?, value = ?, value2 = ?, unitof_measure_lkey = ?, referencerange_lkey = ?, status_lkey = ?, method_lkey = ?, position_lkey = ?, site_lkey = ?, equipment_used_lkey = ?, comments = ?, source_record_key = ?, provider_key = ?, provider_name = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
if (record.getObservationDate() != null) ps.setDate(4, new java.sql.Date(record.getObservationDate().getTime()));
else ps.setDate(4, null); 
ps.setString(5, record.getObservationTypeLkey());
ps.setString(6, record.getValue());
ps.setString(7, record.getValue2());
ps.setString(8, record.getUnitofMeasureLkey());
ps.setString(9, record.getReferencerangeLkey());
ps.setString(10, record.getStatusLkey());
ps.setString(11, record.getMethodLkey());
ps.setString(12, record.getPositionLkey());
ps.setString(13, record.getSiteLkey());
ps.setString(14, record.getEquipmentUsedLkey());
ps.setString(15, record.getComments());
ps.setString(16, record.getSourceRecordKey());
ps.setString(17, record.getProviderKey());
ps.setString(18, record.getProviderName());
ps.setString(19, record.getCreatedBy());
ps.setString(20, record.getUpdatedBy());
ps.setString(21, record.getDeletedBy());
ps.setBigDecimal(22, record.getCreatedAt());
ps.setBigDecimal(23, record.getUpdatedAt());
ps.setBigDecimal(24, record.getDeletedAt());
ps.setBoolean(25, record.getIsValid());
ps.setString(26, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientObservation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_observation set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientObservation> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_observation where "+ where);) {
List<ApPatientObservation> list = new ArrayList<ApPatientObservation>();
while(rs.next()){
ApPatientObservation record = new ApPatientObservation();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setObservationDate(rs.getDate("observation_date"));
record.setObservationTypeLkey(rs.getString("observation_type_lkey"));
record.setValue(rs.getString("value"));
record.setValue2(rs.getString("value2"));
record.setUnitofMeasureLkey(rs.getString("unitof_measure_lkey"));
record.setReferencerangeLkey(rs.getString("referencerange_lkey"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setMethodLkey(rs.getString("method_lkey"));
record.setPositionLkey(rs.getString("position_lkey"));
record.setSiteLkey(rs.getString("site_lkey"));
record.setEquipmentUsedLkey(rs.getString("equipment_used_lkey"));
record.setComments(rs.getString("comments"));
record.setSourceRecordKey(rs.getString("source_record_key"));
record.setProviderKey(rs.getString("provider_key"));
record.setProviderName(rs.getString("provider_name"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientObservation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_observation values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
if (record.getObservationDate() != null) ps.setDate(4, new java.sql.Date(record.getObservationDate().getTime()));
else ps.setDate(4, null); 
ps.setString(5, record.getObservationTypeLkey());
ps.setString(6, record.getValue());
ps.setString(7, record.getValue2());
ps.setString(8, record.getUnitofMeasureLkey());
ps.setString(9, record.getReferencerangeLkey());
ps.setString(10, record.getStatusLkey());
ps.setString(11, record.getMethodLkey());
ps.setString(12, record.getPositionLkey());
ps.setString(13, record.getSiteLkey());
ps.setString(14, record.getEquipmentUsedLkey());
ps.setString(15, record.getComments());
ps.setString(16, record.getSourceRecordKey());
ps.setString(17, record.getProviderKey());
ps.setString(18, record.getProviderName());
ps.setString(19, record.getCreatedBy());
ps.setString(20, record.getUpdatedBy());
ps.setString(21, record.getDeletedBy());
ps.setBigDecimal(22, record.getCreatedAt());
ps.setBigDecimal(23, record.getUpdatedAt());
ps.setBigDecimal(24, record.getDeletedAt());
ps.setBoolean(25, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientObservationEntity entity, String lang) {
        Class<?> myClass = ApPatientObservationEntity.class;
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
public void translateObject(ApPatientObservationEntity entity, String lang) {
        ApPatientObservationEntity translated = (ApPatientObservationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}