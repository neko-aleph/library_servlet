package org.example.java_servlet.app.models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Program Files/Apache Software Foundation/Tomcat 11.0/webapps/java_servlet_war/WEB-INF/sample.db");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, isBorrowed INTEGER, borrowerId INTEGER);\n");
            statement.execute("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT);\n");
            return connection;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BD.class.getName());
            logger.log(Level.SEVERE, "Ошибка при подключении к SQLite", e);
            return null;
        }
    }
}
