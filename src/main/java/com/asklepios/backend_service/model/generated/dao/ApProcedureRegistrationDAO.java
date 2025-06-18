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
import com.asklepios.backend_service.model.generated.pojo.ApProcedureRegistration;
import com.asklepios.backend_service.model.generated.entity.ApProcedureRegistrationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApProcedureRegistrationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApProcedureRegistration getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_registration where key = '"+key+"'");) {
ApProcedureRegistration record = new ApProcedureRegistration();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setConsentFormLkey(rs.getString("consent_form_lkey"));
record.setDateTime(rs.getBigDecimal("date_time"));
record.setPractitionersKey(rs.getString("practitioners_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setRequestedBy(rs.getString("requested_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApProcedureRegistration record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_registration set key = ?, procedure_key = ?, consent_form_lkey = ?, date_time = ?, practitioners_key = ?, department_key = ?, requested_by = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, encounter_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureKey());
ps.setString(3, record.getConsentFormLkey());
ps.setBigDecimal(4, record.getDateTime());
ps.setString(5, record.getPractitionersKey());
ps.setString(6, record.getDepartmentKey());
ps.setString(7, record.getRequestedBy());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setString(14, record.getEncounterKey());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApProcedureRegistration record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_procedure_registration set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApProcedureRegistration> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_procedure_registration where "+ where);) {
List<ApProcedureRegistration> list = new ArrayList<ApProcedureRegistration>();
while(rs.next()){
ApProcedureRegistration record = new ApProcedureRegistration();
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setConsentFormLkey(rs.getString("consent_form_lkey"));
record.setDateTime(rs.getBigDecimal("date_time"));
record.setPractitionersKey(rs.getString("practitioners_key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setRequestedBy(rs.getString("requested_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApProcedureRegistration record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_procedure_registration values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureKey());
ps.setString(3, record.getConsentFormLkey());
ps.setBigDecimal(4, record.getDateTime());
ps.setString(5, record.getPractitionersKey());
ps.setString(6, record.getDepartmentKey());
ps.setString(7, record.getRequestedBy());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setString(14, record.getEncounterKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApProcedureRegistrationEntity entity, String lang) {
        Class<?> myClass = ApProcedureRegistrationEntity.class;
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
public void translateObject(ApProcedureRegistrationEntity entity, String lang) {
        ApProcedureRegistrationEntity translated = (ApProcedureRegistrationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}