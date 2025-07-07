package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApOperationStaff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApOperationStaffDAO;

@Service
@Slf4j
public class ApOperationStaffService extends ApOperationStaffDAO implements Serializable {

    public void deleteRecord(ApOperationStaff staff) {
        String sql = "DELETE FROM ap_operation_staff WHERE key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, staff.getKey());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}