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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestRadiology;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestRadiologyEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestRadiologyDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTestRadiology getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_radiology where key = '"+key+"'");) {
ApDiagnosticTestRadiology record = new ApDiagnosticTestRadiology();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setInternationalCodingTypeLkey(rs.getString("international_coding_type_lkey"));
record.setChildCodeLkey(rs.getString("child_code_lkey"));
record.setRadCategoryLkey(rs.getString("rad_category_lkey"));
record.setImageDuration(rs.getString("image_duration"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTestDescription(rs.getString("test_description"));
record.setMedicalIndications(rs.getString("medical_indications"));
record.setTurnaroundTimeUnitLkey(rs.getString("turnaround_time_unit_lkey"));
record.setTurnaroundTime(rs.getBigDecimal("turnaround_time"));
record.setAssociatedRisks(rs.getString("associated_risks"));
record.setLabCatalogLkey(rs.getString("lab_catalog_lkey"));
record.setRadCatalogKey(rs.getString("rad_catalog_key"));
record.setPropertyLkey(rs.getString("property_lkey"));
record.setSystemLkey(rs.getString("system_lkey"));
record.setScaleLkey(rs.getString("scale_lkey"));
record.setReagentsLkey(rs.getString("reagents_lkey"));
record.setMethodLkey(rs.getString("method_lkey"));
record.setTimingLkey(rs.getString("timing_lkey"));
record.setResultType(rs.getString("result_type"));
record.setResultUnitLkey(rs.getString("result_unit_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticTestRadiology record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_radiology set key = ?, test_key = ?, international_coding_type_lkey = ?, child_code_lkey = ?, rad_category_lkey = ?, image_duration = ?, time_unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, test_description = ?, medical_indications = ?, turnaround_time_unit_lkey = ?, turnaround_time = ?, associated_risks = ?, lab_catalog_lkey = ?, property_lkey = ?, system_lkey = ?, scale_lkey = ?, reagents_lkey = ?, method_lkey = ?, timing_lkey = ?, result_type = ?, result_unit_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTestKey());
ps.setString(3, record.getInternationalCodingTypeLkey());
ps.setString(4, record.getChildCodeLkey());
ps.setString(5, record.getRadCategoryLkey());
ps.setString(6, record.getImageDuration());
ps.setString(7, record.getTimeUnitLkey());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getTestDescription());
ps.setString(16, record.getMedicalIndications());
ps.setString(17, record.getTurnaroundTimeUnitLkey());
ps.setBigDecimal(18, record.getTurnaroundTime());
ps.setString(19, record.getAssociatedRisks());
ps.setString(20, record.getLabCatalogLkey());
ps.setString(20, record.getRadCatalogKey());
ps.setString(21, record.getPropertyLkey());
ps.setString(22, record.getSystemLkey());
ps.setString(23, record.getScaleLkey());
ps.setString(24, record.getReagentsLkey());
ps.setString(25, record.getMethodLkey());
ps.setString(26, record.getTimingLkey());
ps.setString(27, record.getResultType());
ps.setString(28, record.getResultUnitLkey());
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTestRadiology record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_radiology set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTestRadiology> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_radiology where "+ where);) {
List<ApDiagnosticTestRadiology> list = new ArrayList<ApDiagnosticTestRadiology>();
while(rs.next()){
ApDiagnosticTestRadiology record = new ApDiagnosticTestRadiology();
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setInternationalCodingTypeLkey(rs.getString("international_coding_type_lkey"));
record.setChildCodeLkey(rs.getString("child_code_lkey"));
record.setRadCategoryLkey(rs.getString("rad_category_lkey"));
record.setImageDuration(rs.getString("image_duration"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTestDescription(rs.getString("test_description"));
record.setMedicalIndications(rs.getString("medical_indications"));
record.setTurnaroundTimeUnitLkey(rs.getString("turnaround_time_unit_lkey"));
record.setTurnaroundTime(rs.getBigDecimal("turnaround_time"));
record.setAssociatedRisks(rs.getString("associated_risks"));
record.setLabCatalogLkey(rs.getString("lab_catalog_lkey"));
record.setRadCatalogKey(rs.getString("rad_catalog_key"));
record.setPropertyLkey(rs.getString("property_lkey"));
record.setSystemLkey(rs.getString("system_lkey"));
record.setScaleLkey(rs.getString("scale_lkey"));
record.setReagentsLkey(rs.getString("reagents_lkey"));
record.setMethodLkey(rs.getString("method_lkey"));
record.setTimingLkey(rs.getString("timing_lkey"));
record.setResultType(rs.getString("result_type"));
record.setResultUnitLkey(rs.getString("result_unit_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticTestRadiology record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test_radiology values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTestKey());
ps.setString(3, record.getInternationalCodingTypeLkey());
ps.setString(4, record.getChildCodeLkey());
ps.setString(5, record.getRadCategoryLkey());
ps.setString(6, record.getImageDuration());
ps.setString(7, record.getTimeUnitLkey());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getTestDescription());
ps.setString(16, record.getMedicalIndications());
ps.setString(17, record.getTurnaroundTimeUnitLkey());
ps.setBigDecimal(18, record.getTurnaroundTime());
ps.setString(19, record.getAssociatedRisks());
ps.setString(20, record.getLabCatalogLkey());
ps.setString(20, record.getRadCatalogKey());
ps.setString(21, record.getPropertyLkey());
ps.setString(22, record.getSystemLkey());
ps.setString(23, record.getScaleLkey());
ps.setString(24, record.getReagentsLkey());
ps.setString(25, record.getMethodLkey());
ps.setString(26, record.getTimingLkey());
ps.setString(27, record.getResultType());
ps.setString(28, record.getResultUnitLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestRadiologyEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestRadiologyEntity.class;
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
public void translateObject(ApDiagnosticTestRadiologyEntity entity, String lang) {
        ApDiagnosticTestRadiologyEntity translated = (ApDiagnosticTestRadiologyEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}