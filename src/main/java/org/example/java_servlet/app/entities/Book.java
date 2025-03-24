package org.example.java_servlet.app.entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;
    private Integer borrowerId;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrowerId = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(int borrowerId) {
        isBorrowed = true;
        this.borrowerId = borrowerId;
    }

    public void setUnborrowed() {
        isBorrowed = false;
        this.borrowerId = null;
    }

    public int getBorrowerId() {
        return borrowerId;
    }
}
