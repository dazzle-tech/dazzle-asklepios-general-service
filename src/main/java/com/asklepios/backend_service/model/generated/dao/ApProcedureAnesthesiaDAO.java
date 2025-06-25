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
import com.asklepios.backend_service.model.generated.pojo.ApProcedureAnesthesia;
import com.asklepios.backend_service.model.generated.entity.ApProcedureAnesthesiaEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApProcedureAnesthesiaDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApProcedureAnesthesia getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_anesthesia where key = '"+key+"'");) {
ApProcedureAnesthesia record = new ApProcedureAnesthesia();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setAirwayGradeLkey(rs.getString("airway_grade_lkey"));
record.setAsaScoreLkey(rs.getString("asa_score_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApProcedureAnesthesia record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_anesthesia set key = ?, procedure_key = ?, airway_grade_lkey = ?, asa_score_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, encounter_key = ?, patient_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureKey());
ps.setString(3, record.getAirwayGradeLkey());
ps.setString(4, record.getAsaScoreLkey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setString(11, record.getEncounterKey());
ps.setString(12, record.getPatientKey());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApProcedureAnesthesia record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_anesthesia set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApProcedureAnesthesia> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_anesthesia where "+ where);) {
List<ApProcedureAnesthesia> list = new ArrayList<ApProcedureAnesthesia>();
while(rs.next()){
ApProcedureAnesthesia record = new ApProcedureAnesthesia();
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setAirwayGradeLkey(rs.getString("airway_grade_lkey"));
record.setAsaScoreLkey(rs.getString("asa_score_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApProcedureAnesthesia record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_procedure_anesthesia values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureKey());
ps.setString(3, record.getAirwayGradeLkey());
ps.setString(4, record.getAsaScoreLkey());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setString(11, record.getEncounterKey());
ps.setString(12, record.getPatientKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApProcedureAnesthesiaEntity entity, String lang) {
        Class<?> myClass = ApProcedureAnesthesiaEntity.class;
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
public void translateObject(ApProcedureAnesthesiaEntity entity, String lang) {
        ApProcedureAnesthesiaEntity translated = (ApProcedureAnesthesiaEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}