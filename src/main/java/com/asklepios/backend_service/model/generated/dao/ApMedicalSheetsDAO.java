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
import com.asklepios.backend_service.model.generated.pojo.ApMedicalSheets;
import com.asklepios.backend_service.model.generated.entity.ApMedicalSheetsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApMedicalSheetsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApMedicalSheets getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medical_sheets where key = '"+key+"'");) {
ApMedicalSheets record = new ApMedicalSheets();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setPatientDashboard(rs.getBoolean("patient_dashboard"));
record.setClinicalVisit(rs.getBoolean("clinical_visit"));
record.setDiagnosticsOrder(rs.getBoolean("diagnostics_order"));
record.setPrescription(rs.getBoolean("prescription"));
record.setDrugOrder(rs.getBoolean("drug_order"));
record.setConsultation(rs.getBoolean("consultation"));
record.setProcedures(rs.getBoolean("procedures"));
record.setPatientHistory(rs.getBoolean("patient_history"));
record.setAllergies(rs.getBoolean("allergies"));
record.setMedicalWarnings(rs.getBoolean("medical_warnings"));
record.setMedicationsRecord(rs.getBoolean("medications_record"));
record.setPsychologicalExam(rs.getBoolean("psychological_exam"));
record.setAudiometryPuretone(rs.getBoolean("audiometry_puretone"));
record.setOptometricExam(rs.getBoolean("optometric_exam"));
record.setVaccineReccord(rs.getBoolean("vaccine_reccord"));
record.setDiagnosticsResult(rs.getBoolean("diagnostics_result"));
record.setDentalCare(rs.getBoolean("dental_care"));
record.setCardiology(rs.getBoolean("cardiology"));
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
public void updateRecord(ApMedicalSheets record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medical_sheets set key = ?, department_key = ?, facility_key = ?, patient_dashboard = ?, clinical_visit = ?, diagnostics_order = ?, prescription = ?, drug_order = ?, consultation = ?, procedures = ?, patient_history = ?, allergies = ?, medical_warnings = ?, medications_record = ?, psychological_exam = ?, audiometry_puretone = ?, optometric_exam = ?, vaccine_reccord = ?, diagnostics_result = ?, dental_care = ?, cardiology = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getDepartmentKey());
ps.setString(3, record.getFacilityKey());
ps.setBoolean(4, record.getPatientDashboard());
ps.setBoolean(5, record.getClinicalVisit());
ps.setBoolean(6, record.getDiagnosticsOrder());
ps.setBoolean(7, record.getPrescription());
ps.setBoolean(8, record.getDrugOrder());
ps.setBoolean(9, record.getConsultation());
ps.setBoolean(10, record.getProcedures());
ps.setBoolean(11, record.getPatientHistory());
ps.setBoolean(12, record.getAllergies());
ps.setBoolean(13, record.getMedicalWarnings());
ps.setBoolean(14, record.getMedicationsRecord());
ps.setBoolean(15, record.getPsychologicalExam());
ps.setBoolean(16, record.getAudiometryPuretone());
ps.setBoolean(17, record.getOptometricExam());
ps.setBoolean(18, record.getVaccineReccord());
ps.setBoolean(19, record.getDiagnosticsResult());
ps.setBoolean(20, record.getDentalCare());
ps.setBoolean(21, record.getCardiology());
ps.setString(22, record.getCreatedBy());
ps.setString(23, record.getUpdatedBy());
ps.setString(24, record.getDeletedBy());
ps.setBigDecimal(25, record.getCreatedAt());
ps.setBigDecimal(26, record.getUpdatedAt());
ps.setBigDecimal(27, record.getDeletedAt());
ps.setBoolean(28, record.getIsValid());
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApMedicalSheets record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medical_sheets set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApMedicalSheets> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medical_sheets where "+ where);) {
List<ApMedicalSheets> list = new ArrayList<ApMedicalSheets>();
while(rs.next()){
ApMedicalSheets record = new ApMedicalSheets();
record.setKey(rs.getString("key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setPatientDashboard(rs.getBoolean("patient_dashboard"));
record.setClinicalVisit(rs.getBoolean("clinical_visit"));
record.setDiagnosticsOrder(rs.getBoolean("diagnostics_order"));
record.setPrescription(rs.getBoolean("prescription"));
record.setDrugOrder(rs.getBoolean("drug_order"));
record.setConsultation(rs.getBoolean("consultation"));
record.setProcedures(rs.getBoolean("procedures"));
record.setPatientHistory(rs.getBoolean("patient_history"));
record.setAllergies(rs.getBoolean("allergies"));
record.setMedicalWarnings(rs.getBoolean("medical_warnings"));
record.setMedicationsRecord(rs.getBoolean("medications_record"));
record.setPsychologicalExam(rs.getBoolean("psychological_exam"));
record.setAudiometryPuretone(rs.getBoolean("audiometry_puretone"));
record.setOptometricExam(rs.getBoolean("optometric_exam"));
record.setVaccineReccord(rs.getBoolean("vaccine_reccord"));
record.setDiagnosticsResult(rs.getBoolean("diagnostics_result"));
record.setDentalCare(rs.getBoolean("dental_care"));
record.setCardiology(rs.getBoolean("cardiology"));
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
public String saveRecord(ApMedicalSheets record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_medical_sheets values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getDepartmentKey());
ps.setString(3, record.getFacilityKey());
ps.setBoolean(4, record.getPatientDashboard());
ps.setBoolean(5, record.getClinicalVisit());
ps.setBoolean(6, record.getDiagnosticsOrder());
ps.setBoolean(7, record.getPrescription());
ps.setBoolean(8, record.getDrugOrder());
ps.setBoolean(9, record.getConsultation());
ps.setBoolean(10, record.getProcedures());
ps.setBoolean(11, record.getPatientHistory());
ps.setBoolean(12, record.getAllergies());
ps.setBoolean(13, record.getMedicalWarnings());
ps.setBoolean(14, record.getMedicationsRecord());
ps.setBoolean(15, record.getPsychologicalExam());
ps.setBoolean(16, record.getAudiometryPuretone());
ps.setBoolean(17, record.getOptometricExam());
ps.setBoolean(18, record.getVaccineReccord());
ps.setBoolean(19, record.getDiagnosticsResult());
ps.setBoolean(20, record.getDentalCare());
ps.setBoolean(21, record.getCardiology());
ps.setString(22, record.getCreatedBy());
ps.setString(23, record.getUpdatedBy());
ps.setString(24, record.getDeletedBy());
ps.setBigDecimal(25, record.getCreatedAt());
ps.setBigDecimal(26, record.getUpdatedAt());
ps.setBigDecimal(27, record.getDeletedAt());
ps.setBoolean(28, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApMedicalSheetsEntity entity, String lang) {
        Class<?> myClass = ApMedicalSheetsEntity.class;
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
public void translateObject(ApMedicalSheetsEntity entity, String lang) {
        ApMedicalSheetsEntity translated = (ApMedicalSheetsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}