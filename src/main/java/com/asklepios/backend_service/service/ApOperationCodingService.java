package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApOperationCoding;
import com.asklepios.backend_service.model.generated.pojo.ApProcedureCoding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApOperationCodingDAO;

@Service
@Slf4j
public class ApOperationCodingService extends ApOperationCodingDAO implements Serializable {
    public void deleteCoding(ApOperationCoding record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from ap_operation_coding  where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

}