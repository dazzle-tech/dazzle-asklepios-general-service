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
import com.asklepios.backend_service.model.generated.pojo.ApPostProcedureChecklist;
import com.asklepios.backend_service.model.generated.entity.ApPostProcedureChecklistEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPostProcedureChecklistDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPostProcedureChecklist getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_procedure_checklist where key = '"+key+"'");) {
ApPostProcedureChecklist record = new ApPostProcedureChecklist();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setNauseaVomiting(rs.getBoolean("nausea_vomiting"));
record.setAwakeAndOriented(rs.getBoolean("awake_and_oriented"));
record.setToleratingOralFluids(rs.getBoolean("tolerating_oral_fluids"));
record.setAmbulatingIndependently(rs.getBoolean("ambulating_independently"));
record.setVoidedUrine(rs.getBoolean("voided_urine"));
record.setNoActiveBleeding(rs.getBoolean("no_active_bleeding"));
record.setPainScore4(rs.getBoolean("pain_score4"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPostProcedureChecklist record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_procedure_checklist set key = ?, procedure_key = ?, nausea_vomiting = ?, awake_and_oriented = ?, tolerating_oral_fluids = ?, ambulating_independently = ?, voided_urine = ?, no_active_bleeding = ?, pain_score4 = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureKey());
ps.setBoolean(3, record.getNauseaVomiting());
ps.setBoolean(4, record.getAwakeAndOriented());
ps.setBoolean(5, record.getToleratingOralFluids());
ps.setBoolean(6, record.getAmbulatingIndependently());
ps.setBoolean(7, record.getVoidedUrine());
ps.setBoolean(8, record.getNoActiveBleeding());
ps.setBoolean(9, record.getPainScore4());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsvalid());
ps.setString(17, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPostProcedureChecklist record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_procedure_checklist set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPostProcedureChecklist> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_procedure_checklist where "+ where);) {
List<ApPostProcedureChecklist> list = new ArrayList<ApPostProcedureChecklist>();
while(rs.next()){
ApPostProcedureChecklist record = new ApPostProcedureChecklist();
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setNauseaVomiting(rs.getBoolean("nausea_vomiting"));
record.setAwakeAndOriented(rs.getBoolean("awake_and_oriented"));
record.setToleratingOralFluids(rs.getBoolean("tolerating_oral_fluids"));
record.setAmbulatingIndependently(rs.getBoolean("ambulating_independently"));
record.setVoidedUrine(rs.getBoolean("voided_urine"));
record.setNoActiveBleeding(rs.getBoolean("no_active_bleeding"));
record.setPainScore4(rs.getBoolean("pain_score4"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPostProcedureChecklist record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_post_procedure_checklist values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureKey());
ps.setBoolean(3, record.getNauseaVomiting());
ps.setBoolean(4, record.getAwakeAndOriented());
ps.setBoolean(5, record.getToleratingOralFluids());
ps.setBoolean(6, record.getAmbulatingIndependently());
ps.setBoolean(7, record.getVoidedUrine());
ps.setBoolean(8, record.getNoActiveBleeding());
ps.setBoolean(9, record.getPainScore4());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPostProcedureChecklistEntity entity, String lang) {
        Class<?> myClass = ApPostProcedureChecklistEntity.class;
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
public void translateObject(ApPostProcedureChecklistEntity entity, String lang) {
        ApPostProcedureChecklistEntity translated = (ApPostProcedureChecklistEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}