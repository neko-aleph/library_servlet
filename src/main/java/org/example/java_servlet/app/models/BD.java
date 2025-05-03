package org.example.java_servlet.app.models;

import java.sql.*;
        import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {
    private static Connection con = null;

    public static Connection connect() {
        if (con != null) {
            return con;
        }

        try {
            Class.forName("org.sqlite.JDBC");
//            con = DriverManager.getConnection("jdbc:sqlite:C:/Program Files/Apache Software Foundation/Tomcat 11.0/webapps/library_app/WEB-INF/sample.db");
            con = DriverManager.getConnection("jdbc:sqlite:C:/Users/pc/IdeaProjects/java_servlet/src/main/webapp/WEB-INF/sample.db");

            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, isBorrowed INTEGER, borrowerId INTEGER);");
            statement.execute("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT);");

            return con;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(BD.class.getName());
            logger.log(Level.SEVERE, "Ошибка при подключении к SQLite", e);
            return null;
        }
    }
}