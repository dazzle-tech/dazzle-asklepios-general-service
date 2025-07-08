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
import com.asklepios.backend_service.model.generated.pojo.ApOperationInduction;
import com.asklepios.backend_service.model.generated.entity.ApOperationInductionEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationInductionDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationInduction getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_induction where key = '"+key+"'");) {
ApOperationInduction record = new ApOperationInduction();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
record.setDose(rs.getBigDecimal("dose"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setActiveIngredientKey(rs.getBigDecimal("active_ingredient_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationInduction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_induction set key = ?, operation_request_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ?, dose = ?, unit_lkey = ?, active_ingredient_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getCreatedBy());
ps.setString(4, record.getUpdatedBy());
ps.setString(5, record.getDeletedBy());
ps.setBigDecimal(6, record.getCreatedAt());
ps.setBigDecimal(7, record.getUpdatedAt());
ps.setBigDecimal(8, record.getDeletedAt());
ps.setBoolean(9, record.getIsvalid());
ps.setBigDecimal(10, record.getDose());
ps.setString(11, record.getUnitLkey());
ps.setBigDecimal(12, record.getActiveIngredientKey());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationInduction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_induction set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationInduction> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_induction where "+ where);) {
List<ApOperationInduction> list = new ArrayList<ApOperationInduction>();
while(rs.next()){
ApOperationInduction record = new ApOperationInduction();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
record.setDose(rs.getBigDecimal("dose"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setActiveIngredientKey(rs.getBigDecimal("active_ingredient_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationInduction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_induction values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getCreatedBy());
ps.setString(4, record.getUpdatedBy());
ps.setString(5, record.getDeletedBy());
ps.setBigDecimal(6, record.getCreatedAt());
ps.setBigDecimal(7, record.getUpdatedAt());
ps.setBigDecimal(8, record.getDeletedAt());
ps.setBoolean(9, record.getIsvalid());
ps.setBigDecimal(10, record.getDose());
ps.setString(11, record.getUnitLkey());
ps.setBigDecimal(12, record.getActiveIngredientKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationInductionEntity entity, String lang) {
        Class<?> myClass = ApOperationInductionEntity.class;
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
public void translateObject(ApOperationInductionEntity entity, String lang) {
        ApOperationInductionEntity translated = (ApOperationInductionEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}