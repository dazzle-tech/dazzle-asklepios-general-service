package com.asklepios.backend_service.database;

import com.asklepios.backend_service.util.Utilities;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Component
public class DS {

        private static HikariConfig config;
    private static HikariDataSource ds;
    private static Properties props;

    static {
        try {
            props = new Properties();
            props.load(new Utilities().loadResource("datasource.properties"));
            config = new HikariConfig(props);
            config.setDriverClassName(org.postgresql.Driver.class.getName());
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DS() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection connection = DriverManager.getConnection(
//                props.getProperty("jdbcUrl"), props.getProperty("dataSource.user"),
//                props.getProperty("dataSource.password"));
//        return connection;
    }

    public static synchronized int executeQuery(String query) throws SQLException {
        int res = 0;
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();) {
            System.out.println(query);
            res = st.executeUpdate(query);
            st.close();
        }

        return res;
    }

    public static synchronized BigDecimal executeDecimalResultQuery(String query) throws SQLException {
        BigDecimal res = BigDecimal.ZERO;
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            res = rs.getBigDecimal(1);
        }
        rs.close();
        st.close();
        conn.close();
        return res;
    }

    public static HikariDataSource getDs() {
        return ds;
    }

    public static void setDs(HikariDataSource ds) {
        DS.ds = ds;
    }
}
