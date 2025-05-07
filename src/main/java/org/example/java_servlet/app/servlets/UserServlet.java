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

        User user = new User(0, name, password, false);
        boolean success = model.addUser(user);

        if (success) {
            out.println("<style>body {\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            justify-content: center;\n" +
                    "            align-content: center;\n" +
                    "            align-items: center;\n" +
                    "            font-family: sans-serif;\n" +
                    "            background: #ffffff;\n" +
                    "        }" +
                    "button {\n" +
                    "            height: 40px;\n" +
                    "            width: 200px;\n" +
                    "            border: none;\n" +
                    "            background-color: blue;\n" +
                    "            color: white;\n" +
                    "            font-weight: bold;\n" +
                    "            border-radius: 12px;\n" +
                    "        }\n" +
                    "        button:hover {\n" +
                    "            cursor: pointer;\n" +
                    "            background-color: #2222ff;\n" +
                    "        }\n" +
                    "        button:active {\n" +
                    "            background-color: #4444ff;\n" +
                    "        }</style><html><head><meta charset=\"UTF-8\"></head><body><h1>Вы успешно зарегистрированны</h1>\n" +
                    "    <button onclick=\"history.back()\">Назад</button></body></html>");
        } else {
            out.println("<style>body {\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "            justify-content: center;\n" +
                    "            align-content: center;\n" +
                    "            align-items: center;\n" +
                    "            font-family: sans-serif;\n" +
                    "            background: #ffffff;\n" +
                    "        }" +
                    "button {\n" +
                    "            height: 40px;\n" +
                    "            width: 200px;\n" +
                    "            border: none;\n" +
                    "            background-color: blue;\n" +
                    "            color: white;\n" +
                    "            font-weight: bold;\n" +
                    "            border-radius: 12px;\n" +
                    "        }\n" +
                    "        button:hover {\n" +
                    "            cursor: pointer;\n" +
                    "            background-color: #2222ff;\n" +
                    "        }\n" +
                    "        button:active {\n" +
                    "            background-color: #4444ff;\n" +
                    "        }</style><html><head><meta charset=\"UTF-8\"></head><body><h1>Ошибка при регистрации</h1>\n" +
                    "    <button onclick=\"history.back()\">Назад</button></body></html>");
        }
    }
}
