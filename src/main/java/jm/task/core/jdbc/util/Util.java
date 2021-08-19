package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/test";
    private final static String USER = "root";
    private final static String PASSW = "12345";

   public Connection getConnectionJDBC() throws SQLException {
       return DriverManager.getConnection(URL, USER, PASSW);
   }



}
