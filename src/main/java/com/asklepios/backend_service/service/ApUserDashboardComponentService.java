package com.asklepios.backend_service.service;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.dao.ApUserDashboardComponentsDAO;
import com.asklepios.backend_service.model.generated.entity.ApUserDashboardComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApUserDashboardComponentService extends ApUserDashboardComponentsDAO implements Serializable {
    public List<ApUserDashboardComponents> getAllByUser(BigDecimal user_id) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_user_dashboard_components where user_id = '"+user_id+"'");) {
            List<ApUserDashboardComponents> list = new ArrayList<>();
            while(rs.next()){
                ApUserDashboardComponents record = new ApUserDashboardComponents();
                record.setKey(rs.getString("key"));
                record.setUser_id(rs.getBigDecimal("user_id"));
                record.setComponent_key(rs.getString("component_key"));
                list.add(record);
            }
            return list;
        }
    }

    public void deleteComponent(ApUserDashboardComponents record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM ap_user_dashboard_components\n" +
                        "WHERE user_id = ?\n" +
                        "  AND component_key = ?;\n");
        ) {
            ps.setBigDecimal(1, record.getUser_id());
            ps.setString(2, record.getComponent_key());
            ps.executeUpdate();
        }
    }

    public String addComponent(ApUserDashboardComponents record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into ap_user_dashboard_components values (?,?, ?)");
        ) {
            String key = "" + System.nanoTime();
            record.setKey(key);
            ps.setString(1, key);
            ps.setBigDecimal(2, record.getUser_id());
            ps.setString(3, record.getComponent_key());
            ps.executeUpdate();
            return key;
        }
    }


}
