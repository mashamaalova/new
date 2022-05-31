package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?user=root&password=root&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "12q34w67";
    private static Connection connection;

    public Util() {

    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    static {
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASS);
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void setConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection() {
        return connection;
    }
}
