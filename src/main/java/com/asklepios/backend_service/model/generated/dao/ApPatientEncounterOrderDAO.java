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
import com.asklepios.backend_service.model.generated.pojo.ApPatientEncounterOrder;
import com.asklepios.backend_service.model.generated.entity.ApPatientEncounterOrderEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientEncounterOrderDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientEncounterOrder getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_encounter_order where key = '"+key+"'");) {
ApPatientEncounterOrder record = new ApPatientEncounterOrder();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setTestKey(rs.getString("test_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setReceivedLabLkey(rs.getString("received_lab_lkey"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setSubmitDate(rs.getBigDecimal("submit_date"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientEncounterOrder record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_encounter_order set key = ?, patient_key = ?, visit_key = ?, test_key = ?, status_lkey = ?, received_lab_lkey = ?, reason_lkey = ?, priority_lkey = ?, notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, submit_date = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getTestKey());
ps.setString(5, record.getStatusLkey());
ps.setString(6, record.getReceivedLabLkey());
ps.setString(7, record.getReasonLkey());
ps.setString(8, record.getPriorityLkey());
ps.setString(9, record.getNotes());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.setBigDecimal(17, record.getSubmitDate());
ps.setString(18, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientEncounterOrder record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_encounter_order set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientEncounterOrder> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_encounter_order where "+ where);) {
List<ApPatientEncounterOrder> list = new ArrayList<ApPatientEncounterOrder>();
while(rs.next()){
ApPatientEncounterOrder record = new ApPatientEncounterOrder();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setTestKey(rs.getString("test_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setReceivedLabLkey(rs.getString("received_lab_lkey"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setSubmitDate(rs.getBigDecimal("submit_date"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientEncounterOrder record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_encounter_order values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getTestKey());
ps.setString(5, record.getStatusLkey());
ps.setString(6, record.getReceivedLabLkey());
ps.setString(7, record.getReasonLkey());
ps.setString(8, record.getPriorityLkey());
ps.setString(9, record.getNotes());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.setBigDecimal(17, record.getSubmitDate());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientEncounterOrderEntity entity, String lang) {
        Class<?> myClass = ApPatientEncounterOrderEntity.class;
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
public void translateObject(ApPatientEncounterOrderEntity entity, String lang) {
        ApPatientEncounterOrderEntity translated = (ApPatientEncounterOrderEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}