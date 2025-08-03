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
import com.asklepios.backend_service.model.generated.pojo.ApOperationAnesthesiaRecovery;
import com.asklepios.backend_service.model.generated.entity.ApOperationAnesthesiaRecoveryEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationAnesthesiaRecoveryDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationAnesthesiaRecovery getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_anesthesia_recovery where key = '"+key+"'");) {
ApOperationAnesthesiaRecovery record = new ApOperationAnesthesiaRecovery();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setAirwayTypeOnArrival(rs.getString("airway_type_on_arrival"));
record.setOxygenGiven(rs.getBoolean("oxygen_given"));
record.setOxygenFlowLpm(rs.getBigDecimal("oxygen_flow_lpm"));
record.setExtubationTime(rs.getBigDecimal("extubation_time"));
record.setExtubationStatus(rs.getString("extubation_status"));
record.setConsciousnessLevelLkey(rs.getString("consciousness_level_lkey"));
record.setPainLevelLkey(rs.getString("pain_level_lkey"));
record.setNauseaVomiting(rs.getBoolean("nausea_vomiting"));
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
public void updateRecord(ApOperationAnesthesiaRecovery record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_anesthesia_recovery set key = ?, operation_request_key = ?, airway_type_on_arrival = ?, oxygen_given = ?, oxygen_flow_lpm = ?, extubation_time = ?, extubation_status = ?, consciousness_level_lkey = ?, pain_level_lkey = ?, nausea_vomiting = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getAirwayTypeOnArrival());
ps.setBoolean(4, record.getOxygenGiven());
ps.setBigDecimal(5, record.getOxygenFlowLpm());
ps.setBigDecimal(6, record.getExtubationTime());
ps.setString(7, record.getExtubationStatus());
ps.setString(8, record.getConsciousnessLevelLkey());
ps.setString(9, record.getPainLevelLkey());
ps.setBoolean(10, record.getNauseaVomiting());
ps.setString(11, record.getCreatedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setString(13, record.getUpdatedBy());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsvalid());
ps.setString(18, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationAnesthesiaRecovery record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_anesthesia_recovery set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationAnesthesiaRecovery> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_anesthesia_recovery where "+ where);) {
List<ApOperationAnesthesiaRecovery> list = new ArrayList<ApOperationAnesthesiaRecovery>();
while(rs.next()){
ApOperationAnesthesiaRecovery record = new ApOperationAnesthesiaRecovery();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setAirwayTypeOnArrival(rs.getString("airway_type_on_arrival"));
record.setOxygenGiven(rs.getBoolean("oxygen_given"));
record.setOxygenFlowLpm(rs.getBigDecimal("oxygen_flow_lpm"));
record.setExtubationTime(rs.getBigDecimal("extubation_time"));
record.setExtubationStatus(rs.getString("extubation_status"));
record.setConsciousnessLevelLkey(rs.getString("consciousness_level_lkey"));
record.setPainLevelLkey(rs.getString("pain_level_lkey"));
record.setNauseaVomiting(rs.getBoolean("nausea_vomiting"));
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
public String saveRecord(ApOperationAnesthesiaRecovery record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_anesthesia_recovery values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getAirwayTypeOnArrival());
ps.setBoolean(4, record.getOxygenGiven());
ps.setBigDecimal(5, record.getOxygenFlowLpm());
ps.setBigDecimal(6, record.getExtubationTime());
ps.setString(7, record.getExtubationStatus());
ps.setString(8, record.getConsciousnessLevelLkey());
ps.setString(9, record.getPainLevelLkey());
ps.setBoolean(10, record.getNauseaVomiting());
ps.setString(11, record.getCreatedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setString(13, record.getUpdatedBy());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationAnesthesiaRecoveryEntity entity, String lang) {
        Class<?> myClass = ApOperationAnesthesiaRecoveryEntity.class;
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
public void translateObject(ApOperationAnesthesiaRecoveryEntity entity, String lang) {
        ApOperationAnesthesiaRecoveryEntity translated = (ApOperationAnesthesiaRecoveryEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}