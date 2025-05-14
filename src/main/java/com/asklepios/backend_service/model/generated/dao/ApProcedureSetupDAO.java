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
import com.asklepios.backend_service.model.generated.pojo.ApProcedureSetup;
import com.asklepios.backend_service.model.generated.entity.ApProcedureSetupEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApProcedureSetupDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApProcedureSetup getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_setup where key = '"+key+"'");) {
ApProcedureSetup record = new ApProcedureSetup();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setName(rs.getString("name"));
record.setCode(rs.getString("code"));
record.setCategoryLkey(rs.getString("category_lkey"));
record.setIndications(rs.getString("indications"));
record.setContraindications(rs.getString("contraindications"));
record.setPreparationInstructions(rs.getString("preparation_instructions"));
record.setRecoveryNotes(rs.getString("recovery_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsAppointable(rs.getBoolean("is_appointable"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApProcedureSetup record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_setup set key = ?, name = ?, code = ?, category_lkey = ?, indications = ?, contraindications = ?, preparation_instructions = ?, recovery_notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_appointable = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getName());
ps.setString(3, record.getCode());
ps.setString(4, record.getCategoryLkey());
ps.setString(5, record.getIndications());
ps.setString(6, record.getContraindications());
ps.setString(7, record.getPreparationInstructions());
ps.setString(8, record.getRecoveryNotes());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsAppointable());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApProcedureSetup record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_setup set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApProcedureSetup> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_setup where "+ where);) {
List<ApProcedureSetup> list = new ArrayList<ApProcedureSetup>();
while(rs.next()){
ApProcedureSetup record = new ApProcedureSetup();
record.setKey(rs.getString("key"));
record.setName(rs.getString("name"));
record.setCode(rs.getString("code"));
record.setCategoryLkey(rs.getString("category_lkey"));
record.setIndications(rs.getString("indications"));
record.setContraindications(rs.getString("contraindications"));
record.setPreparationInstructions(rs.getString("preparation_instructions"));
record.setRecoveryNotes(rs.getString("recovery_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsAppointable(rs.getBoolean("is_appointable"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApProcedureSetup record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_procedure_setup values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getName());
ps.setString(3, record.getCode());
ps.setString(4, record.getCategoryLkey());
ps.setString(5, record.getIndications());
ps.setString(6, record.getContraindications());
ps.setString(7, record.getPreparationInstructions());
ps.setString(8, record.getRecoveryNotes());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsAppointable());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApProcedureSetupEntity entity, String lang) {
        Class<?> myClass = ApProcedureSetupEntity.class;
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
public void translateObject(ApProcedureSetupEntity entity, String lang) {
        ApProcedureSetupEntity translated = (ApProcedureSetupEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}