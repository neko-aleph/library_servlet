package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.java_servlet.app.entities.User;
import org.example.java_servlet.app.models.UserDataBase;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataBase model = UserDataBase.getInstance();

        int id = UserDataBase.getNextId();
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User(id, name, password);
        model.addUser(user);

        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Вы успешно зарегистрированны</h1></body></html>");
    }
}
