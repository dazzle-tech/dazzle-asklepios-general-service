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
import com.asklepios.backend_service.model.generated.pojo.ApOperationArrivalToRecoveryRoom;
import com.asklepios.backend_service.model.generated.entity.ApOperationArrivalToRecoveryRoomEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationArrivalToRecoveryRoomDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationArrivalToRecoveryRoom getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_arrival_to_recovery_room where key = '"+key+"'");) {
ApOperationArrivalToRecoveryRoom record = new ApOperationArrivalToRecoveryRoom();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setArrivalTime(rs.getBigDecimal("arrival_time"));
record.setAccompaniedBy(rs.getString("accompanied_by"));
record.setHandoverSummary(rs.getString("handover_summary"));
record.setInitialAssessmentTime(rs.getBigDecimal("initial_assessment_time"));
record.setResponsibleNurseKey(rs.getString("responsible_nurse_key"));
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
public void updateRecord(ApOperationArrivalToRecoveryRoom record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_arrival_to_recovery_room set key = ?, operation_request_key = ?, arrival_time = ?, accompanied_by = ?, handover_summary = ?, initial_assessment_time = ?, responsible_nurse_key = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getArrivalTime());
ps.setString(4, record.getAccompaniedBy());
ps.setString(5, record.getHandoverSummary());
ps.setBigDecimal(6, record.getInitialAssessmentTime());
ps.setString(7, record.getResponsibleNurseKey());
ps.setString(8, record.getCreatedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setString(10, record.getUpdatedBy());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsvalid());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationArrivalToRecoveryRoom record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_arrival_to_recovery_room set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationArrivalToRecoveryRoom> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_arrival_to_recovery_room where "+ where);) {
List<ApOperationArrivalToRecoveryRoom> list = new ArrayList<ApOperationArrivalToRecoveryRoom>();
while(rs.next()){
ApOperationArrivalToRecoveryRoom record = new ApOperationArrivalToRecoveryRoom();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setArrivalTime(rs.getBigDecimal("arrival_time"));
record.setAccompaniedBy(rs.getString("accompanied_by"));
record.setHandoverSummary(rs.getString("handover_summary"));
record.setInitialAssessmentTime(rs.getBigDecimal("initial_assessment_time"));
record.setResponsibleNurseKey(rs.getString("responsible_nurse_key"));
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
public String saveRecord(ApOperationArrivalToRecoveryRoom record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_arrival_to_recovery_room values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getArrivalTime());
ps.setString(4, record.getAccompaniedBy());
ps.setString(5, record.getHandoverSummary());
ps.setBigDecimal(6, record.getInitialAssessmentTime());
ps.setString(7, record.getResponsibleNurseKey());
ps.setString(8, record.getCreatedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setString(10, record.getUpdatedBy());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationArrivalToRecoveryRoomEntity entity, String lang) {
        Class<?> myClass = ApOperationArrivalToRecoveryRoomEntity.class;
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
public void translateObject(ApOperationArrivalToRecoveryRoomEntity entity, String lang) {
        ApOperationArrivalToRecoveryRoomEntity translated = (ApOperationArrivalToRecoveryRoomEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}