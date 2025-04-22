package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        UserDataBaseSQLite users = UserDataBaseSQLite.getInstance();
        BookDataBaseSQLite books = BookDataBaseSQLite.getInstance();

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        int bookId = Integer.parseInt(req.getParameter("bookId"));

        User user = users.CheckPassword(name, password);

        PrintWriter out = resp.getWriter();
        if (user == null) {
            out.println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Неверное имя или пароль</h1>\n" +
                    "    <button onclick=\"history.back()\">Назад</button></body></html>");
        } else {
            books.setBookUnborrowed(bookId, user.getId());
            out.println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Книга успешно возвращена</h1>\n" +
                    "    <button onclick=\"history.back()\">Назад</button></body></html>");
        }
    }
}
