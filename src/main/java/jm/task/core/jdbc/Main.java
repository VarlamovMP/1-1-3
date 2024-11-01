package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Анна", "Варламова", (byte)6);
        userService.saveUser("Григорий", "Варламов", (byte)14);
        userService.saveUser("Татьяна", "Варламова", (byte)43);
        userService.saveUser("Михаил", "Варламов", (byte)42);
    }
}
