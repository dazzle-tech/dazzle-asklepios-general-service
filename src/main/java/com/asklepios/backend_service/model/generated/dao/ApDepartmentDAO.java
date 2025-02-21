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
import com.asklepios.backend_service.model.generated.pojo.ApDepartment;
import com.asklepios.backend_service.model.generated.entity.ApDepartmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDepartmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDepartment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_department where key = '"+key+"'");) {
ApDepartment record = new ApDepartment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setName(rs.getString("name"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentTypeLkey(rs.getString("department_type_lkey"));
record.setAppointable(rs.getBoolean("appointable"));
record.setHasTriage(rs.getBoolean("has_triage"));
record.setDepartmentCode(rs.getString("department_code"));
record.setPhoneNumber(rs.getString("phone_number"));
record.setEmail(rs.getString("email"));
record.setEncountertypelkey(rs.getString("encountertypelkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDepartment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_department set key = ?, facility_key = ?, name = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, department_type_lkey = ?, appointable = ?, has_triage = ?, department_code = ?, phone_number = ?, email = ?, encountertypelkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getName());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setBoolean(10, record.getIsValid());
ps.setString(11, record.getDepartmentTypeLkey());
ps.setBoolean(12, record.getAppointable());
ps.setBoolean(13, record.getHasTriage());
ps.setString(14, record.getDepartmentCode());
ps.setString(15, record.getPhoneNumber());
ps.setString(16, record.getEmail());
ps.setString(17, record.getEncountertypelkey());
ps.setString(18, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDepartment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_department set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDepartment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_department where "+ where);) {
List<ApDepartment> list = new ArrayList<ApDepartment>();
while(rs.next()){
ApDepartment record = new ApDepartment();
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setName(rs.getString("name"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentTypeLkey(rs.getString("department_type_lkey"));
record.setAppointable(rs.getBoolean("appointable"));
record.setHasTriage(rs.getBoolean("has_triage"));
record.setDepartmentCode(rs.getString("department_code"));
record.setPhoneNumber(rs.getString("phone_number"));
record.setEmail(rs.getString("email"));
record.setEncountertypelkey(rs.getString("encountertypelkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDepartment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_department values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getName());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setBoolean(10, record.getIsValid());
ps.setString(11, record.getDepartmentTypeLkey());
ps.setBoolean(12, record.getAppointable());
ps.setBoolean(13, record.getHasTriage());
ps.setString(14, record.getDepartmentCode());
ps.setString(15, record.getPhoneNumber());
ps.setString(16, record.getEmail());
ps.setString(17, record.getEncountertypelkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDepartmentEntity entity, String lang) {
        Class<?> myClass = ApDepartmentEntity.class;
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
public void translateObject(ApDepartmentEntity entity, String lang) {
        ApDepartmentEntity translated = (ApDepartmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}