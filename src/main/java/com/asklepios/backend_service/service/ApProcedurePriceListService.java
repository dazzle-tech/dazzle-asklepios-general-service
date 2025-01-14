package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApProcedurePriceList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApProcedurePriceListDAO;

@Service
@Slf4j
public class ApProcedurePriceListService extends ApProcedurePriceListDAO implements Serializable {
    public void deletePriceList(ApProcedurePriceList record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from ap_procedure_price_list  where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

}