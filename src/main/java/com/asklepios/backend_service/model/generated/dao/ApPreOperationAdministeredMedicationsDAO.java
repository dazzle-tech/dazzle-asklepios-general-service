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
import com.asklepios.backend_service.model.generated.pojo.ApPreOperationAdministeredMedications;
import com.asklepios.backend_service.model.generated.entity.ApPreOperationAdministeredMedicationsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPreOperationAdministeredMedicationsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPreOperationAdministeredMedications getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operation_administered_medications where key = '"+key+"'");) {
ApPreOperationAdministeredMedications record = new ApPreOperationAdministeredMedications();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPreOperationKey(rs.getString("pre_operation_key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setDose(rs.getBigDecimal("dose"));
record.setUnitLkey(rs.getString("unit_lkey"));
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
public void updateRecord(ApPreOperationAdministeredMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operation_administered_medications set key = ?, pre_operation_key = ?, active_ingredient_key = ?, dose = ?, unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPreOperationKey());
ps.setString(3, record.getActiveIngredientKey());
ps.setBigDecimal(4, record.getDose());
ps.setString(5, record.getUnitLkey());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setBoolean(12, record.getIsvalid());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPreOperationAdministeredMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operation_administered_medications set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPreOperationAdministeredMedications> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operation_administered_medications where "+ where);) {
List<ApPreOperationAdministeredMedications> list = new ArrayList<ApPreOperationAdministeredMedications>();
while(rs.next()){
ApPreOperationAdministeredMedications record = new ApPreOperationAdministeredMedications();
record.setKey(rs.getString("key"));
record.setPreOperationKey(rs.getString("pre_operation_key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setDose(rs.getBigDecimal("dose"));
record.setUnitLkey(rs.getString("unit_lkey"));
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
public String saveRecord(ApPreOperationAdministeredMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_pre_operation_administered_medications values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPreOperationKey());
ps.setString(3, record.getActiveIngredientKey());
ps.setBigDecimal(4, record.getDose());
ps.setString(5, record.getUnitLkey());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setBoolean(12, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPreOperationAdministeredMedicationsEntity entity, String lang) {
        Class<?> myClass = ApPreOperationAdministeredMedicationsEntity.class;
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
public void translateObject(ApPreOperationAdministeredMedicationsEntity entity, String lang) {
        ApPreOperationAdministeredMedicationsEntity translated = (ApPreOperationAdministeredMedicationsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}