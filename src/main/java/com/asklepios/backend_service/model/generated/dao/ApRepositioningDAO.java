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
import com.asklepios.backend_service.model.generated.pojo.ApRepositioning;
import com.asklepios.backend_service.model.generated.entity.ApRepositioningEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApRepositioningDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApRepositioning getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_repositioning where key = '"+key+"'");) {
ApRepositioning record = new ApRepositioning();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setNewPositionLkey(rs.getString("new_position_lkey"));
record.setPositionChangeSuccessful(rs.getBoolean("position_change_successful"));
record.setNotes(rs.getString("notes"));
record.setExpectedNextRepositioning(rs.getBigDecimal("expected_next_repositioning"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCancellationReason(rs.getString("cancellation_reason"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApRepositioning record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_repositioning set key = ?, patient_key = ?, encounter_key = ?, new_position_lkey = ?, position_change_successful = ?, notes = ?, expected_next_repositioning = ?, time_unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, status_lkey = ?, cancellation_reason = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getNewPositionLkey());
ps.setBoolean(5, record.getPositionChangeSuccessful());
ps.setString(6, record.getNotes());
ps.setBigDecimal(7, record.getExpectedNextRepositioning());
ps.setString(8, record.getTimeUnitLkey());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setString(15, record.getStatusLkey());
ps.setString(16, record.getCancellationReason());
ps.setString(17, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApRepositioning record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_repositioning set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApRepositioning> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_repositioning where "+ where);) {
List<ApRepositioning> list = new ArrayList<ApRepositioning>();
while(rs.next()){
ApRepositioning record = new ApRepositioning();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setNewPositionLkey(rs.getString("new_position_lkey"));
record.setPositionChangeSuccessful(rs.getBoolean("position_change_successful"));
record.setNotes(rs.getString("notes"));
record.setExpectedNextRepositioning(rs.getBigDecimal("expected_next_repositioning"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCancellationReason(rs.getString("cancellation_reason"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApRepositioning record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_repositioning values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getNewPositionLkey());
ps.setBoolean(5, record.getPositionChangeSuccessful());
ps.setString(6, record.getNotes());
ps.setBigDecimal(7, record.getExpectedNextRepositioning());
ps.setString(8, record.getTimeUnitLkey());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setString(15, record.getStatusLkey());
ps.setString(16, record.getCancellationReason());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApRepositioningEntity entity, String lang) {
        Class<?> myClass = ApRepositioningEntity.class;
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
public void translateObject(ApRepositioningEntity entity, String lang) {
        ApRepositioningEntity translated = (ApRepositioningEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}