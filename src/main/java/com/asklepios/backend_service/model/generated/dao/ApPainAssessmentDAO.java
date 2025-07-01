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
import com.asklepios.backend_service.model.generated.pojo.ApPainAssessment;
import com.asklepios.backend_service.model.generated.entity.ApPainAssessmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPainAssessmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPainAssessment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pain_assessment where key = '"+key+"'");) {
ApPainAssessment record = new ApPainAssessment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPainDegreeLkey(rs.getString("pain_degree_lkey"));
record.setPainLocationLkey(rs.getString("pain_location_lkey"));
record.setPainPatternLkey(rs.getString("pain_pattern_lkey"));
record.setOnsetLkey(rs.getString("onset_lkey"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setDuration(rs.getBigDecimal("duration"));
record.setDurationUnitLkey(rs.getString("duration_unit_lkey"));
record.setAggravatingFactors(rs.getString("aggravating_factors"));
record.setRelievingFactors(rs.getString("relieving_factors"));
record.setAssociatedSymptoms(rs.getString("associated_symptoms"));
record.setPainManagementGiven(rs.getString("pain_management_given"));
record.setImpactOnFunction(rs.getBoolean("impact_on_function"));
record.setPainReassessmentRequired(rs.getBoolean("pain_reassessment_required"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCancellationReason(rs.getString("cancellation_reason"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPainAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pain_assessment set key = ?, patient_key = ?, encounter_key = ?, pain_degree_lkey = ?, pain_location_lkey = ?, pain_pattern_lkey = ?, onset_lkey = ?, pain_score_lkey = ?, duration = ?, duration_unit_lkey = ?, aggravating_factors = ?, relieving_factors = ?, associated_symptoms = ?, pain_management_given = ?, impact_on_function = ?, pain_reassessment_required = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, status_lkey = ?, cancellation_reason = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getPainDegreeLkey());
ps.setString(5, record.getPainLocationLkey());
ps.setString(6, record.getPainPatternLkey());
ps.setString(7, record.getOnsetLkey());
ps.setString(8, record.getPainScoreLkey());
ps.setBigDecimal(9, record.getDuration());
ps.setString(10, record.getDurationUnitLkey());
ps.setString(11, record.getAggravatingFactors());
ps.setString(12, record.getRelievingFactors());
ps.setString(13, record.getAssociatedSymptoms());
ps.setString(14, record.getPainManagementGiven());
ps.setBoolean(15, record.getImpactOnFunction());
ps.setBoolean(16, record.getPainReassessmentRequired());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setString(23, record.getStatusLkey());
ps.setString(24, record.getCancellationReason());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPainAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pain_assessment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPainAssessment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pain_assessment where "+ where);) {
List<ApPainAssessment> list = new ArrayList<ApPainAssessment>();
while(rs.next()){
ApPainAssessment record = new ApPainAssessment();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPainDegreeLkey(rs.getString("pain_degree_lkey"));
record.setPainLocationLkey(rs.getString("pain_location_lkey"));
record.setPainPatternLkey(rs.getString("pain_pattern_lkey"));
record.setOnsetLkey(rs.getString("onset_lkey"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setDuration(rs.getBigDecimal("duration"));
record.setDurationUnitLkey(rs.getString("duration_unit_lkey"));
record.setAggravatingFactors(rs.getString("aggravating_factors"));
record.setRelievingFactors(rs.getString("relieving_factors"));
record.setAssociatedSymptoms(rs.getString("associated_symptoms"));
record.setPainManagementGiven(rs.getString("pain_management_given"));
record.setImpactOnFunction(rs.getBoolean("impact_on_function"));
record.setPainReassessmentRequired(rs.getBoolean("pain_reassessment_required"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCancellationReason(rs.getString("cancellation_reason"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPainAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_pain_assessment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getPainDegreeLkey());
ps.setString(5, record.getPainLocationLkey());
ps.setString(6, record.getPainPatternLkey());
ps.setString(7, record.getOnsetLkey());
ps.setString(8, record.getPainScoreLkey());
ps.setBigDecimal(9, record.getDuration());
ps.setString(10, record.getDurationUnitLkey());
ps.setString(11, record.getAggravatingFactors());
ps.setString(12, record.getRelievingFactors());
ps.setString(13, record.getAssociatedSymptoms());
ps.setString(14, record.getPainManagementGiven());
ps.setBoolean(15, record.getImpactOnFunction());
ps.setBoolean(16, record.getPainReassessmentRequired());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setString(23, record.getStatusLkey());
ps.setString(24, record.getCancellationReason());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPainAssessmentEntity entity, String lang) {
        Class<?> myClass = ApPainAssessmentEntity.class;
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
public void translateObject(ApPainAssessmentEntity entity, String lang) {
        ApPainAssessmentEntity translated = (ApPainAssessmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}