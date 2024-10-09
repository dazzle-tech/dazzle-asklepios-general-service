package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApDepartment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDepartmentDAO;

@Service
@Slf4j
public class ApDepartmentService extends ApDepartmentDAO implements Serializable {


    public ApDepartment getRecordByfacilityKey(String key) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_department where facility_key = '"+key+"'");) {
            ApDepartment record = new ApDepartment();
            if(rs.next()){
                record.setKey(rs.getString("key"));
                record.setFacilityKey(rs.getString("facility_key"));
                record.setName(rs.getString("name"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
            } else { record = null; }
            return record;
        }
    }

}