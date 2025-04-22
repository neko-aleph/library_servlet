package org.example.java_servlet.app.models;

import org.example.java_servlet.app.entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDataBaseSQLite {
    private static BookDataBaseSQLite instance;
    private static Connection connection;

    private BookDataBaseSQLite() {
        connection = BD.connect();
    }

    public static BookDataBaseSQLite getInstance() {
        if (instance == null) {
            instance = new BookDataBaseSQLite();
        }
        return instance;
    }

    public List<Book> getAllBooks(){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from book");
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"));
                books.add(book);
            }
            return books;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int addBook(Book book){
        String sql = "INSERT INTO book (title, author) Values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());

            return  preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
