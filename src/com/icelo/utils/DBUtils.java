package com.icelo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.*;


public class DBUtils {
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            InputStream is = DBUtils.class.getResourceAsStream("/db.properties");
            PROPERTIES.load(is);
            Class.forName(PROPERTIES.getProperty("driver"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("username"),
                    PROPERTIES.getProperty("password"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
    public static void closeAll(Connection con, Statement stat, ResultSet res) {
        try {

            if (res != null) {
                res.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
