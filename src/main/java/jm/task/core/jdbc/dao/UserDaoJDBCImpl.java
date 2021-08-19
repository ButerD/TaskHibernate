package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
     final String USERTABLE = "users";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Connection  connection = new Util().getConnectionJDBC()) {
            String sql = String.format("CREATE TABLE %s(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT)", USERTABLE);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
//            System.out.println("Error createUserTable: " + e.getMessage());
        }

    }

    public void dropUsersTable() {
        try(Connection  connection = new Util().getConnectionJDBC()) {
            String sql = String.format("DROP TABLE %s", USERTABLE);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error dropUser: " + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection  connection = new Util().getConnectionJDBC()) {
            String sql = String.format("INSERT INTO %s(name, lastName, age) VALUES (?, ?, ?)", USERTABLE);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saveUser: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try(Connection  connection = new Util().getConnectionJDBC()) {
            String sql = String.format("DELETE FROM %s WHERE id = ?", USERTABLE);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, (int) id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in removeUser: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Connection  connection = new Util().getConnectionJDBC()) {
            String sql = String.format("SELECT * FROM %s", USERTABLE);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                String name =  resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int age = resultSet.getInt(4);
                User user = new User(name, lastName, (byte)age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error getAllUser: " + e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection  connection = new Util().getConnectionJDBC()) {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM %s", USERTABLE));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error cleanTable: " + e.getMessage());
        }

    }
}
