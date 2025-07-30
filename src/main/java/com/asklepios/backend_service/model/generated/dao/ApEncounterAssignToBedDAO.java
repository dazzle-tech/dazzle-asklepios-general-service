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
import com.asklepios.backend_service.model.generated.pojo.ApEncounterAssignToBed;
import com.asklepios.backend_service.model.generated.entity.ApEncounterAssignToBedEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApEncounterAssignToBedDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApEncounterAssignToBed getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_encounter_assign_to_bed where key = '"+key+"'");) {
ApEncounterAssignToBed record = new ApEncounterAssignToBed();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setRoomKey(rs.getString("room_key"));
record.setBedKey(rs.getString("bed_key"));
record.setAdmissionReason(rs.getString("admission_reason"));
record.setDepartmentKey(rs.getString("department_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApEncounterAssignToBed record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_encounter_assign_to_bed set key = ?, encounter_key = ?, patient_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, room_key = ?, bed_key = ?, admission_reason = ?, department_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setString(10, record.getRoomKey());
ps.setString(11, record.getBedKey());
ps.setString(12, record.getAdmissionReason());
ps.setString(13, record.getDepartmentKey());
ps.setString(14, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApEncounterAssignToBed record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_encounter_assign_to_bed set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApEncounterAssignToBed> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_encounter_assign_to_bed where "+ where);) {
List<ApEncounterAssignToBed> list = new ArrayList<ApEncounterAssignToBed>();
while(rs.next()){
ApEncounterAssignToBed record = new ApEncounterAssignToBed();
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setRoomKey(rs.getString("room_key"));
record.setBedKey(rs.getString("bed_key"));
record.setAdmissionReason(rs.getString("admission_reason"));
record.setDepartmentKey(rs.getString("department_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApEncounterAssignToBed record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_encounter_assign_to_bed values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setString(10, record.getRoomKey());
ps.setString(11, record.getBedKey());
ps.setString(12, record.getAdmissionReason());
ps.setString(13, record.getDepartmentKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApEncounterAssignToBedEntity entity, String lang) {
        Class<?> myClass = ApEncounterAssignToBedEntity.class;
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
public void translateObject(ApEncounterAssignToBedEntity entity, String lang) {
        ApEncounterAssignToBedEntity translated = (ApEncounterAssignToBedEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}