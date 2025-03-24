package org.example.java_servlet.app.models;

import org.example.java_servlet.app.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private static UserDataBase instance;
    private List<User> users;
    private static int nextId;

    private UserDataBase() {
        users = new ArrayList<>();
        nextId = 1;
    }

    public static UserDataBase getInstance() {
        if (instance == null) {
            instance = new UserDataBase();
        }
        return instance;
    }

    public static int getNextId() {
        return nextId++;
    }

    public int addUser(User user) {
        users.add(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User CheckPassword(String name, String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.checkPassword(password)) {
                return user;
            }
        }

        return null;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}
