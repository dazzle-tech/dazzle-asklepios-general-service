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
import com.asklepios.backend_service.model.generated.pojo.ApOperationDischargeToWard;
import com.asklepios.backend_service.model.generated.entity.ApOperationDischargeToWardEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationDischargeToWardDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationDischargeToWard getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_discharge_to_ward where key = '"+key+"'");) {
ApOperationDischargeToWard record = new ApOperationDischargeToWard();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setReturnToDifferentWard(rs.getBoolean("return_to_different_ward"));
record.setDesignationLkey(rs.getString("designation_lkey"));
record.setTransferTime(rs.getBigDecimal("transfer_time"));
record.setReceivingNurseKey(rs.getString("receiving_nurse_key"));
record.setFinalNotes(rs.getString("final_notes"));
record.setPatientIdBandRechecked(rs.getBoolean("patient_id_band_rechecked"));
record.setTransportMode(rs.getString("transport_mode"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationDischargeToWard record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_discharge_to_ward set key = ?, operation_request_key = ?, return_to_different_ward = ?, designation_lkey = ?, transfer_time = ?, receiving_nurse_key = ?, final_notes = ?, patient_id_band_rechecked = ?, transport_mode = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setBoolean(3, record.getReturnToDifferentWard());
ps.setString(4, record.getDesignationLkey());
ps.setBigDecimal(5, record.getTransferTime());
ps.setString(6, record.getReceivingNurseKey());
ps.setString(7, record.getFinalNotes());
ps.setBoolean(8, record.getPatientIdBandRechecked());
ps.setString(9, record.getTransportMode());
ps.setString(10, record.getCreatedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setString(12, record.getUpdatedBy());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsvalid());
ps.setString(17, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationDischargeToWard record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_discharge_to_ward set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationDischargeToWard> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_discharge_to_ward where "+ where);) {
List<ApOperationDischargeToWard> list = new ArrayList<ApOperationDischargeToWard>();
while(rs.next()){
ApOperationDischargeToWard record = new ApOperationDischargeToWard();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setReturnToDifferentWard(rs.getBoolean("return_to_different_ward"));
record.setDesignationLkey(rs.getString("designation_lkey"));
record.setTransferTime(rs.getBigDecimal("transfer_time"));
record.setReceivingNurseKey(rs.getString("receiving_nurse_key"));
record.setFinalNotes(rs.getString("final_notes"));
record.setPatientIdBandRechecked(rs.getBoolean("patient_id_band_rechecked"));
record.setTransportMode(rs.getString("transport_mode"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationDischargeToWard record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_discharge_to_ward values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setBoolean(3, record.getReturnToDifferentWard());
ps.setString(4, record.getDesignationLkey());
ps.setBigDecimal(5, record.getTransferTime());
ps.setString(6, record.getReceivingNurseKey());
ps.setString(7, record.getFinalNotes());
ps.setBoolean(8, record.getPatientIdBandRechecked());
ps.setString(9, record.getTransportMode());
ps.setString(10, record.getCreatedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setString(12, record.getUpdatedBy());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationDischargeToWardEntity entity, String lang) {
        Class<?> myClass = ApOperationDischargeToWardEntity.class;
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
public void translateObject(ApOperationDischargeToWardEntity entity, String lang) {
        ApOperationDischargeToWardEntity translated = (ApOperationDischargeToWardEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}