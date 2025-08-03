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
import com.asklepios.backend_service.model.generated.pojo.ApOperationDischargeReadiness;
import com.asklepios.backend_service.model.generated.entity.ApOperationDischargeReadinessEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationDischargeReadinessDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationDischargeReadiness getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_discharge_readiness where key = '"+key+"'");) {
ApOperationDischargeReadiness record = new ApOperationDischargeReadiness();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setActivityLkey(rs.getString("activity_lkey"));
record.setRespirationLkey(rs.getString("respiration_lkey"));
record.setCirculationLkey(rs.getString("circulation_lkey"));
record.setConsciousnessLkey(rs.getString("consciousness_lkey"));
record.setOxygenSaturationLkey(rs.getString("oxygen_saturation_lkey"));
record.setAldreteScore(rs.getString("aldrete_score"));
record.setPainControlled(rs.getBoolean("pain_controlled"));
record.setVitalsStable(rs.getBoolean("vitals_stable"));
record.setFullyAwake(rs.getBoolean("fully_awake"));
record.setMaintainAirway(rs.getBoolean("maintain_airway"));
record.setSiteDressingIntact(rs.getBoolean("site_dressing_intact"));
record.setNauseaControlled(rs.getBoolean("nausea_controlled"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationDischargeReadiness record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_discharge_readiness set key = ?, operation_request_key = ?, activity_lkey = ?, respiration_lkey = ?, circulation_lkey = ?, consciousness_lkey = ?, oxygen_saturation_lkey = ?, aldrete_score = ?, pain_controlled = ?, vitals_stable = ?, fully_awake = ?, maintain_airway = ?, site_dressing_intact = ?, nausea_controlled = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getActivityLkey());
ps.setString(4, record.getRespirationLkey());
ps.setString(5, record.getCirculationLkey());
ps.setString(6, record.getConsciousnessLkey());
ps.setString(7, record.getOxygenSaturationLkey());
ps.setString(8, record.getAldreteScore());
ps.setBoolean(9, record.getPainControlled());
ps.setBoolean(10, record.getVitalsStable());
ps.setBoolean(11, record.getFullyAwake());
ps.setBoolean(12, record.getMaintainAirway());
ps.setBoolean(13, record.getSiteDressingIntact());
ps.setBoolean(14, record.getNauseaControlled());
ps.setString(15, record.getCreatedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setString(17, record.getUpdatedBy());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsvalid());
ps.setString(22, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationDischargeReadiness record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_discharge_readiness set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationDischargeReadiness> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_discharge_readiness where "+ where);) {
List<ApOperationDischargeReadiness> list = new ArrayList<ApOperationDischargeReadiness>();
while(rs.next()){
ApOperationDischargeReadiness record = new ApOperationDischargeReadiness();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setActivityLkey(rs.getString("activity_lkey"));
record.setRespirationLkey(rs.getString("respiration_lkey"));
record.setCirculationLkey(rs.getString("circulation_lkey"));
record.setConsciousnessLkey(rs.getString("consciousness_lkey"));
record.setOxygenSaturationLkey(rs.getString("oxygen_saturation_lkey"));
record.setAldreteScore(rs.getString("aldrete_score"));
record.setPainControlled(rs.getBoolean("pain_controlled"));
record.setVitalsStable(rs.getBoolean("vitals_stable"));
record.setFullyAwake(rs.getBoolean("fully_awake"));
record.setMaintainAirway(rs.getBoolean("maintain_airway"));
record.setSiteDressingIntact(rs.getBoolean("site_dressing_intact"));
record.setNauseaControlled(rs.getBoolean("nausea_controlled"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationDischargeReadiness record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_discharge_readiness values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getActivityLkey());
ps.setString(4, record.getRespirationLkey());
ps.setString(5, record.getCirculationLkey());
ps.setString(6, record.getConsciousnessLkey());
ps.setString(7, record.getOxygenSaturationLkey());
ps.setString(8, record.getAldreteScore());
ps.setBoolean(9, record.getPainControlled());
ps.setBoolean(10, record.getVitalsStable());
ps.setBoolean(11, record.getFullyAwake());
ps.setBoolean(12, record.getMaintainAirway());
ps.setBoolean(13, record.getSiteDressingIntact());
ps.setBoolean(14, record.getNauseaControlled());
ps.setString(15, record.getCreatedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setString(17, record.getUpdatedBy());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationDischargeReadinessEntity entity, String lang) {
        Class<?> myClass = ApOperationDischargeReadinessEntity.class;
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
public void translateObject(ApOperationDischargeReadinessEntity entity, String lang) {
        ApOperationDischargeReadinessEntity translated = (ApOperationDischargeReadinessEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}