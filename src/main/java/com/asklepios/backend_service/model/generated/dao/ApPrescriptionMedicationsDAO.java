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
import com.asklepios.backend_service.model.generated.pojo.ApPrescriptionMedications;
import com.asklepios.backend_service.model.generated.entity.ApPrescriptionMedicationsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPrescriptionMedicationsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPrescriptionMedications getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_prescription_medications where key = '"+key+"'");) {
ApPrescriptionMedications record = new ApPrescriptionMedications();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setPrescriptionKey(rs.getString("prescription_key"));
record.setGenericMedicationsKey(rs.getString("generic_medications_key"));
record.setNumberOfRefills(rs.getBigDecimal("number_of_refills"));
record.setRefillInterval(rs.getString("refill_interval"));
record.setInstructionsTypeLkey(rs.getString("instructions_type_lkey"));
record.setInstructions(rs.getString("instructions"));
record.setNotes(rs.getString("notes"));
record.setParametersToMonitor(rs.getString("parameters_to_monitor"));
record.setValidUtil(rs.getDate("valid_util"));
record.setMaximumDose(rs.getBigDecimal("maximum_dose"));
record.setGenericSubstitute(rs.getBoolean("generic_substitute"));
record.setChronicMedication(rs.getBoolean("chronic_medication"));
record.setAdministrationInstructions(rs.getString("administration_instructions"));
record.setDuration(rs.getBigDecimal("duration"));
record.setDurationTypeLkey(rs.getString("duration_type_lkey"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setRefillIntervalValue(rs.getBigDecimal("refill_interval_value"));
record.setRefillIntervalUnitLkey(rs.getString("refill_interval_unit_lkey"));
record.setIndicationManually(rs.getString("indication_manually"));
record.setIndicationUseLkey(rs.getString("indication_use_lkey"));
record.setIndicationIcd(rs.getString("indication_icd"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPrescriptionMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_prescription_medications set key = ?, patient_key = ?, visit_key = ?, prescription_key = ?, generic_medications_key = ?, number_of_refills = ?, refill_interval = ?, instructions_type_lkey = ?, instructions = ?, notes = ?, parameters_to_monitor = ?, valid_util = ?, maximum_dose = ?, generic_substitute = ?, chronic_medication = ?, administration_instructions = ?, duration = ?, duration_type_lkey = ?, status_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, refill_interval_value = ?, refill_interval_unit_lkey = ?, indication_manually = ?, indication_use_lkey = ?, indication_icd = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getPrescriptionKey());
ps.setString(5, record.getGenericMedicationsKey());
ps.setBigDecimal(6, record.getNumberOfRefills());
ps.setString(7, record.getRefillInterval());
ps.setString(8, record.getInstructionsTypeLkey());
ps.setString(9, record.getInstructions());
ps.setString(10, record.getNotes());
ps.setString(11, record.getParametersToMonitor());
if (record.getValidUtil() != null) ps.setDate(12, new java.sql.Date(record.getValidUtil().getTime()));
else ps.setDate(12, null); 
ps.setBigDecimal(13, record.getMaximumDose());
ps.setBoolean(14, record.getGenericSubstitute());
ps.setBoolean(15, record.getChronicMedication());
ps.setString(16, record.getAdministrationInstructions());
ps.setBigDecimal(17, record.getDuration());
ps.setString(18, record.getDurationTypeLkey());
ps.setString(19, record.getStatusLkey());
ps.setString(20, record.getCreatedBy());
ps.setString(21, record.getUpdatedBy());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getCreatedAt());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setBigDecimal(25, record.getDeletedAt());
ps.setBoolean(26, record.getIsValid());
ps.setBigDecimal(27, record.getRefillIntervalValue());
ps.setString(28, record.getRefillIntervalUnitLkey());
ps.setString(29, record.getIndicationManually());
ps.setString(30, record.getIndicationUseLkey());
ps.setString(31, record.getIndicationIcd());
ps.setString(32, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPrescriptionMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_prescription_medications set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPrescriptionMedications> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_prescription_medications where "+ where);) {
List<ApPrescriptionMedications> list = new ArrayList<ApPrescriptionMedications>();
while(rs.next()){
ApPrescriptionMedications record = new ApPrescriptionMedications();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setPrescriptionKey(rs.getString("prescription_key"));
record.setGenericMedicationsKey(rs.getString("generic_medications_key"));
record.setNumberOfRefills(rs.getBigDecimal("number_of_refills"));
record.setRefillInterval(rs.getString("refill_interval"));
record.setInstructionsTypeLkey(rs.getString("instructions_type_lkey"));
record.setInstructions(rs.getString("instructions"));
record.setNotes(rs.getString("notes"));
record.setParametersToMonitor(rs.getString("parameters_to_monitor"));
record.setValidUtil(rs.getDate("valid_util"));
record.setMaximumDose(rs.getBigDecimal("maximum_dose"));
record.setGenericSubstitute(rs.getBoolean("generic_substitute"));
record.setChronicMedication(rs.getBoolean("chronic_medication"));
record.setAdministrationInstructions(rs.getString("administration_instructions"));
record.setDuration(rs.getBigDecimal("duration"));
record.setDurationTypeLkey(rs.getString("duration_type_lkey"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setRefillIntervalValue(rs.getBigDecimal("refill_interval_value"));
record.setRefillIntervalUnitLkey(rs.getString("refill_interval_unit_lkey"));
record.setIndicationManually(rs.getString("indication_manually"));
record.setIndicationUseLkey(rs.getString("indication_use_lkey"));
record.setIndicationIcd(rs.getString("indication_icd"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPrescriptionMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_prescription_medications values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getPrescriptionKey());
ps.setString(5, record.getGenericMedicationsKey());
ps.setBigDecimal(6, record.getNumberOfRefills());
ps.setString(7, record.getRefillInterval());
ps.setString(8, record.getInstructionsTypeLkey());
ps.setString(9, record.getInstructions());
ps.setString(10, record.getNotes());
ps.setString(11, record.getParametersToMonitor());
if (record.getValidUtil() != null) ps.setDate(12, new java.sql.Date(record.getValidUtil().getTime()));
else ps.setDate(12, null); 
ps.setBigDecimal(13, record.getMaximumDose());
ps.setBoolean(14, record.getGenericSubstitute());
ps.setBoolean(15, record.getChronicMedication());
ps.setString(16, record.getAdministrationInstructions());
ps.setBigDecimal(17, record.getDuration());
ps.setString(18, record.getDurationTypeLkey());
ps.setString(19, record.getStatusLkey());
ps.setString(20, record.getCreatedBy());
ps.setString(21, record.getUpdatedBy());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getCreatedAt());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setBigDecimal(25, record.getDeletedAt());
ps.setBoolean(26, record.getIsValid());
ps.setBigDecimal(27, record.getRefillIntervalValue());
ps.setString(28, record.getRefillIntervalUnitLkey());
ps.setString(29, record.getIndicationManually());
ps.setString(30, record.getIndicationUseLkey());
ps.setString(31, record.getIndicationIcd());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPrescriptionMedicationsEntity entity, String lang) {
        Class<?> myClass = ApPrescriptionMedicationsEntity.class;
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
public void translateObject(ApPrescriptionMedicationsEntity entity, String lang) {
        ApPrescriptionMedicationsEntity translated = (ApPrescriptionMedicationsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}