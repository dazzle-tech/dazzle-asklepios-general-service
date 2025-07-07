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
import com.asklepios.backend_service.model.generated.pojo.ApOperationPatientArrival;
import com.asklepios.backend_service.model.generated.entity.ApOperationPatientArrivalEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationPatientArrivalDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationPatientArrival getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_patient_arrival where key = '"+key+"'");) {
ApOperationPatientArrival record = new ApOperationPatientArrival();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setConsentLkey(rs.getString("consent_lkey"));
record.setRoomKey(rs.getString("room_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setSurgeonKey(rs.getString("surgeon_key"));
record.setDateTime(rs.getBigDecimal("date_time"));
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
public void updateRecord(ApOperationPatientArrival record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_patient_arrival set key = ?, operation_request_key = ?, consent_lkey = ?, room_key = ?, department_key = ?, surgeon_key = ?, date_time = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getConsentLkey());
ps.setString(4, record.getRoomKey());
ps.setString(5, record.getDepartmentKey());
ps.setString(6, record.getSurgeonKey());
ps.setBigDecimal(7, record.getDateTime());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationPatientArrival record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_patient_arrival set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationPatientArrival> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_patient_arrival where "+ where);) {
List<ApOperationPatientArrival> list = new ArrayList<ApOperationPatientArrival>();
while(rs.next()){
ApOperationPatientArrival record = new ApOperationPatientArrival();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setConsentLkey(rs.getString("consent_lkey"));
record.setRoomKey(rs.getString("room_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setSurgeonKey(rs.getString("surgeon_key"));
record.setDateTime(rs.getBigDecimal("date_time"));
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
public String saveRecord(ApOperationPatientArrival record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_patient_arrival values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getConsentLkey());
ps.setString(4, record.getRoomKey());
ps.setString(5, record.getDepartmentKey());
ps.setString(6, record.getSurgeonKey());
ps.setBigDecimal(7, record.getDateTime());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationPatientArrivalEntity entity, String lang) {
        Class<?> myClass = ApOperationPatientArrivalEntity.class;
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
public void translateObject(ApOperationPatientArrivalEntity entity, String lang) {
        ApOperationPatientArrivalEntity translated = (ApOperationPatientArrivalEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}