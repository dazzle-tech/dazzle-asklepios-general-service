package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApProcedureServiceEquipment;
import com.asklepios.backend_service.model.generated.pojo.ApProcedureStaff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApProcedureServiceEquipmentDAO;

@Service
@Slf4j
public class ApProcedureServiceEquipmentService extends ApProcedureServiceEquipmentDAO implements Serializable {
    public void deleteRecord(ApProcedureServiceEquipment p) {
        String sql = "DELETE FROM ap_procedure_service_equipment WHERE key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, p.getKey());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}