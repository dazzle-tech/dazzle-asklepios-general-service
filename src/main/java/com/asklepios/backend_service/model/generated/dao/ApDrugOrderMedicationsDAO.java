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
import com.asklepios.backend_service.model.generated.pojo.ApDrugOrderMedications;
import com.asklepios.backend_service.model.generated.entity.ApDrugOrderMedicationsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDrugOrderMedicationsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDrugOrderMedications getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_drug_order_medications where key = '"+key+"'");) {
ApDrugOrderMedications record = new ApDrugOrderMedications();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setDrugOrderKey(rs.getString("drug_order_key"));
record.setGenericMedicationsKey(rs.getString("generic_medications_key"));
record.setDrugOrderTypeLkey(rs.getString("drug_order_type_lkey"));
record.setDoseUnitLkey(rs.getString("dose_unit_lkey"));
record.setRoaLkey(rs.getString("roa_lkey"));
record.setFrequency(rs.getBigDecimal("frequency"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setPharmacyDepartmentKey(rs.getString("pharmacy_department_key"));
record.setDose(rs.getBigDecimal("dose"));
record.setNotes(rs.getString("notes"));
record.setPrnIndication(rs.getString("prn_indication"));
record.setSpecialInstructions(rs.getString("special_instructions"));
record.setParametersToMonitor(rs.getString("parameters_to_monitor"));
record.setStartDateTime(rs.getBigDecimal("start_date_time"));
record.setMaximumDose(rs.getBigDecimal("maximum_dose"));
record.setGenericSubstitute(rs.getBoolean("generic_substitute"));
record.setChronicMedication(rs.getBoolean("chronic_medication"));
record.setPatientOwnMedication(rs.getBoolean("patient_own_medication"));
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
} else { record = null; }
return record;
}
}
public void updateRecord(ApDrugOrderMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_drug_order_medications set key = ?, patient_key = ?, visit_key = ?, drug_order_key = ?, generic_medications_key = ?, drug_order_type_lkey = ?, dose_unit_lkey = ?, roa_lkey = ?, frequency = ?, priority_lkey = ?, pharmacy_department_key = ?, dose = ?, notes = ?, prn_indication = ?, special_instructions = ?, parameters_to_monitor = ?, start_date_time = ?, maximum_dose = ?, generic_substitute = ?, chronic_medication = ?, patient_own_medication = ?, administration_instructions = ?, duration = ?, duration_type_lkey = ?, status_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getDrugOrderKey());
ps.setString(5, record.getGenericMedicationsKey());
ps.setString(6, record.getDrugOrderTypeLkey());
ps.setString(7, record.getDoseUnitLkey());
ps.setString(8, record.getRoaLkey());
ps.setBigDecimal(9, record.getFrequency());
ps.setString(10, record.getPriorityLkey());
ps.setString(11, record.getPharmacyDepartmentKey());
ps.setBigDecimal(12, record.getDose());
ps.setString(13, record.getNotes());
ps.setString(14, record.getPrnIndication());
ps.setString(15, record.getSpecialInstructions());
ps.setString(16, record.getParametersToMonitor());
ps.setBigDecimal(17, record.getStartDateTime());
ps.setBigDecimal(18, record.getMaximumDose());
ps.setBoolean(19, record.getGenericSubstitute());
ps.setBoolean(20, record.getChronicMedication());
ps.setBoolean(21, record.getPatientOwnMedication());
ps.setString(22, record.getAdministrationInstructions());
ps.setBigDecimal(23, record.getDuration());
ps.setString(24, record.getDurationTypeLkey());
ps.setString(25, record.getStatusLkey());
ps.setString(26, record.getCreatedBy());
ps.setString(27, record.getUpdatedBy());
ps.setString(28, record.getDeletedBy());
ps.setBigDecimal(29, record.getCreatedAt());
ps.setBigDecimal(30, record.getUpdatedAt());
ps.setBigDecimal(31, record.getDeletedAt());
ps.setString(32, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDrugOrderMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_drug_order_medications set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDrugOrderMedications> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_drug_order_medications where "+ where);) {
List<ApDrugOrderMedications> list = new ArrayList<ApDrugOrderMedications>();
while(rs.next()){
ApDrugOrderMedications record = new ApDrugOrderMedications();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setDrugOrderKey(rs.getString("drug_order_key"));
record.setGenericMedicationsKey(rs.getString("generic_medications_key"));
record.setDrugOrderTypeLkey(rs.getString("drug_order_type_lkey"));
record.setDoseUnitLkey(rs.getString("dose_unit_lkey"));
record.setRoaLkey(rs.getString("roa_lkey"));
record.setFrequency(rs.getBigDecimal("frequency"));
record.setPriorityLkey(rs.getString("priority_lkey"));
record.setPharmacyDepartmentKey(rs.getString("pharmacy_department_key"));
record.setDose(rs.getBigDecimal("dose"));
record.setNotes(rs.getString("notes"));
record.setPrnIndication(rs.getString("prn_indication"));
record.setSpecialInstructions(rs.getString("special_instructions"));
record.setParametersToMonitor(rs.getString("parameters_to_monitor"));
record.setStartDateTime(rs.getBigDecimal("start_date_time"));
record.setMaximumDose(rs.getBigDecimal("maximum_dose"));
record.setGenericSubstitute(rs.getBoolean("generic_substitute"));
record.setChronicMedication(rs.getBoolean("chronic_medication"));
record.setPatientOwnMedication(rs.getBoolean("patient_own_medication"));
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
list.add(record);
}
return list;
}
}
public String saveRecord(ApDrugOrderMedications record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_drug_order_medications values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getDrugOrderKey());
ps.setString(5, record.getGenericMedicationsKey());
ps.setString(6, record.getDrugOrderTypeLkey());
ps.setString(7, record.getDoseUnitLkey());
ps.setString(8, record.getRoaLkey());
ps.setBigDecimal(9, record.getFrequency());
ps.setString(10, record.getPriorityLkey());
ps.setString(11, record.getPharmacyDepartmentKey());
ps.setBigDecimal(12, record.getDose());
ps.setString(13, record.getNotes());
ps.setString(14, record.getPrnIndication());
ps.setString(15, record.getSpecialInstructions());
ps.setString(16, record.getParametersToMonitor());
ps.setBigDecimal(17, record.getStartDateTime());
ps.setBigDecimal(18, record.getMaximumDose());
ps.setBoolean(19, record.getGenericSubstitute());
ps.setBoolean(20, record.getChronicMedication());
ps.setBoolean(21, record.getPatientOwnMedication());
ps.setString(22, record.getAdministrationInstructions());
ps.setBigDecimal(23, record.getDuration());
ps.setString(24, record.getDurationTypeLkey());
ps.setString(25, record.getStatusLkey());
ps.setString(26, record.getCreatedBy());
ps.setString(27, record.getUpdatedBy());
ps.setString(28, record.getDeletedBy());
ps.setBigDecimal(29, record.getCreatedAt());
ps.setBigDecimal(30, record.getUpdatedAt());
ps.setBigDecimal(31, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDrugOrderMedicationsEntity entity, String lang) {
        Class<?> myClass = ApDrugOrderMedicationsEntity.class;
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
public void translateObject(ApDrugOrderMedicationsEntity entity, String lang) {
        ApDrugOrderMedicationsEntity translated = (ApDrugOrderMedicationsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}