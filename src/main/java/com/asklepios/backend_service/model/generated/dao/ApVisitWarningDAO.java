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
import com.asklepios.backend_service.model.generated.pojo.ApVisitWarning;
import com.asklepios.backend_service.model.generated.entity.ApVisitWarningEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApVisitWarningDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApVisitWarning getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_visit_warning where key = '"+key+"'");) {
ApVisitWarning record = new ApVisitWarning();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setWarningTypeLkey(rs.getString("warning_type_lkey"));
record.setFirstTimeRecorded(rs.getBigDecimal("first_time_recorded"));
record.setActionTake(rs.getString("action_take"));
record.setSourceOfInformationLkey(rs.getString("source_of_information_lkey"));
record.setNotes(rs.getString("notes"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setResolvedBy(rs.getString("resolved_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setResolvedAt(rs.getBigDecimal("resolved_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setSeverityLkey(rs.getString("severity_lkey"));
record.setWarning(rs.getString("warning"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApVisitWarning record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_visit_warning set key = ?, patient_key = ?, visit_key = ?, status_lkey = ?, warning_type_lkey = ?, first_time_recorded = ?, action_take = ?, source_of_information_lkey = ?, notes = ?, cancellation_reason = ?, resolved_by = ?, created_by = ?, updated_by = ?, deleted_by = ?, resolved_at = ?, created_at = ?, updated_at = ?, deleted_at = ?, severity_lkey = ?, warning = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getWarningTypeLkey());
ps.setBigDecimal(6, record.getFirstTimeRecorded());
ps.setString(7, record.getActionTake());
ps.setString(8, record.getSourceOfInformationLkey());
ps.setString(9, record.getNotes());
ps.setString(10, record.getCancellationReason());
ps.setString(11, record.getResolvedBy());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getResolvedAt());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setString(19, record.getSeverityLkey());
ps.setString(20, record.getWarning());
ps.setString(21, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApVisitWarning record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_visit_warning set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApVisitWarning> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_visit_warning where "+ where);) {
List<ApVisitWarning> list = new ArrayList<ApVisitWarning>();
while(rs.next()){
ApVisitWarning record = new ApVisitWarning();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setWarningTypeLkey(rs.getString("warning_type_lkey"));
record.setFirstTimeRecorded(rs.getBigDecimal("first_time_recorded"));
record.setActionTake(rs.getString("action_take"));
record.setSourceOfInformationLkey(rs.getString("source_of_information_lkey"));
record.setNotes(rs.getString("notes"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setResolvedBy(rs.getString("resolved_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setResolvedAt(rs.getBigDecimal("resolved_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setSeverityLkey(rs.getString("severity_lkey"));
record.setWarning(rs.getString("warning"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApVisitWarning record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_visit_warning values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getWarningTypeLkey());
ps.setBigDecimal(6, record.getFirstTimeRecorded());
ps.setString(7, record.getActionTake());
ps.setString(8, record.getSourceOfInformationLkey());
ps.setString(9, record.getNotes());
ps.setString(10, record.getCancellationReason());
ps.setString(11, record.getResolvedBy());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getResolvedAt());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setString(19, record.getSeverityLkey());
ps.setString(20, record.getWarning());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApVisitWarningEntity entity, String lang) {
        Class<?> myClass = ApVisitWarningEntity.class;
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
public void translateObject(ApVisitWarningEntity entity, String lang) {
        ApVisitWarningEntity translated = (ApVisitWarningEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}