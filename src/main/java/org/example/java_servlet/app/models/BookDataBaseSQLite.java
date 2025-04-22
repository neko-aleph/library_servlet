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
                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("isBorrowed"), rs.getInt("borrowerId"));
                books.add(book);
            }
            return books;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean addBook(Book book){
        String sql = "INSERT INTO book (title, author, isBorrowed, borrowerId) Values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBoolean(3, book.isBorrowed());
            if (book.isBorrowed()) {
                preparedStatement.setInt(4, book.getBorrowerId());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean setBookBorrowed(int id, int borrowerId) {
        String sql = "UPDATE book SET isBorrowed = true , borrowerId = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borrowerId);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean setBookUnborrowed(int id, int borrowerId) {
        String sql = "UPDATE book SET isBorrowed = false , borrowerId = null WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteBook(int id) {
        String sql = "DELETE book WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
