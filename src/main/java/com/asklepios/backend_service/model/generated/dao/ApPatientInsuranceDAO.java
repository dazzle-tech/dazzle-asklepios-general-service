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
import com.asklepios.backend_service.model.generated.pojo.ApPatientInsurance;
import com.asklepios.backend_service.model.generated.entity.ApPatientInsuranceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientInsuranceDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientInsurance getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_insurance where key = '"+key+"'");) {
ApPatientInsurance record = new ApPatientInsurance();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setInsuranceProviderLkey(rs.getString("insurance_provider_lkey"));
record.setPrimaryInsurance(rs.getBoolean("primary_insurance"));
record.setInsurancePolicyNumber(rs.getString("insurance_policy_number"));
record.setGroupNumber(rs.getString("group_number"));
record.setInsurancePlanTypeLkey(rs.getString("insurance_plan_type_lkey"));
record.setAuthorizationNumbers(rs.getString("authorization_numbers"));
record.setExpirationDate(rs.getDate("expiration_date"));
record.setCoPayment(rs.getBoolean("co_payment"));
record.setCoPaymentValue(rs.getBigDecimal("co_payment_value"));
record.setCoInsurance(rs.getBoolean("co_insurance"));
record.setCoInsuranceValue(rs.getBigDecimal("co_insurance_value"));
record.setDeductibles(rs.getBoolean("deductibles"));
record.setDeductiblesValue(rs.getBigDecimal("deductibles_value"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setPolicyHolder(rs.getString("policy_holder"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientInsurance record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_insurance set key = ?, patient_key = ?, insurance_provider_lkey = ?, primary_insurance = ?, insurance_policy_number = ?, group_number = ?, insurance_plan_type_lkey = ?, authorization_numbers = ?, expiration_date = ?, co_payment = ?, co_payment_value = ?, co_insurance = ?, co_insurance_value = ?, deductibles = ?, deductibles_value = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, policy_holder = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getInsuranceProviderLkey());
ps.setBoolean(4, record.getPrimaryInsurance());
ps.setString(5, record.getInsurancePolicyNumber());
ps.setString(6, record.getGroupNumber());
ps.setString(7, record.getInsurancePlanTypeLkey());
ps.setString(8, record.getAuthorizationNumbers());
if (record.getExpirationDate() != null) ps.setDate(9, new java.sql.Date(record.getExpirationDate().getTime()));
else ps.setDate(9, null); 
ps.setBoolean(10, record.getCoPayment());
ps.setBigDecimal(11, record.getCoPaymentValue());
ps.setBoolean(12, record.getCoInsurance());
ps.setBigDecimal(13, record.getCoInsuranceValue());
ps.setBoolean(14, record.getDeductibles());
ps.setBigDecimal(15, record.getDeductiblesValue());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsValid());
ps.setString(23, record.getPolicyHolder());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientInsurance record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_insurance set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientInsurance> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_insurance where "+ where);) {
List<ApPatientInsurance> list = new ArrayList<ApPatientInsurance>();
while(rs.next()){
ApPatientInsurance record = new ApPatientInsurance();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setInsuranceProviderLkey(rs.getString("insurance_provider_lkey"));
record.setPrimaryInsurance(rs.getBoolean("primary_insurance"));
record.setInsurancePolicyNumber(rs.getString("insurance_policy_number"));
record.setGroupNumber(rs.getString("group_number"));
record.setInsurancePlanTypeLkey(rs.getString("insurance_plan_type_lkey"));
record.setAuthorizationNumbers(rs.getString("authorization_numbers"));
record.setExpirationDate(rs.getDate("expiration_date"));
record.setCoPayment(rs.getBoolean("co_payment"));
record.setCoPaymentValue(rs.getBigDecimal("co_payment_value"));
record.setCoInsurance(rs.getBoolean("co_insurance"));
record.setCoInsuranceValue(rs.getBigDecimal("co_insurance_value"));
record.setDeductibles(rs.getBoolean("deductibles"));
record.setDeductiblesValue(rs.getBigDecimal("deductibles_value"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setPolicyHolder(rs.getString("policy_holder"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientInsurance record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_insurance values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getInsuranceProviderLkey());
ps.setBoolean(4, record.getPrimaryInsurance());
ps.setString(5, record.getInsurancePolicyNumber());
ps.setString(6, record.getGroupNumber());
ps.setString(7, record.getInsurancePlanTypeLkey());
ps.setString(8, record.getAuthorizationNumbers());
if (record.getExpirationDate() != null) ps.setDate(9, new java.sql.Date(record.getExpirationDate().getTime()));
else ps.setDate(9, null); 
ps.setBoolean(10, record.getCoPayment());
ps.setBigDecimal(11, record.getCoPaymentValue());
ps.setBoolean(12, record.getCoInsurance());
ps.setBigDecimal(13, record.getCoInsuranceValue());
ps.setBoolean(14, record.getDeductibles());
ps.setBigDecimal(15, record.getDeductiblesValue());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsValid());
ps.setString(23, record.getPolicyHolder());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientInsuranceEntity entity, String lang) {
        Class<?> myClass = ApPatientInsuranceEntity.class;
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
public void translateObject(ApPatientInsuranceEntity entity, String lang) {
        ApPatientInsuranceEntity translated = (ApPatientInsuranceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}