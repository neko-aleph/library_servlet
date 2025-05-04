package org.example.java_servlet.app.entities;

public class User {
    private int id;
    private String name;
    private String password;
    private boolean admin;

    public User(int id, String name, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
}
