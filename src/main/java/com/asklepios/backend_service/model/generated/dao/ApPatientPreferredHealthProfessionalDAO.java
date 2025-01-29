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
import com.asklepios.backend_service.model.generated.pojo.ApPatientPreferredHealthProfessional;
import com.asklepios.backend_service.model.generated.entity.ApPatientPreferredHealthProfessionalEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientPreferredHealthProfessionalDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientPreferredHealthProfessional getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_preferred_health_professional where key = '"+key+"'");) {
ApPatientPreferredHealthProfessional record = new ApPatientPreferredHealthProfessional();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPractitionerKey(rs.getString("practitioner_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setNetworkAffiliation(rs.getString("network_affiliation"));
record.setRelatedWith(rs.getString("related_with"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setPatientKey(rs.getString("patient_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientPreferredHealthProfessional record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_preferred_health_professional set key = ?, practitioner_key = ?, facility_key = ?, created_by = ?, network_affiliation = ?, related_with = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, patient_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPractitionerKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getNetworkAffiliation());
ps.setString(6, record.getRelatedWith());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setString(12, record.getPatientKey());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientPreferredHealthProfessional record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_preferred_health_professional set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientPreferredHealthProfessional> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_preferred_health_professional where "+ where);) {
List<ApPatientPreferredHealthProfessional> list = new ArrayList<ApPatientPreferredHealthProfessional>();
while(rs.next()){
ApPatientPreferredHealthProfessional record = new ApPatientPreferredHealthProfessional();
record.setKey(rs.getString("key"));
record.setPractitionerKey(rs.getString("practitioner_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setNetworkAffiliation(rs.getString("network_affiliation"));
record.setRelatedWith(rs.getString("related_with"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setPatientKey(rs.getString("patient_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientPreferredHealthProfessional record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_preferred_health_professional values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPractitionerKey());
ps.setString(3, record.getFacilityKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getNetworkAffiliation());
ps.setString(6, record.getRelatedWith());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setString(12, record.getPatientKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientPreferredHealthProfessionalEntity entity, String lang) {
        Class<?> myClass = ApPatientPreferredHealthProfessionalEntity.class;
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
public void translateObject(ApPatientPreferredHealthProfessionalEntity entity, String lang) {
        ApPatientPreferredHealthProfessionalEntity translated = (ApPatientPreferredHealthProfessionalEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}