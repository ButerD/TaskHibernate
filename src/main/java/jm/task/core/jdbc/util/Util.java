package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/test";
    private final static String USER = "root";
    private final static String PASSW = "12345";
    private   static  SessionFactory sessionFactory ;

    public Connection getConnectionJDBC() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSW);
    }

    public SessionFactory getSessionFactory() throws HibernateException {
        if(sessionFactory == null) {
            Properties properties = new Properties();
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, USER);
            properties.put(Environment.PASS, PASSW);
//            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            sessionFactory = new Configuration().addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public Util() {
    }
}
