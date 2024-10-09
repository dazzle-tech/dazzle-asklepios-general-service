package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApPatientInsuranceCoverage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientInsuranceCoverageDAO;

@Service
@Slf4j
public class ApPatientInsuranceCoverageService extends ApPatientInsuranceCoverageDAO implements Serializable {

    public List<ApPatientInsuranceCoverage> getListView(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from apv_patient_insurance_coverage where "+ where);) {
            List<ApPatientInsuranceCoverage> list = new ArrayList<ApPatientInsuranceCoverage>();
            while(rs.next()){
                ApPatientInsuranceCoverage record = new ApPatientInsuranceCoverage();
                record.setKey(rs.getString("key"));
                record.setPatientInsuranceKey(rs.getString("patient_insurance_key"));
                record.setTypeLkey(rs.getString("type_lkey"));
                record.setCoverageTypeLkey(rs.getString("coverage_type_lkey"));
                record.setCoveredAmount(rs.getBigDecimal("covered_amount"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setCoverageType(rs.getString("coverage_type"));
                record.setType(rs.getString("type"));
                list.add(record);
            }
            return list;
        }
    }

}