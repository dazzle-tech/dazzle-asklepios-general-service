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
import com.asklepios.backend_service.model.generated.pojo.ApPatientSocialHistory;
import com.asklepios.backend_service.model.generated.entity.ApPatientSocialHistoryEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientSocialHistoryDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientSocialHistory getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_social_history where key = '"+key+"'");) {
ApPatientSocialHistory record = new ApPatientSocialHistory();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setCurrentSmoker(rs.getBoolean("current_smoker"));
record.setSmokeStartDate(rs.getBigDecimal("smoke_start_date"));
record.setCigaretteAmount(rs.getBigDecimal("cigarette_amount"));
record.setCigaretteType(rs.getString("cigarette_type"));
record.setPreviousSmoker(rs.getBoolean("previous_smoker"));
record.setSmokeQuitDate(rs.getBigDecimal("smoke_quit_date"));
record.setExposureToSecondHandSmoke(rs.getBoolean("exposure_to_second_hand_smoke"));
record.setAlcoholConsumption(rs.getBoolean("alcohol_consumption"));
record.setTypeOfAlcohol(rs.getString("type_of_alcohol"));
record.setAlcoholSinceWhen(rs.getBigDecimal("alcohol_since_when"));
record.setSubstanceUse(rs.getBoolean("substance_use"));
record.setRouteLkey(rs.getString("route_lkey"));
record.setFrequencyLkey(rs.getString("frequency_lkey"));
record.setPhysicalLimitationLkey(rs.getString("physical_limitation_lkey"));
record.setDiagnosedEatingDisordersLkey(rs.getString("diagnosed_eating_disorders_lkey"));
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
public void updateRecord(ApPatientSocialHistory record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_social_history set key = ?, patient_key = ?, current_smoker = ?, smoke_start_date = ?, cigarette_amount = ?, cigarette_type = ?, previous_smoker = ?, smoke_quit_date = ?, exposure_to_second_hand_smoke = ?, alcohol_consumption = ?, type_of_alcohol = ?, alcohol_since_when = ?, substance_use = ?, route_lkey = ?, frequency_lkey = ?, physical_limitation_lkey = ?, diagnosed_eating_disorders_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setBoolean(3, record.getCurrentSmoker());
ps.setBigDecimal(4, record.getSmokeStartDate());
ps.setBigDecimal(5, record.getCigaretteAmount());
ps.setString(6, record.getCigaretteType());
ps.setBoolean(7, record.getPreviousSmoker());
ps.setBigDecimal(8, record.getSmokeQuitDate());
ps.setBoolean(9, record.getExposureToSecondHandSmoke());
ps.setBoolean(10, record.getAlcoholConsumption());
ps.setString(11, record.getTypeOfAlcohol());
ps.setBigDecimal(12, record.getAlcoholSinceWhen());
ps.setBoolean(13, record.getSubstanceUse());
ps.setString(14, record.getRouteLkey());
ps.setString(15, record.getFrequencyLkey());
ps.setString(16, record.getPhysicalLimitationLkey());
ps.setString(17, record.getDiagnosedEatingDisordersLkey());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientSocialHistory record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_social_history set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientSocialHistory> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_social_history where "+ where);) {
List<ApPatientSocialHistory> list = new ArrayList<ApPatientSocialHistory>();
while(rs.next()){
ApPatientSocialHistory record = new ApPatientSocialHistory();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setCurrentSmoker(rs.getBoolean("current_smoker"));
record.setSmokeStartDate(rs.getBigDecimal("smoke_start_date"));
record.setCigaretteAmount(rs.getBigDecimal("cigarette_amount"));
record.setCigaretteType(rs.getString("cigarette_type"));
record.setPreviousSmoker(rs.getBoolean("previous_smoker"));
record.setSmokeQuitDate(rs.getBigDecimal("smoke_quit_date"));
record.setExposureToSecondHandSmoke(rs.getBoolean("exposure_to_second_hand_smoke"));
record.setAlcoholConsumption(rs.getBoolean("alcohol_consumption"));
record.setTypeOfAlcohol(rs.getString("type_of_alcohol"));
record.setAlcoholSinceWhen(rs.getBigDecimal("alcohol_since_when"));
record.setSubstanceUse(rs.getBoolean("substance_use"));
record.setRouteLkey(rs.getString("route_lkey"));
record.setFrequencyLkey(rs.getString("frequency_lkey"));
record.setPhysicalLimitationLkey(rs.getString("physical_limitation_lkey"));
record.setDiagnosedEatingDisordersLkey(rs.getString("diagnosed_eating_disorders_lkey"));
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
public String saveRecord(ApPatientSocialHistory record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_social_history values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setBoolean(3, record.getCurrentSmoker());
ps.setBigDecimal(4, record.getSmokeStartDate());
ps.setBigDecimal(5, record.getCigaretteAmount());
ps.setString(6, record.getCigaretteType());
ps.setBoolean(7, record.getPreviousSmoker());
ps.setBigDecimal(8, record.getSmokeQuitDate());
ps.setBoolean(9, record.getExposureToSecondHandSmoke());
ps.setBoolean(10, record.getAlcoholConsumption());
ps.setString(11, record.getTypeOfAlcohol());
ps.setBigDecimal(12, record.getAlcoholSinceWhen());
ps.setBoolean(13, record.getSubstanceUse());
ps.setString(14, record.getRouteLkey());
ps.setString(15, record.getFrequencyLkey());
ps.setString(16, record.getPhysicalLimitationLkey());
ps.setString(17, record.getDiagnosedEatingDisordersLkey());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientSocialHistoryEntity entity, String lang) {
        Class<?> myClass = ApPatientSocialHistoryEntity.class;
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
public void translateObject(ApPatientSocialHistoryEntity entity, String lang) {
        ApPatientSocialHistoryEntity translated = (ApPatientSocialHistoryEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}