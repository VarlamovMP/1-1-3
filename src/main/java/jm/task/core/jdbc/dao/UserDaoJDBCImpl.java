package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    //Этот метод отвечает за создание таблицы пользователей в базе данных.
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "NAME VARCHAR(100), " +
                "LAST_NAME VARCHAR(100), " +
                "AGE TINYINT)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица Users создана или уже существует.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Этот метод удаляет существующую таблицу пользователей из базы данных.
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица Users удалена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы Users: " + e.getMessage());
        }
    }

    //Этот метод сохраняет информацию о пользователе (имя, фамилию и возраст) в таблице пользователей.
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (NAME, LAST_NAME, AGE) VALUES(?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Этот метод удаляет пользователя из таблицы по уникальному идентификатору (ID).
    public void removeUserById(long id) {

        String sql = "DELETE FROM Users WHERE ID=? ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Этот метод возвращает список всех пользователей, хранящихся в таблице пользователей.
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LAST_NAME, AGE FROM Users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    //Этот метод очищает таблицу пользователей, удаляя всех пользователей, но оставляя саму таблицу.
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE Users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица Users очищена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы Users: " + e.getMessage());
        }
    }
}
