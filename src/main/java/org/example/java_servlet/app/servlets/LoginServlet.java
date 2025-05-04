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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        UserDataBaseSQLite model = UserDataBaseSQLite.getInstance();
        User user = model.CheckPassword(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            session.setAttribute("id", user.getId());
            session.setAttribute("admin", user.isAdmin());

            resp.sendRedirect("book");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
