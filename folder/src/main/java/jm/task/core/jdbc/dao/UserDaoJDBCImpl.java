package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static PreparedStatement preparedStatement;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Util.setConnection();
        try {
            preparedStatement = Util.getConnection().prepareStatement("create table IF NOT EXISTS users (\n" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "name varchar(100) NOT NULL,\n" +
                    "lastname varchar(100) NOT NULL,\n" +
                    "age TINYINT NOT NULL\n" +
                    ");");
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Не удалось создать таблицу!");
        } finally {
                try {
                    Util.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Util.setConnection();
        try {
            preparedStatement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS users;");
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Не удалось удалить таблицу!");
        } finally {
            try {
                Util.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util.setConnection();
        try {
            preparedStatement = Util.getConnection()
                    .prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?);");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                Util.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Util.setConnection();
        try {
            preparedStatement = Util.getConnection().prepareStatement("DELETE FROM users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                Util.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        ResultSet resultSet = null;
        List<User> listUsers = new ArrayList<>();
        Util.setConnection();
        try {
            preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM mydbtest.users;");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);

            }
            Util.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
                Util.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        Util.setConnection();
        try {
            preparedStatement = Util.getConnection().prepareStatement("DELETE IF EXIST from users");
            preparedStatement.executeUpdate();
            Util.getConnection().commit();
        } catch (SQLException e) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Не удалось очистить тамблицу1");
        } finally {
            try {
                Util.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
