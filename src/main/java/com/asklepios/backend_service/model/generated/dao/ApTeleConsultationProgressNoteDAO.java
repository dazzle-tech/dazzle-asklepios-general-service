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
import com.asklepios.backend_service.model.generated.pojo.ApTeleConsultationProgressNote;
import com.asklepios.backend_service.model.generated.entity.ApTeleConsultationProgressNoteEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApTeleConsultationProgressNoteDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApTeleConsultationProgressNote getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tele_consultation_progress_note where id = '"+key+"'");) {
ApTeleConsultationProgressNote record = new ApTeleConsultationProgressNote();
if(rs.next()){
record.setId(rs.getString("id"));
record.setNote(rs.getString("note"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedDate(rs.getBigDecimal("created_date"));
record.setTeleConsultationId(rs.getString("tele_consultation_id"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApTeleConsultationProgressNote record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tele_consultation_progress_note set id = ?, note = ?, created_by = ?, created_date = ?, tele_consultation_id = ? where id = ?");
) {

ps.setString(1, record.getId());
ps.setString(2, record.getNote());
ps.setString(3, record.getCreatedBy());
ps.setBigDecimal(4, record.getCreatedDate());
ps.setString(5, record.getTeleConsultationId());
ps.setString(6, record.getId());
ps.executeUpdate();
}
}
public void deleteRecord(ApTeleConsultationProgressNote record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tele_consultation_progress_note set  deleted_at = '"+System.currentTimeMillis()+"' where id = ?");
) {
ps.setString(1, record.getId());
ps.executeUpdate();
}
}
public List<ApTeleConsultationProgressNote> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tele_consultation_progress_note where "+ where);) {
List<ApTeleConsultationProgressNote> list = new ArrayList<ApTeleConsultationProgressNote>();
while(rs.next()){
ApTeleConsultationProgressNote record = new ApTeleConsultationProgressNote();
record.setId(rs.getString("id"));
record.setNote(rs.getString("note"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedDate(rs.getBigDecimal("created_date"));
record.setTeleConsultationId(rs.getString("tele_consultation_id"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApTeleConsultationProgressNote record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_tele_consultation_progress_note values (?, ?, ?, ?, ?)");
) {
if(record.getId() != null && !record.getId().isEmpty()) {updateRecord(record); return record.getId();}
if (record.getCreatedDate() == null) record.setCreatedDate(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setId(key);

ps.setString(1, key);
ps.setString(2, record.getNote());
ps.setString(3, record.getCreatedBy());
ps.setBigDecimal(4, record.getCreatedDate());
ps.setString(5, record.getTeleConsultationId());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApTeleConsultationProgressNoteEntity entity, String lang) {
        Class<?> myClass = ApTeleConsultationProgressNoteEntity.class;
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
public void translateObject(ApTeleConsultationProgressNoteEntity entity, String lang) {
        ApTeleConsultationProgressNoteEntity translated = (ApTeleConsultationProgressNoteEntity) publicServices.getObjectTranslation(entity.getId(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}