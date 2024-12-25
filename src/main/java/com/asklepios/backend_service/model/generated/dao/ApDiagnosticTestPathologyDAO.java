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
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestPathology;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticTestPathologyEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDiagnosticTestPathologyDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDiagnosticTestPathology getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_pathology where key = '"+key+"'");) {
ApDiagnosticTestPathology record = new ApDiagnosticTestPathology();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setInternationalCodingTypeLkey(rs.getString("international_coding_type_lkey"));
record.setChildCodeLkey(rs.getString("child_code_lkey"));
record.setPathologyCategoryLkey(rs.getString("pathology_category_lkey"));
record.setSpecimenTypeLkey(rs.getString("specimen_type_lkey"));
record.setAnalysisProcedureLkey(rs.getString("analysis_procedure_lkey"));
record.setTurnaroundTime(rs.getString("turnaround_time"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTestDescription(rs.getString("test_description"));
record.setSampleHandling(rs.getString("sample_handling"));
record.setMedicalLndications(rs.getString("medical_lndications"));
record.setCriticalValues(rs.getString("critical_values"));
record.setPreparationRequirements(rs.getString("preparation_requirements"));
record.setAssociatedRisks(rs.getString("associated_risks"));
record.setPathCatalogKey(rs.getString("path_catalog_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDiagnosticTestPathology record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_pathology set key = ?, test_key = ?, international_coding_type_lkey = ?, child_code_lkey = ?, pathology_category_lkey = ?, specimen_type_lkey = ?, analysis_procedure_lkey = ?, turnaround_time = ?, time_unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, test_description = ?, sample_handling = ?, medical_lndications = ?, critical_values = ?, preparation_requirements = ?, associated_risks = ?, path_catalog_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTestKey());
ps.setString(3, record.getInternationalCodingTypeLkey());
ps.setString(4, record.getChildCodeLkey());
ps.setString(5, record.getPathologyCategoryLkey());
ps.setString(6, record.getSpecimenTypeLkey());
ps.setString(7, record.getAnalysisProcedureLkey());
ps.setString(8, record.getTurnaroundTime());
ps.setString(9, record.getTimeUnitLkey());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.setString(17, record.getTestDescription());
ps.setString(18, record.getSampleHandling());
ps.setString(19, record.getMedicalLndications());
ps.setString(20, record.getCriticalValues());
ps.setString(21, record.getPreparationRequirements());
ps.setString(22, record.getAssociatedRisks());
ps.setString(23, record.getPathCatalogKey());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDiagnosticTestPathology record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_diagnostic_test_pathology set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDiagnosticTestPathology> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_diagnostic_test_pathology where "+ where);) {
List<ApDiagnosticTestPathology> list = new ArrayList<ApDiagnosticTestPathology>();
while(rs.next()){
ApDiagnosticTestPathology record = new ApDiagnosticTestPathology();
record.setKey(rs.getString("key"));
record.setTestKey(rs.getString("test_key"));
record.setInternationalCodingTypeLkey(rs.getString("international_coding_type_lkey"));
record.setChildCodeLkey(rs.getString("child_code_lkey"));
record.setPathologyCategoryLkey(rs.getString("pathology_category_lkey"));
record.setSpecimenTypeLkey(rs.getString("specimen_type_lkey"));
record.setAnalysisProcedureLkey(rs.getString("analysis_procedure_lkey"));
record.setTurnaroundTime(rs.getString("turnaround_time"));
record.setTimeUnitLkey(rs.getString("time_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTestDescription(rs.getString("test_description"));
record.setSampleHandling(rs.getString("sample_handling"));
record.setMedicalLndications(rs.getString("medical_lndications"));
record.setCriticalValues(rs.getString("critical_values"));
record.setPreparationRequirements(rs.getString("preparation_requirements"));
record.setAssociatedRisks(rs.getString("associated_risks"));
record.setPathCatalogKey(rs.getString("path_catalog_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDiagnosticTestPathology record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_diagnostic_test_pathology values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTestKey());
ps.setString(3, record.getInternationalCodingTypeLkey());
ps.setString(4, record.getChildCodeLkey());
ps.setString(5, record.getPathologyCategoryLkey());
ps.setString(6, record.getSpecimenTypeLkey());
ps.setString(7, record.getAnalysisProcedureLkey());
ps.setString(8, record.getTurnaroundTime());
ps.setString(9, record.getTimeUnitLkey());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.setString(17, record.getTestDescription());
ps.setString(18, record.getSampleHandling());
ps.setString(19, record.getMedicalLndications());
ps.setString(20, record.getCriticalValues());
ps.setString(21, record.getPreparationRequirements());
ps.setString(22, record.getAssociatedRisks());
ps.setString(23, record.getPathCatalogKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDiagnosticTestPathologyEntity entity, String lang) {
        Class<?> myClass = ApDiagnosticTestPathologyEntity.class;
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
public void translateObject(ApDiagnosticTestPathologyEntity entity, String lang) {
        ApDiagnosticTestPathologyEntity translated = (ApDiagnosticTestPathologyEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}