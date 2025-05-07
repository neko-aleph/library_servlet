package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.java_servlet.app.entities.User;
import org.example.java_servlet.app.models.BookDataBase;
import org.example.java_servlet.app.models.BookDataBaseSQLite;
import org.example.java_servlet.app.models.UserDataBase;
import org.example.java_servlet.app.models.UserDataBaseSQLite;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "returnServlet", value = "/return")
public class ReturnServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int userId = (session != null) ? (Integer) session.getAttribute("id") : -1;

        if (userId != -1) {
            BookDataBaseSQLite model = BookDataBaseSQLite.getInstance();
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            boolean success = model.setBookUnborrowed(bookId, userId);
            if (success) {
                resp.getWriter().println("<style>body {\n" +
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
                        "        }</style><html><head><meta charset=\"UTF-8\"></head><body><h1>Книга успешно возвращена</h1>\n" +
                        "    <button onclick=\"location.href='book'\">Назад</button></body></html>");
            } else {
                resp.getWriter().println("<style>body {\n" +
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
                        "        }</style><html><head><meta charset=\"UTF-8\"></head><body><h1>Ошибка</h1>\n" +
                        "    <button onclick=\"location.href='book'\">Назад</button></body></html>");
            }
        } else {
            resp.getWriter().println("<style>body {\n" +
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
                    "        }</style><h1>Нет доступа</h1><button onclick=\"history.back()\">Назад</button>");
        }
    }
}
