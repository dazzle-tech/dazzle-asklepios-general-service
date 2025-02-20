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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestNormalRange;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestNormalRangeEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestNormalRangeDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTestNormalRange getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_normal_range where key = '"+key+"'");) {
ApDiagnosticTestNormalRange record = new ApDiagnosticTestNormalRange();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setAgeFrom(rs.getBigDecimal("age_from"));
record.setAgeFromUnitLkey(rs.getString("age_from_unit_lkey"));
record.setAgeTo(rs.getBigDecimal("age_to"));
record.setAgeToUnitLkey(rs.getString("age_to_unit_lkey"));
record.setConditionLkey(rs.getString("condition_lkey"));
record.setResultTypeLkey(rs.getString("result_type_lkey"));
record.setResultText(rs.getString("result_text"));
record.setResultLovKey(rs.getString("result_lov_key"));
record.setNormalRangeTypeLkey(rs.getString("normal_range_type_lkey"));
record.setRangeFrom(rs.getBigDecimal("range_from"));
record.setRangeTo(rs.getBigDecimal("range_to"));
record.setCriticalValue(rs.getBoolean("critical_value"));
record.setCriticalValueLessThan(rs.getBigDecimal("critical_value_less_than"));
record.setCriticalValueMoreThan(rs.getBigDecimal("critical_value_more_than"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setProfileTestKey(rs.getString("profile_test_key"));
record.setIsProfile(rs.getBoolean("is_profile"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticTestNormalRange record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_normal_range set key = ?, test_key = ?, gender_lkey = ?, age_from = ?, age_from_unit_lkey = ?, age_to = ?, age_to_unit_lkey = ?, condition_lkey = ?, result_type_lkey = ?, result_text = ?, result_lov_key = ?, normal_range_type_lkey = ?, range_from = ?, range_to = ?, critical_value = ?, critical_value_less_than = ?, critical_value_more_than = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, profile_test_key = ?, is_profile = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTestKey());
ps.setString(3, record.getGenderLkey());
ps.setBigDecimal(4, record.getAgeFrom());
ps.setString(5, record.getAgeFromUnitLkey());
ps.setBigDecimal(6, record.getAgeTo());
ps.setString(7, record.getAgeToUnitLkey());
ps.setString(8, record.getConditionLkey());
ps.setString(9, record.getResultTypeLkey());
ps.setString(10, record.getResultText());
ps.setString(11, record.getResultLovKey());
ps.setString(12, record.getNormalRangeTypeLkey());
ps.setBigDecimal(13, record.getRangeFrom());
ps.setBigDecimal(14, record.getRangeTo());
ps.setBoolean(15, record.getCriticalValue());
ps.setBigDecimal(16, record.getCriticalValueLessThan());
ps.setBigDecimal(17, record.getCriticalValueMoreThan());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.setString(25, record.getProfileTestKey());
ps.setBoolean(26, record.getIsProfile());
ps.setString(27, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTestNormalRange record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_normal_range set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTestNormalRange> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_normal_range where "+ where);) {
List<ApDiagnosticTestNormalRange> list = new ArrayList<ApDiagnosticTestNormalRange>();
while(rs.next()){
ApDiagnosticTestNormalRange record = new ApDiagnosticTestNormalRange();
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setAgeFrom(rs.getBigDecimal("age_from"));
record.setAgeFromUnitLkey(rs.getString("age_from_unit_lkey"));
record.setAgeTo(rs.getBigDecimal("age_to"));
record.setAgeToUnitLkey(rs.getString("age_to_unit_lkey"));
record.setConditionLkey(rs.getString("condition_lkey"));
record.setResultTypeLkey(rs.getString("result_type_lkey"));
record.setResultText(rs.getString("result_text"));
record.setResultLovKey(rs.getString("result_lov_key"));
record.setNormalRangeTypeLkey(rs.getString("normal_range_type_lkey"));
record.setRangeFrom(rs.getBigDecimal("range_from"));
record.setRangeTo(rs.getBigDecimal("range_to"));
record.setCriticalValue(rs.getBoolean("critical_value"));
record.setCriticalValueLessThan(rs.getBigDecimal("critical_value_less_than"));
record.setCriticalValueMoreThan(rs.getBigDecimal("critical_value_more_than"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setProfileTestKey(rs.getString("profile_test_key"));
record.setIsProfile(rs.getBoolean("is_profile"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticTestNormalRange record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test_normal_range values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTestKey());
ps.setString(3, record.getGenderLkey());
ps.setBigDecimal(4, record.getAgeFrom());
ps.setString(5, record.getAgeFromUnitLkey());
ps.setBigDecimal(6, record.getAgeTo());
ps.setString(7, record.getAgeToUnitLkey());
ps.setString(8, record.getConditionLkey());
ps.setString(9, record.getResultTypeLkey());
ps.setString(10, record.getResultText());
ps.setString(11, record.getResultLovKey());
ps.setString(12, record.getNormalRangeTypeLkey());
ps.setBigDecimal(13, record.getRangeFrom());
ps.setBigDecimal(14, record.getRangeTo());
ps.setBoolean(15, record.getCriticalValue());
ps.setBigDecimal(16, record.getCriticalValueLessThan());
ps.setBigDecimal(17, record.getCriticalValueMoreThan());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.setString(25, record.getProfileTestKey());
ps.setBoolean(26, record.getIsProfile());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestNormalRangeEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestNormalRangeEntity.class;
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
public void translateObject(ApDiagnosticTestNormalRangeEntity entity, String lang) {
        ApDiagnosticTestNormalRangeEntity translated = (ApDiagnosticTestNormalRangeEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}