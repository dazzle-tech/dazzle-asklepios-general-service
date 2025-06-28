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
import com.asklepios.backend_service.model.generated.pojo.ApPostProcedureAnesthesia;
import com.asklepios.backend_service.model.generated.entity.ApPostProcedureAnesthesiaEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPostProcedureAnesthesiaDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPostProcedureAnesthesia getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_procedure_anesthesia where key = '"+key+"'");) {
ApPostProcedureAnesthesia record = new ApPostProcedureAnesthesia();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setActivityLkey(rs.getString("activity_lkey"));
record.setRespirationLkey(rs.getString("respiration_lkey"));
record.setCirculationLkey(rs.getString("circulation_lkey"));
record.setConsciousnessLkey(rs.getString("consciousness_lkey"));
record.setOxygenSaturationLkey(rs.getString("oxygen_saturation_lkey"));
record.setAldreteScore(rs.getBigDecimal("aldrete_score"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPostProcedureAnesthesia record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_procedure_anesthesia set key = ?, procedure_key = ?, activity_lkey = ?, respiration_lkey = ?, circulation_lkey = ?, consciousness_lkey = ?, oxygen_saturation_lkey = ?, aldrete_score = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureKey());
ps.setString(3, record.getActivityLkey());
ps.setString(4, record.getRespirationLkey());
ps.setString(5, record.getCirculationLkey());
ps.setString(6, record.getConsciousnessLkey());
ps.setString(7, record.getOxygenSaturationLkey());
ps.setBigDecimal(8, record.getAldreteScore());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsvalid());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPostProcedureAnesthesia record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_procedure_anesthesia set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPostProcedureAnesthesia> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_procedure_anesthesia where "+ where);) {
List<ApPostProcedureAnesthesia> list = new ArrayList<ApPostProcedureAnesthesia>();
while(rs.next()){
ApPostProcedureAnesthesia record = new ApPostProcedureAnesthesia();
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setActivityLkey(rs.getString("activity_lkey"));
record.setRespirationLkey(rs.getString("respiration_lkey"));
record.setCirculationLkey(rs.getString("circulation_lkey"));
record.setConsciousnessLkey(rs.getString("consciousness_lkey"));
record.setOxygenSaturationLkey(rs.getString("oxygen_saturation_lkey"));
record.setAldreteScore(rs.getBigDecimal("aldrete_score"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPostProcedureAnesthesia record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_post_procedure_anesthesia values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureKey());
ps.setString(3, record.getActivityLkey());
ps.setString(4, record.getRespirationLkey());
ps.setString(5, record.getCirculationLkey());
ps.setString(6, record.getConsciousnessLkey());
ps.setString(7, record.getOxygenSaturationLkey());
ps.setBigDecimal(8, record.getAldreteScore());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPostProcedureAnesthesiaEntity entity, String lang) {
        Class<?> myClass = ApPostProcedureAnesthesiaEntity.class;
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
public void translateObject(ApPostProcedureAnesthesiaEntity entity, String lang) {
        ApPostProcedureAnesthesiaEntity translated = (ApPostProcedureAnesthesiaEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}