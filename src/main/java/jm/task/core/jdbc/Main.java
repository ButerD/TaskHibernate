package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private final static String URL = "jdbc:mysql://localhost:3306/test";
    private final static String USER = "root";
    private final static String PASSW = "12345";


    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Vasya", "Petrov", (byte)23);
        service.saveUser("Oleg", "Olegovich", (byte)21);
        service.saveUser("Ivan", "Ivanov", (byte)40);
        service.saveUser("Olga", "Sitkova", (byte)35);

        List<User> list = service.getAllUsers();
        list.forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
