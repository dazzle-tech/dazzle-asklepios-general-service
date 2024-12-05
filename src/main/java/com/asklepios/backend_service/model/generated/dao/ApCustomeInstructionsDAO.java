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
import com.asklepios.backend_service.model.generated.pojo.ApCustomeInstructions;
import com.asklepios.backend_service.model.generated.entity.ApCustomeInstructionsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApCustomeInstructionsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApCustomeInstructions getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_custome_instructions where key = '"+key+"'");) {
ApCustomeInstructions record = new ApCustomeInstructions();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPrescriptionMedicationsKey(rs.getString("prescription_medications_key"));
record.setDose(rs.getBigDecimal("dose"));
record.setFrequencyLkey(rs.getString("frequency_lkey"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setRoaLkey(rs.getString("roa_lkey"));
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
public void updateRecord(ApCustomeInstructions record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_custome_instructions set key = ?, prescription_medications_key = ?, dose = ?, frequency_lkey = ?, unit_lkey = ?, roa_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPrescriptionMedicationsKey());
ps.setBigDecimal(3, record.getDose());
ps.setString(4, record.getFrequencyLkey());
ps.setString(5, record.getUnitLkey());
ps.setString(6, record.getRoaLkey());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.setString(14, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApCustomeInstructions record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_custome_instructions set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApCustomeInstructions> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_custome_instructions where "+ where);) {
List<ApCustomeInstructions> list = new ArrayList<ApCustomeInstructions>();
while(rs.next()){
ApCustomeInstructions record = new ApCustomeInstructions();
record.setKey(rs.getString("key"));
record.setPrescriptionMedicationsKey(rs.getString("prescription_medications_key"));
record.setDose(rs.getBigDecimal("dose"));
record.setFrequencyLkey(rs.getString("frequency_lkey"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setRoaLkey(rs.getString("roa_lkey"));
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
public String saveRecord(ApCustomeInstructions record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_custome_instructions values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPrescriptionMedicationsKey());
ps.setBigDecimal(3, record.getDose());
ps.setString(4, record.getFrequencyLkey());
ps.setString(5, record.getUnitLkey());
ps.setString(6, record.getRoaLkey());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApCustomeInstructionsEntity entity, String lang) {
        Class<?> myClass = ApCustomeInstructionsEntity.class;
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
public void translateObject(ApCustomeInstructionsEntity entity, String lang) {
        ApCustomeInstructionsEntity translated = (ApCustomeInstructionsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}