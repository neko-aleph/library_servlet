package org.example.java_servlet.app.models;

import org.example.java_servlet.app.entities.Book;
import org.example.java_servlet.app.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDataBaseSQLite {
    private static UserDataBaseSQLite instance;
    private static Connection connection;

    private UserDataBaseSQLite() {
        connection = BD.connect();
    }

    public static UserDataBaseSQLite getInstance() {
        if (instance == null) {
            instance = new UserDataBaseSQLite();
        }
        return instance;
    }

    public boolean addUser(User user) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE name = ?";
        String insertSql = "INSERT INTO user (name, password) VALUES (?, ?)";

        try {
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, user.getName());
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("User with this name already exists.");
                return false;
            }

            PreparedStatement insertStmt = connection.prepareStatement(insertSql);
            insertStmt.setString(1, user.getName());
            insertStmt.setString(2, user.getPassword());
            insertStmt.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public List<User> getAllUsers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user");
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
                users.add(user);
            }
            return users;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public User CheckPassword(String name, String password) {
        String sql = "select * from user where name = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            boolean exists = rs.next();
            if (exists) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
