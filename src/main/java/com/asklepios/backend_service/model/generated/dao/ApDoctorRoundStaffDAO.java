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
import com.asklepios.backend_service.model.generated.pojo.ApDoctorRoundStaff;
import com.asklepios.backend_service.model.generated.entity.ApDoctorRoundStaffEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDoctorRoundStaffDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDoctorRoundStaff getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_doctor_round_staff where key = '"+key+"'");) {
ApDoctorRoundStaff record = new ApDoctorRoundStaff();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setDoctorRoundKey(rs.getString("doctor_round_key"));
record.setUserKey(rs.getString("user_key"));
record.setResponsibility(rs.getString("responsibility"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setIsPresent(rs.getBoolean("is_present"));
record.setPatientKey(rs.getString("patient_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDoctorRoundStaff record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_doctor_round_staff set key = ?, doctor_round_key = ?, user_key = ?, responsibility = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, encounter_key = ?, is_present = ?, patient_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getDoctorRoundKey());
ps.setString(3, record.getUserKey());
ps.setString(4, record.getResponsibility());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setString(11, record.getEncounterKey());
ps.setBoolean(12, record.getIsPresent());
ps.setString(13, record.getPatientKey());
ps.setString(14, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDoctorRoundStaff record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_doctor_round_staff set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDoctorRoundStaff> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_doctor_round_staff where "+ where);) {
List<ApDoctorRoundStaff> list = new ArrayList<ApDoctorRoundStaff>();
while(rs.next()){
ApDoctorRoundStaff record = new ApDoctorRoundStaff();
record.setKey(rs.getString("key"));
record.setDoctorRoundKey(rs.getString("doctor_round_key"));
record.setUserKey(rs.getString("user_key"));
record.setResponsibility(rs.getString("responsibility"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setIsPresent(rs.getBoolean("is_present"));
record.setPatientKey(rs.getString("patient_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDoctorRoundStaff record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_doctor_round_staff values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getDoctorRoundKey());
ps.setString(3, record.getUserKey());
ps.setString(4, record.getResponsibility());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setString(11, record.getEncounterKey());
ps.setBoolean(12, record.getIsPresent());
ps.setString(13, record.getPatientKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDoctorRoundStaffEntity entity, String lang) {
        Class<?> myClass = ApDoctorRoundStaffEntity.class;
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
public void translateObject(ApDoctorRoundStaffEntity entity, String lang) {
        ApDoctorRoundStaffEntity translated = (ApDoctorRoundStaffEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}