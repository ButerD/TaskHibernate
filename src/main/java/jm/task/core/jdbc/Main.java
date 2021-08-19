package jm.task.core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private final static String URL = "jdbc:mysql://localhost:3306/test";
    private final static String USER = "root";
    private final static String PASSW = "12345";


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSW)) {
            PreparedStatement ps = connection.prepareStatement("select * from users");



        } catch (SQLException e) {

        }
    }
}
