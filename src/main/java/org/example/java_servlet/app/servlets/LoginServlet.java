package org.example.java_servlet.app.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.java_servlet.app.entities.User;
import org.example.java_servlet.app.models.UserDataBaseSQLite;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("password");

        UserDataBaseSQLite model = UserDataBaseSQLite.getInstance();
        User user = model.CheckPassword(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("id", user.getId());

            response.sendRedirect("book");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
