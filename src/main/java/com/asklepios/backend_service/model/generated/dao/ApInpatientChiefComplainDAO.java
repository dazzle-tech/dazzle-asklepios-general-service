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
import com.asklepios.backend_service.model.generated.pojo.ApInpatientChiefComplain;
import com.asklepios.backend_service.model.generated.entity.ApInpatientChiefComplainEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApInpatientChiefComplainDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApInpatientChiefComplain getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inpatient_chief_complain where key = '"+key+"'");) {
ApInpatientChiefComplain record = new ApInpatientChiefComplain();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setChiefComplaint(rs.getString("chief_complaint"));
record.setProvocation(rs.getString("provocation"));
record.setPalliation(rs.getString("palliation"));
record.setQualityLkey(rs.getString("quality_lkey"));
record.setRegionLkey(rs.getString("region_lkey"));
record.setOnsetDateTime(rs.getBigDecimal("onset_date_time"));
record.setUnderstanding(rs.getString("understanding"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setSeverityLkey(rs.getString("severity_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApInpatientChiefComplain record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inpatient_chief_complain set key = ?, patient_key = ?, encounter_key = ?, chief_complaint = ?, provocation = ?, palliation = ?, quality_lkey = ?, region_lkey = ?, onset_date_time = ?, understanding = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ?, severity_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getChiefComplaint());
ps.setString(5, record.getProvocation());
ps.setString(6, record.getPalliation());
ps.setString(7, record.getQualityLkey());
ps.setString(8, record.getRegionLkey());
ps.setBigDecimal(9, record.getOnsetDateTime());
ps.setString(10, record.getUnderstanding());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setString(17, record.getCancellationReason());
ps.setString(18, record.getStatusLkey());
ps.setString(19, record.getSeverityLkey());
ps.setString(20, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApInpatientChiefComplain record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inpatient_chief_complain set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApInpatientChiefComplain> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inpatient_chief_complain where "+ where);) {
List<ApInpatientChiefComplain> list = new ArrayList<ApInpatientChiefComplain>();
while(rs.next()){
ApInpatientChiefComplain record = new ApInpatientChiefComplain();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setChiefComplaint(rs.getString("chief_complaint"));
record.setProvocation(rs.getString("provocation"));
record.setPalliation(rs.getString("palliation"));
record.setQualityLkey(rs.getString("quality_lkey"));
record.setRegionLkey(rs.getString("region_lkey"));
record.setOnsetDateTime(rs.getBigDecimal("onset_date_time"));
record.setUnderstanding(rs.getString("understanding"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setSeverityLkey(rs.getString("severity_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApInpatientChiefComplain record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_inpatient_chief_complain values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getChiefComplaint());
ps.setString(5, record.getProvocation());
ps.setString(6, record.getPalliation());
ps.setString(7, record.getQualityLkey());
ps.setString(8, record.getRegionLkey());
ps.setBigDecimal(9, record.getOnsetDateTime());
ps.setString(10, record.getUnderstanding());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setString(17, record.getCancellationReason());
ps.setString(18, record.getStatusLkey());
ps.setString(19, record.getSeverityLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApInpatientChiefComplainEntity entity, String lang) {
        Class<?> myClass = ApInpatientChiefComplainEntity.class;
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
public void translateObject(ApInpatientChiefComplainEntity entity, String lang) {
        ApInpatientChiefComplainEntity translated = (ApInpatientChiefComplainEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}