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
import com.asklepios.backend_service.model.generated.pojo.ApAdmitOutpatientInpatient;
import com.asklepios.backend_service.model.generated.entity.ApAdmitOutpatientInpatientEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApAdmitOutpatientInpatientDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApAdmitOutpatientInpatient getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_admit_outpatient_inpatient where key = '"+key+"'");) {
ApAdmitOutpatientInpatient record = new ApAdmitOutpatientInpatient();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setToEncounterKey(rs.getString("to_encounter_key"));
record.setFromEncounterKey(rs.getString("from_encounter_key"));
record.setInpatientDepartmentKey(rs.getString("inpatient_department_key"));
record.setPhysicianKey(rs.getString("physician_key"));
record.setAdmissionNotes(rs.getString("admission_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setAdmitSourceLkey(rs.getString("admit_source_lkey"));
record.setAdmissionDepartmentKey(rs.getString("admission_department_key"));
record.setRoomKey(rs.getString("room_key"));
record.setBedKey(rs.getString("bed_key"));
record.setHandoffInformation(rs.getString("handoff_information"));
record.setIcd10(rs.getString("icd_10"));
record.setReasonOfAdmission(rs.getString("reason_of_admission"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApAdmitOutpatientInpatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_admit_outpatient_inpatient set key = ?, to_encounter_key = ?, from_encounter_key = ?, inpatient_department_key = ?, physician_key = ?, admission_notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, admit_source_lkey = ?, admission_department_key = ?, room_key = ?, bed_key = ?, handoff_information = ?, icd_10 = ?, reason_of_admission = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getToEncounterKey());
ps.setString(3, record.getFromEncounterKey());
ps.setString(4, record.getInpatientDepartmentKey());
ps.setString(5, record.getPhysicianKey());
ps.setString(6, record.getAdmissionNotes());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setString(13, record.getAdmitSourceLkey());
ps.setString(14, record.getAdmissionDepartmentKey());
ps.setString(15, record.getRoomKey());
ps.setString(16, record.getBedKey());
ps.setString(17, record.getHandoffInformation());
ps.setString(18, record.getIcd10());
ps.setString(19, record.getReasonOfAdmission());
ps.setString(20, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApAdmitOutpatientInpatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_admit_outpatient_inpatient set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApAdmitOutpatientInpatient> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_admit_outpatient_inpatient where "+ where);) {
List<ApAdmitOutpatientInpatient> list = new ArrayList<ApAdmitOutpatientInpatient>();
while(rs.next()){
ApAdmitOutpatientInpatient record = new ApAdmitOutpatientInpatient();
record.setKey(rs.getString("key"));
record.setToEncounterKey(rs.getString("to_encounter_key"));
record.setFromEncounterKey(rs.getString("from_encounter_key"));
record.setInpatientDepartmentKey(rs.getString("inpatient_department_key"));
record.setPhysicianKey(rs.getString("physician_key"));
record.setAdmissionNotes(rs.getString("admission_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setAdmitSourceLkey(rs.getString("admit_source_lkey"));
record.setAdmissionDepartmentKey(rs.getString("admission_department_key"));
record.setRoomKey(rs.getString("room_key"));
record.setBedKey(rs.getString("bed_key"));
record.setHandoffInformation(rs.getString("handoff_information"));
record.setIcd10(rs.getString("icd_10"));
record.setReasonOfAdmission(rs.getString("reason_of_admission"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApAdmitOutpatientInpatient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_admit_outpatient_inpatient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getToEncounterKey());
ps.setString(3, record.getFromEncounterKey());
ps.setString(4, record.getInpatientDepartmentKey());
ps.setString(5, record.getPhysicianKey());
ps.setString(6, record.getAdmissionNotes());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setString(13, record.getAdmitSourceLkey());
ps.setString(14, record.getAdmissionDepartmentKey());
ps.setString(15, record.getRoomKey());
ps.setString(16, record.getBedKey());
ps.setString(17, record.getHandoffInformation());
ps.setString(18, record.getIcd10());
ps.setString(19, record.getReasonOfAdmission());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApAdmitOutpatientInpatientEntity entity, String lang) {
        Class<?> myClass = ApAdmitOutpatientInpatientEntity.class;
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
public void translateObject(ApAdmitOutpatientInpatientEntity entity, String lang) {
        ApAdmitOutpatientInpatientEntity translated = (ApAdmitOutpatientInpatientEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}