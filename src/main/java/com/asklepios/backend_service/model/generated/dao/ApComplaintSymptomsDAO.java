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
import com.asklepios.backend_service.model.generated.pojo.ApComplaintSymptoms;
import com.asklepios.backend_service.model.generated.entity.ApComplaintSymptomsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApComplaintSymptomsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApComplaintSymptoms getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_complaint_symptoms where key = '"+key+"'");) {
ApComplaintSymptoms record = new ApComplaintSymptoms();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setChiefComplaint(rs.getString("chief_complaint"));
record.setOnsetDate(rs.getBigDecimal("onset_date"));
record.setDuration(rs.getBigDecimal("duration"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setPainCharacteristics(rs.getString("pain_characteristics"));
record.setPainLocationLkey(rs.getString("pain_location_lkey"));
record.setRadiation(rs.getString("radiation"));
record.setAggravatingFactors(rs.getString("aggravating_factors"));
record.setRelievingFactors(rs.getString("relieving_factors"));
record.setAssociatedSymptoms(rs.getString("associated_symptoms"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApComplaintSymptoms record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_complaint_symptoms set key = ?, patient_key = ?, encounter_key = ?, chief_complaint = ?, onset_date = ?, duration = ?, unit_lkey = ?, pain_characteristics = ?, pain_location_lkey = ?, radiation = ?, aggravating_factors = ?, relieving_factors = ?, associated_symptoms = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getChiefComplaint());
ps.setBigDecimal(5, record.getOnsetDate());
ps.setBigDecimal(6, record.getDuration());
ps.setString(7, record.getUnitLkey());
ps.setString(8, record.getPainCharacteristics());
ps.setString(9, record.getPainLocationLkey());
ps.setString(10, record.getRadiation());
ps.setString(11, record.getAggravatingFactors());
ps.setString(12, record.getRelievingFactors());
ps.setString(13, record.getAssociatedSymptoms());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setString(20, record.getCancellationReason());
ps.setString(21, record.getStatusLkey());
ps.setString(22, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApComplaintSymptoms record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_complaint_symptoms set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApComplaintSymptoms> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_complaint_symptoms where "+ where);) {
List<ApComplaintSymptoms> list = new ArrayList<ApComplaintSymptoms>();
while(rs.next()){
ApComplaintSymptoms record = new ApComplaintSymptoms();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setChiefComplaint(rs.getString("chief_complaint"));
record.setOnsetDate(rs.getBigDecimal("onset_date"));
record.setDuration(rs.getBigDecimal("duration"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setPainCharacteristics(rs.getString("pain_characteristics"));
record.setPainLocationLkey(rs.getString("pain_location_lkey"));
record.setRadiation(rs.getString("radiation"));
record.setAggravatingFactors(rs.getString("aggravating_factors"));
record.setRelievingFactors(rs.getString("relieving_factors"));
record.setAssociatedSymptoms(rs.getString("associated_symptoms"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApComplaintSymptoms record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_complaint_symptoms values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getChiefComplaint());
ps.setBigDecimal(5, record.getOnsetDate());
ps.setBigDecimal(6, record.getDuration());
ps.setString(7, record.getUnitLkey());
ps.setString(8, record.getPainCharacteristics());
ps.setString(9, record.getPainLocationLkey());
ps.setString(10, record.getRadiation());
ps.setString(11, record.getAggravatingFactors());
ps.setString(12, record.getRelievingFactors());
ps.setString(13, record.getAssociatedSymptoms());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setString(20, record.getCancellationReason());
ps.setString(21, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApComplaintSymptomsEntity entity, String lang) {
        Class<?> myClass = ApComplaintSymptomsEntity.class;
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
public void translateObject(ApComplaintSymptomsEntity entity, String lang) {
        ApComplaintSymptomsEntity translated = (ApComplaintSymptomsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}