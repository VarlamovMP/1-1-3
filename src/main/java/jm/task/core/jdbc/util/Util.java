package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mybd1.1.3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root";

    public Util() {
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybd1.1.3", "root", "Root");
            System.out.println("Connection OK");
        } catch (SQLException | ClassNotFoundException var2) {
            Exception e = var2;
            ((Exception)e).printStackTrace();
            System.out.println("Connection ERROR");
        }

        return connection;
    }
}
