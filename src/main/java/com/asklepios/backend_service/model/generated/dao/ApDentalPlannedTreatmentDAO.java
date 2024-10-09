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
import com.asklepios.backend_service.model.generated.pojo.ApDentalPlannedTreatment;
import com.asklepios.backend_service.model.generated.entity.ApDentalPlannedTreatmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDentalPlannedTreatmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApDentalPlannedTreatment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_dental_planned_treatment where key = '"+key+"'");) {
ApDentalPlannedTreatment record = new ApDentalPlannedTreatment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setType(rs.getString("type"));
record.setVisitNumber(rs.getBigDecimal("visit_number"));
record.setCdtKey(rs.getString("cdt_key"));
record.setToothKey(rs.getString("tooth_key"));
record.setNote(rs.getString("note"));
record.setSurfaceLkey(rs.getString("surface_lkey"));
record.setBillingTypeLkey(rs.getString("billing_type_lkey"));
record.setFees(rs.getBigDecimal("fees"));
record.setInsurance(rs.getBigDecimal("insurance"));
record.setDiscount(rs.getBigDecimal("discount"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setSource(rs.getString("source"));
record.setSourceKey(rs.getString("source_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApDentalPlannedTreatment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_dental_planned_treatment set key = ?, patient_key = ?, encounter_key = ?, type = ?, visit_number = ?, cdt_key = ?, tooth_key = ?, note = ?, surface_lkey = ?, billing_type_lkey = ?, fees = ?, insurance = ?, discount = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, status_lkey = ?, source = ?, source_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getType());
ps.setBigDecimal(5, record.getVisitNumber());
ps.setString(6, record.getCdtKey());
ps.setString(7, record.getToothKey());
ps.setString(8, record.getNote());
ps.setString(9, record.getSurfaceLkey());
ps.setString(10, record.getBillingTypeLkey());
ps.setBigDecimal(11, record.getFees());
ps.setBigDecimal(12, record.getInsurance());
ps.setBigDecimal(13, record.getDiscount());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getStatusLkey());
ps.setString(22, record.getSource());
ps.setString(23, record.getSourceKey());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApDentalPlannedTreatment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_dental_planned_treatment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApDentalPlannedTreatment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_dental_planned_treatment where "+ where);) {
List<ApDentalPlannedTreatment> list = new ArrayList<ApDentalPlannedTreatment>();
while(rs.next()){
ApDentalPlannedTreatment record = new ApDentalPlannedTreatment();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setType(rs.getString("type"));
record.setVisitNumber(rs.getBigDecimal("visit_number"));
record.setCdtKey(rs.getString("cdt_key"));
record.setToothKey(rs.getString("tooth_key"));
record.setNote(rs.getString("note"));
record.setSurfaceLkey(rs.getString("surface_lkey"));
record.setBillingTypeLkey(rs.getString("billing_type_lkey"));
record.setFees(rs.getBigDecimal("fees"));
record.setInsurance(rs.getBigDecimal("insurance"));
record.setDiscount(rs.getBigDecimal("discount"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setSource(rs.getString("source"));
record.setSourceKey(rs.getString("source_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApDentalPlannedTreatment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_dental_planned_treatment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getType());
ps.setBigDecimal(5, record.getVisitNumber());
ps.setString(6, record.getCdtKey());
ps.setString(7, record.getToothKey());
ps.setString(8, record.getNote());
ps.setString(9, record.getSurfaceLkey());
ps.setString(10, record.getBillingTypeLkey());
ps.setBigDecimal(11, record.getFees());
ps.setBigDecimal(12, record.getInsurance());
ps.setBigDecimal(13, record.getDiscount());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getStatusLkey());
ps.setString(22, record.getSource());
ps.setString(23, record.getSourceKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApDentalPlannedTreatmentEntity entity, String lang) {
        Class<?> myClass = ApDentalPlannedTreatmentEntity.class;
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
public void translateObject(ApDentalPlannedTreatmentEntity entity, String lang) {
        ApDentalPlannedTreatmentEntity translated = (ApDentalPlannedTreatmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}