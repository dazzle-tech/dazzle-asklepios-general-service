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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTest getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test where key = '"+key+"'");) {
ApDiagnosticTest record = new ApDiagnosticTest();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTestTypeLkey(rs.getString("test_type_lkey"));
record.setTestName(rs.getString("test_name"));
record.setInternalCode(rs.getString("internal_code"));
record.setInternationalCodeOne(rs.getString("international_code_one"));
record.setInternationalCodeTwo(rs.getString("international_code_two"));
record.setInternationalCodeThree(rs.getString("international_code_three"));
record.setAgeSpecific(rs.getBoolean("age_specific"));
record.setGenderSpecific(rs.getBoolean("gender_specific"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setSpecialPopulation(rs.getBoolean("special_population"));
record.setPrice(rs.getBigDecimal("price"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
record.setSpecialNotes(rs.getString("special_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setAppointable(rs.getBoolean("appointable"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticTest record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test set key = ?, test_type_lkey = ?, test_name = ?, internal_code = ?, international_code_one = ?, international_code_two = ?, international_code_three = ?, age_specific = ?, gender_specific = ?, gender_lkey = ?, special_population = ?, price = ?, currency_lkey = ?, special_notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, appointable = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTestTypeLkey());
ps.setString(3, record.getTestName());
ps.setString(4, record.getInternalCode());
ps.setString(5, record.getInternationalCodeOne());
ps.setString(6, record.getInternationalCodeTwo());
ps.setString(7, record.getInternationalCodeThree());
ps.setBoolean(8, record.getAgeSpecific());
ps.setBoolean(9, record.getGenderSpecific());
ps.setString(10, record.getGenderLkey());
ps.setBoolean(11, record.getSpecialPopulation());
ps.setBigDecimal(12, record.getPrice());
ps.setString(13, record.getCurrencyLkey());
ps.setString(14, record.getSpecialNotes());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setBoolean(22, record.getAppointable());
ps.setString(23, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTest record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTest> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test where "+ where);) {
List<ApDiagnosticTest> list = new ArrayList<ApDiagnosticTest>();
while(rs.next()){
ApDiagnosticTest record = new ApDiagnosticTest();
record.setKey(rs.getString("key"));
record.setTestTypeLkey(rs.getString("test_type_lkey"));
record.setTestName(rs.getString("test_name"));
record.setInternalCode(rs.getString("internal_code"));
record.setInternationalCodeOne(rs.getString("international_code_one"));
record.setInternationalCodeTwo(rs.getString("international_code_two"));
record.setInternationalCodeThree(rs.getString("international_code_three"));
record.setAgeSpecific(rs.getBoolean("age_specific"));
record.setGenderSpecific(rs.getBoolean("gender_specific"));
record.setGenderLkey(rs.getString("gender_lkey"));
record.setSpecialPopulation(rs.getBoolean("special_population"));
record.setPrice(rs.getBigDecimal("price"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
record.setSpecialNotes(rs.getString("special_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setAppointable(rs.getBoolean("appointable"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticTest record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTestTypeLkey());
ps.setString(3, record.getTestName());
ps.setString(4, record.getInternalCode());
ps.setString(5, record.getInternationalCodeOne());
ps.setString(6, record.getInternationalCodeTwo());
ps.setString(7, record.getInternationalCodeThree());
ps.setBoolean(8, record.getAgeSpecific());
ps.setBoolean(9, record.getGenderSpecific());
ps.setString(10, record.getGenderLkey());
ps.setBoolean(11, record.getSpecialPopulation());
ps.setBigDecimal(12, record.getPrice());
ps.setString(13, record.getCurrencyLkey());
ps.setString(14, record.getSpecialNotes());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setBoolean(22, record.getAppointable());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestEntity.class;
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
public void translateObject(ApDiagnosticTestEntity entity, String lang) {
        ApDiagnosticTestEntity translated = (ApDiagnosticTestEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}