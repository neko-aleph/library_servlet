package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.java_servlet.app.entities.Book;
import org.example.java_servlet.app.models.BookDataBase;
import org.example.java_servlet.app.models.BookDataBaseSQLite;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "bookServlet", value = "/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int userId = (session != null) ? (Integer) session.getAttribute("id") : -1;
        boolean admin = (session != null) ? (Boolean) session.getAttribute("admin") : false;

        if (userId != -1) {
            BookDataBaseSQLite model = BookDataBaseSQLite.getInstance();
            List<Book> books = model.getAllBooks();
            List<Book> userBooks = model.getUserBooks(userId);
            req.setAttribute("booksList", books);
            req.setAttribute("userBooksList", userBooks);
            req.setAttribute("admin", admin);
            req.getRequestDispatcher("view/books.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<h1>Нет доступа</h1><button onclick=\"history.back()\">Назад</button>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDataBaseSQLite model = BookDataBaseSQLite.getInstance();

        String title = req.getParameter("title");
        String author = req.getParameter("author");

        Book book = new Book(0, title, author);
        model.addBook(book);

        resp.sendRedirect("book");
    }
}
