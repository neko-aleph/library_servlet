package org.example.java_servlet.app.models;

import org.example.java_servlet.app.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDataBase {
    private static BookDataBase instance;
    private List<Book> books;
    private static int nextId;

    private BookDataBase() {
        books = new ArrayList<>();
        nextId = 1;
        books.add(new Book(getNextId(), "Война и мир", "Л. Н. Толстой"));
        books.add(new Book(getNextId(), "Капитанская дочка", "А. С. Пушкин"));
    }

    public static BookDataBase getInstance() {
        if (instance == null) {
            instance = new BookDataBase();
        }
        return instance;
    }

    public static int getNextId() {
        return nextId++;
    }

    public int addBook(Book book) {
        books.add(book);
        return book.getId();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book setBookBorrowed(int id, int borrowerId) {
        for (Book book : books) {
            if (book.getId() == id && !book.isBorrowed()) {
                book.setBorrowed(borrowerId);
                return book;
            }
        }

        return null;
    }

    public Book setBookUnborrowed(int id, int borrowerId) {
        for (Book book : books) {
            if (book.getId() == id && book.isBorrowed() && book.getBorrowerId() == borrowerId) {
                book.setUnborrowed();
                return book;
            }
        }

        return null;
    }

    public boolean deleteBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}
