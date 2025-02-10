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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestLaboratory;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestLaboratoryEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestLaboratoryDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTestLaboratory getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_laboratory where key = '"+key+"'");) {
ApDiagnosticTestLaboratory record = new ApDiagnosticTestLaboratory();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setInternationalCodingTypeLkey(rs.getString("international_coding_type_lkey"));
record.setChildCodeLkey(rs.getString("child_code_lkey"));
record.setLabCatalogKey(rs.getString("lab_catalog_key"));
record.setPropertyLkey(rs.getString("property_lkey"));
record.setSystemLkey(rs.getString("system_lkey"));
record.setScaleLkey(rs.getString("scale_lkey"));
record.setReagentsLkey(rs.getString("reagents_lkey"));
record.setMethodLkey(rs.getString("method_lkey"));
record.setTestDurationTime(rs.getBigDecimal("test_duration_time"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setResultType(rs.getString("result_type"));
record.setResultUnitLkey(rs.getString("result_unit_lkey"));
record.setIsProfile(rs.getBoolean("is_profile"));
record.setSampleContainerLkey(rs.getString("sample_container_lkey"));
record.setSampleVolume(rs.getBigDecimal("sample_volume"));
record.setSampleVolumeUnitLkey(rs.getString("sample_volume_unit_lkey"));
record.setTubeColorLkey(rs.getString("tube_color_lkey"));
record.setTestDescription(rs.getString("test_description"));
record.setSampleHandling(rs.getString("sample_handling"));
record.setTurnaroundTime(rs.getBigDecimal("turnaround_time"));
record.setTurnaroundTimeUnitLkey(rs.getString("turnaround_time_unit_lkey"));
record.setPreparationRequirements(rs.getString("preparation_requirements"));
record.setMedicalIndications(rs.getString("medical_indications"));
record.setAssociatedRisks(rs.getString("associated_risks"));
record.setTestInstructions(rs.getString("test_instructions"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCategoryLkey(rs.getString("category_lkey"));
record.setTubeTypeLkey(rs.getString("tube_type_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticTestLaboratory record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_laboratory set key = ?, test_key = ?, international_coding_type_lkey = ?, child_code_lkey = ?, lab_catalog_key = ?, property_lkey = ?, system_lkey = ?, scale_lkey = ?, reagents_lkey = ?, method_lkey = ?, test_duration_time = ?, time_unit_lkey = ?, result_type = ?, result_unit_lkey = ?, is_profile = ?, sample_container_lkey = ?, sample_volume = ?, sample_volume_unit_lkey = ?, tube_color_lkey = ?, test_description = ?, sample_handling = ?, turnaround_time = ?, turnaround_time_unit_lkey = ?, preparation_requirements = ?, medical_indications = ?, associated_risks = ?, test_instructions = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, category_lkey = ?, tube_type_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTestKey());
ps.setString(3, record.getInternationalCodingTypeLkey());
ps.setString(4, record.getChildCodeLkey());
ps.setString(5, record.getLabCatalogKey());
ps.setString(6, record.getPropertyLkey());
ps.setString(7, record.getSystemLkey());
ps.setString(8, record.getScaleLkey());
ps.setString(9, record.getReagentsLkey());
ps.setString(10, record.getMethodLkey());
ps.setBigDecimal(11, record.getTestDurationTime());
ps.setString(12, record.getTimeUnitLkey());
ps.setString(13, record.getResultType());
ps.setString(14, record.getResultUnitLkey());
ps.setBoolean(15, record.getIsProfile());
ps.setString(16, record.getSampleContainerLkey());
ps.setBigDecimal(17, record.getSampleVolume());
ps.setString(18, record.getSampleVolumeUnitLkey());
ps.setString(19, record.getTubeColorLkey());
ps.setString(20, record.getTestDescription());
ps.setString(21, record.getSampleHandling());
ps.setBigDecimal(22, record.getTurnaroundTime());
ps.setString(23, record.getTurnaroundTimeUnitLkey());
ps.setString(24, record.getPreparationRequirements());
ps.setString(25, record.getMedicalIndications());
ps.setString(26, record.getAssociatedRisks());
ps.setString(27, record.getTestInstructions());
ps.setString(28, record.getCreatedBy());
ps.setString(29, record.getUpdatedBy());
ps.setString(30, record.getDeletedBy());
ps.setBigDecimal(31, record.getCreatedAt());
ps.setBigDecimal(32, record.getUpdatedAt());
ps.setBigDecimal(33, record.getDeletedAt());
ps.setBoolean(34, record.getIsValid());
ps.setString(35, record.getCategoryLkey());
ps.setString(36, record.getTubeTypeLkey());
ps.setString(37, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTestLaboratory record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_laboratory set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTestLaboratory> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_laboratory where "+ where);) {
List<ApDiagnosticTestLaboratory> list = new ArrayList<ApDiagnosticTestLaboratory>();
while(rs.next()){
ApDiagnosticTestLaboratory record = new ApDiagnosticTestLaboratory();
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setInternationalCodingTypeLkey(rs.getString("international_coding_type_lkey"));
record.setChildCodeLkey(rs.getString("child_code_lkey"));
record.setLabCatalogKey(rs.getString("lab_catalog_key"));
record.setPropertyLkey(rs.getString("property_lkey"));
record.setSystemLkey(rs.getString("system_lkey"));
record.setScaleLkey(rs.getString("scale_lkey"));
record.setReagentsLkey(rs.getString("reagents_lkey"));
record.setMethodLkey(rs.getString("method_lkey"));
record.setTestDurationTime(rs.getBigDecimal("test_duration_time"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setResultType(rs.getString("result_type"));
record.setResultUnitLkey(rs.getString("result_unit_lkey"));
record.setIsProfile(rs.getBoolean("is_profile"));
record.setSampleContainerLkey(rs.getString("sample_container_lkey"));
record.setSampleVolume(rs.getBigDecimal("sample_volume"));
record.setSampleVolumeUnitLkey(rs.getString("sample_volume_unit_lkey"));
record.setTubeColorLkey(rs.getString("tube_color_lkey"));
record.setTestDescription(rs.getString("test_description"));
record.setSampleHandling(rs.getString("sample_handling"));
record.setTurnaroundTime(rs.getBigDecimal("turnaround_time"));
record.setTurnaroundTimeUnitLkey(rs.getString("turnaround_time_unit_lkey"));
record.setPreparationRequirements(rs.getString("preparation_requirements"));
record.setMedicalIndications(rs.getString("medical_indications"));
record.setAssociatedRisks(rs.getString("associated_risks"));
record.setTestInstructions(rs.getString("test_instructions"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCategoryLkey(rs.getString("category_lkey"));
record.setTubeTypeLkey(rs.getString("tube_type_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticTestLaboratory record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test_laboratory values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTestKey());
ps.setString(3, record.getInternationalCodingTypeLkey());
ps.setString(4, record.getChildCodeLkey());
ps.setString(5, record.getLabCatalogKey());
ps.setString(6, record.getPropertyLkey());
ps.setString(7, record.getSystemLkey());
ps.setString(8, record.getScaleLkey());
ps.setString(9, record.getReagentsLkey());
ps.setString(10, record.getMethodLkey());
ps.setBigDecimal(11, record.getTestDurationTime());
ps.setString(12, record.getTimeUnitLkey());
ps.setString(13, record.getResultType());
ps.setString(14, record.getResultUnitLkey());
ps.setBoolean(15, record.getIsProfile());
ps.setString(16, record.getSampleContainerLkey());
ps.setBigDecimal(17, record.getSampleVolume());
ps.setString(18, record.getSampleVolumeUnitLkey());
ps.setString(19, record.getTubeColorLkey());
ps.setString(20, record.getTestDescription());
ps.setString(21, record.getSampleHandling());
ps.setBigDecimal(22, record.getTurnaroundTime());
ps.setString(23, record.getTurnaroundTimeUnitLkey());
ps.setString(24, record.getPreparationRequirements());
ps.setString(25, record.getMedicalIndications());
ps.setString(26, record.getAssociatedRisks());
ps.setString(27, record.getTestInstructions());
ps.setString(28, record.getCreatedBy());
ps.setString(29, record.getUpdatedBy());
ps.setString(30, record.getDeletedBy());
ps.setBigDecimal(31, record.getCreatedAt());
ps.setBigDecimal(32, record.getUpdatedAt());
ps.setBigDecimal(33, record.getDeletedAt());
ps.setBoolean(34, record.getIsValid());
ps.setString(35, record.getCategoryLkey());
ps.setString(36, record.getTubeTypeLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestLaboratoryEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestLaboratoryEntity.class;
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
public void translateObject(ApDiagnosticTestLaboratoryEntity entity, String lang) {
        ApDiagnosticTestLaboratoryEntity translated = (ApDiagnosticTestLaboratoryEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}