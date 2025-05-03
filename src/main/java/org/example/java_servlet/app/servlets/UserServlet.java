package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.java_servlet.app.entities.User;
import org.example.java_servlet.app.models.UserDataBase;
import org.example.java_servlet.app.models.UserDataBaseSQLite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        UserDataBaseSQLite model = UserDataBaseSQLite.getInstance();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User(0, name, password);
        boolean success = model.addUser(user);

        if (success) {
            out.println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Вы успешно зарегистрированны</h1>\n" +
                    "    <button onclick=\"history.back()\">Назад</button></body></html>");
        } else {
            out.println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Ошибка при регистрации</h1>\n" +
                    "    <button onclick=\"history.back()\">Назад</button></body></html>");
        }
    }
}
