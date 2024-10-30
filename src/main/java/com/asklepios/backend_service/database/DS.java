package com.asklepios.backend_service.database;

import com.asklepios.backend_service.util.Utilities;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Component
public class DS {

    private static Properties props;

    static {
        try {
            props = new Properties();
            props.load(new Utilities().loadResource("datasource.properties"));
            // Load the PostgreSQL driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DS() {
    }

    // Use DriverManager to get a connection directly
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("db.jdbcUrl"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
    }

    public static synchronized int executeQuery(String query) throws SQLException {
        int res = 0;
        try (Connection conn = getConnection();
                Statement st = conn.createStatement()) {
            System.out.println(query);
            res = st.executeUpdate(query);
        }
        return res;
    }

    public static synchronized BigDecimal executeDecimalResultQuery(String query) throws SQLException {
        BigDecimal res = BigDecimal.ZERO;
        try (Connection conn = getConnection();
                Statement st = conn.createStatement()) {
            System.out.println(query);
            try (ResultSet rs = st.executeQuery(query);) {
                if (rs.next()) {
                    res = rs.getBigDecimal(1);
                }
            }
        }
        return res;
    }
}
