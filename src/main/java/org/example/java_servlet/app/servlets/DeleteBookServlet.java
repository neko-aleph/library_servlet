package org.example.java_servlet.app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.java_servlet.app.entities.Book;
import org.example.java_servlet.app.models.BookDataBaseSQLite;

import java.io.IOException;

@WebServlet(name = "deleteBookServlet", value = "/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDataBaseSQLite model = BookDataBaseSQLite.getInstance();

        int id = Integer.parseInt(req.getParameter("id"));
        model.deleteBook(id);

        resp.sendRedirect("book");
    }
}
