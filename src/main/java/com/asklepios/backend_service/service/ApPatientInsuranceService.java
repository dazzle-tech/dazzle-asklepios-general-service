package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApPatientInsurance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientInsuranceDAO;

@Service
@Slf4j
public class ApPatientInsuranceService extends ApPatientInsuranceDAO implements Serializable {
    public List<ApPatientInsurance> getinsuranceList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from apv_patient_insurance where "+ where);) {
            List<ApPatientInsurance> list = new ArrayList<ApPatientInsurance>();
            while(rs.next()){
                ApPatientInsurance record = new ApPatientInsurance();
                record.setKey(rs.getString("key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setInsuranceProviderLkey(rs.getString("insurance_provider_lkey"));
                record.setInsuranceProvider(rs.getString("insurance_provider"));
                record.setPrimaryInsurance(rs.getBoolean("primary_insurance"));
                record.setInsurancePolicyNumber(rs.getString("insurance_policy_number"));
                record.setGroupNumber(rs.getString("group_number"));
                record.setInsurancePlanTypeLkey(rs.getString("insurance_plan_type_lkey"));
                record.setInsurancePlanType(rs.getString("insurance_plan_type"));
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
                record.setPolicyHolder(rs.getString("Policy_Holder"));
                list.add(record);
            }
            return list;
        }
    }

}