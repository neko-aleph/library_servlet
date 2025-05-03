package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.java_servlet.app.entities.Book;
import org.example.java_servlet.app.entities.User;
import org.example.java_servlet.app.models.BookDataBase;
import org.example.java_servlet.app.models.BookDataBaseSQLite;
import org.example.java_servlet.app.models.UserDataBase;
import org.example.java_servlet.app.models.UserDataBaseSQLite;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "borrowServlet", value = "/borrow")
public class BorrowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int userId = (session != null) ? (Integer) session.getAttribute("id") : -1;

        if (userId != -1) {
            BookDataBaseSQLite model = BookDataBaseSQLite.getInstance();
            int bookId = Integer.parseInt(req.getParameter("bookId"));

            boolean success = model.setBookBorrowed(bookId, userId);
            if (success) {
                resp.getWriter().println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Книга успешно выдана</h1>\n" +
                        "    <button onclick=\"location.href='book'\">Назад</button></body></html>");
            } else {
                resp.getWriter().println("<html><head><meta charset=\"UTF-8\"></head><body><h1>Ошибка</h1>\n" +
                        "    <button onclick=\"location.href='book'\">Назад</button></body></html>");
            }
        } else {
            resp.getWriter().println("<h1>Нет доступа</h1><button onclick=\"history.back()\">Назад</button>");
        }
    }
}
