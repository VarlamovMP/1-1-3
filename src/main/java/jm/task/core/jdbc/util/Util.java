package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost/user";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("Соединение установлено ....");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error ");

        }
        return connection;
    }



    public static SessionFactory sessionFactory ;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
        try {
            Configuration configuration = new Configuration();


                Properties settings = new Properties();
                settings.put(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.JAKARTA_JDBC_URL, "dbc:mysql://localhost:3306/users");
                settings.put(Environment.JAKARTA_JDBC_USER, "root");
                settings.put(Environment.JAKARTA_JDBC_PASSWORD, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                 configuration.setProperties(settings);

                 configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             //   sessionFactory.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}













