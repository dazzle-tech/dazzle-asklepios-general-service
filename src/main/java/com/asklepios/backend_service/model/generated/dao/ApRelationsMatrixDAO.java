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
import com.asklepios.backend_service.model.generated.pojo.ApRelationsMatrix;
import com.asklepios.backend_service.model.generated.entity.ApRelationsMatrixEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApRelationsMatrixDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApRelationsMatrix getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_relations_matrix where key = '"+key+"'");) {
ApRelationsMatrix record = new ApRelationsMatrix();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFirstPatientGender(rs.getString("first_patient_gender"));
record.setSecondPatientGender(rs.getString("second_patient_gender"));
record.setFirstRelationCode(rs.getString("first_relation_code"));
record.setSecondRelationCode(rs.getString("second_relation_code"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApRelationsMatrix record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_relations_matrix set key = ?, first_patient_gender = ?, second_patient_gender = ?, first_relation_code = ?, second_relation_code = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getFirstPatientGender());
ps.setString(3, record.getSecondPatientGender());
ps.setString(4, record.getFirstRelationCode());
ps.setString(5, record.getSecondRelationCode());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setString(12, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApRelationsMatrix record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_relations_matrix set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApRelationsMatrix> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_relations_matrix where "+ where);) {
List<ApRelationsMatrix> list = new ArrayList<ApRelationsMatrix>();
while(rs.next()){
ApRelationsMatrix record = new ApRelationsMatrix();
record.setKey(rs.getString("key"));
record.setFirstPatientGender(rs.getString("first_patient_gender"));
record.setSecondPatientGender(rs.getString("second_patient_gender"));
record.setFirstRelationCode(rs.getString("first_relation_code"));
record.setSecondRelationCode(rs.getString("second_relation_code"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApRelationsMatrix record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_relations_matrix values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getFirstPatientGender());
ps.setString(3, record.getSecondPatientGender());
ps.setString(4, record.getFirstRelationCode());
ps.setString(5, record.getSecondRelationCode());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApRelationsMatrixEntity entity, String lang) {
        Class<?> myClass = ApRelationsMatrixEntity.class;
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
public void translateObject(ApRelationsMatrixEntity entity, String lang) {
        ApRelationsMatrixEntity translated = (ApRelationsMatrixEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}