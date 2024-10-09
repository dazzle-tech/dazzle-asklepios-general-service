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
import com.asklepios.backend_service.model.generated.pojo.ApPhysicalExamArea;
import com.asklepios.backend_service.model.generated.entity.ApPhysicalExamAreaEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPhysicalExamAreaDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPhysicalExamArea getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_physical_exam_area where key = '"+key+"'");) {
ApPhysicalExamArea record = new ApPhysicalExamArea();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPhysicalExamAreaLkey(rs.getString("physical_exam_area_lkey"));
record.setPhysicalExamAreaDetailLkey(rs.getString("physical_exam_area_detail_lkey"));
record.setNotes(rs.getString("notes"));
record.setSourceOfAnswerLkey(rs.getString("source_of_answer_lkey"));
record.setPass(rs.getBoolean("pass"));
record.setPassReasonLkey(rs.getString("pass_reason_lkey"));
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
public void updateRecord(ApPhysicalExamArea record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_physical_exam_area set key = ?, patient_key = ?, encounter_key = ?, physical_exam_area_lkey = ?, physical_exam_area_detail_lkey = ?, notes = ?, source_of_answer_lkey = ?, pass = ?, pass_reason_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getPhysicalExamAreaLkey());
ps.setString(5, record.getPhysicalExamAreaDetailLkey());
ps.setString(6, record.getNotes());
ps.setString(7, record.getSourceOfAnswerLkey());
ps.setBoolean(8, record.getPass());
ps.setString(9, record.getPassReasonLkey());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.setString(17, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPhysicalExamArea record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_physical_exam_area set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPhysicalExamArea> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_physical_exam_area where "+ where);) {
List<ApPhysicalExamArea> list = new ArrayList<ApPhysicalExamArea>();
while(rs.next()){
ApPhysicalExamArea record = new ApPhysicalExamArea();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPhysicalExamAreaLkey(rs.getString("physical_exam_area_lkey"));
record.setPhysicalExamAreaDetailLkey(rs.getString("physical_exam_area_detail_lkey"));
record.setNotes(rs.getString("notes"));
record.setSourceOfAnswerLkey(rs.getString("source_of_answer_lkey"));
record.setPass(rs.getBoolean("pass"));
record.setPassReasonLkey(rs.getString("pass_reason_lkey"));
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
public String saveRecord(ApPhysicalExamArea record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_physical_exam_area values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getPhysicalExamAreaLkey());
ps.setString(5, record.getPhysicalExamAreaDetailLkey());
ps.setString(6, record.getNotes());
ps.setString(7, record.getSourceOfAnswerLkey());
ps.setBoolean(8, record.getPass());
ps.setString(9, record.getPassReasonLkey());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPhysicalExamAreaEntity entity, String lang) {
        Class<?> myClass = ApPhysicalExamAreaEntity.class;
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
public void translateObject(ApPhysicalExamAreaEntity entity, String lang) {
        ApPhysicalExamAreaEntity translated = (ApPhysicalExamAreaEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}