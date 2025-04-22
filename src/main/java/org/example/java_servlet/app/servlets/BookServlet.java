package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.java_servlet.app.entities.Book;
import org.example.java_servlet.app.models.BookDataBase;
import org.example.java_servlet.app.models.BookDataBaseSQLite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "bookServlet", value = "/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDataBaseSQLite model = BookDataBaseSQLite.getInstance();
        List<Book> books = model.getAllBooks();
        req.setAttribute("booksList", books);
        req.getRequestDispatcher("view/books.jsp").forward(req, resp);
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
