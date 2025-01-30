package com.asklepios.backend_service.database;

import com.asklepios.backend_service.util.Utilities;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DS {

    private static HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();
            props.load(new Utilities().loadResource("datasource.properties"));

            // HikariCP configuration
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.jdbcUrl"));
            config.setUsername(props.getProperty("db.user"));
            config.setPassword(props.getProperty("db.password"));
            config.setMaximumPoolSize(10); // Adjust pool size as needed
            config.setMinimumIdle(5);
            config.setIdleTimeout(30000);
            config.setMaxLifetime(1800000);

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize HikariCP connection pool", e);
        }
    }

    private DS() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static int executeQuery(String query) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            System.out.println(query);
            return ps.executeUpdate();
        }
    }

    public static BigDecimal executeDecimalResultQuery(String query) throws SQLException {
        BigDecimal result = BigDecimal.ZERO;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            System.out.println(query);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBigDecimal(1);
                }
            }
        }
        return result;
    }
    public static String executeStringResultQuery(String query) throws SQLException {
        String result = null;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            System.out.println(query);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString(1);
                }
            }
        }
        return result;
    }

}
