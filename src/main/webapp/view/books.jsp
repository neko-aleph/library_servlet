<%@ page import="org.example.java_servlet.app.entities.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Библиотека</title>
</head>
<body>
    <h1>Список книг</h1>
    <%
        ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("booksList");
        if (books != null) {
            for (Book book : books) {
    %>
    <p>id: <%= book.getId() %>, название: <%= book.getTitle() %>, автор: <%= book.getAuthor() %></p>
    <%
        }
    } else {
    %>
    <p>Книг нет.</p>
    <% } %>

    <h2>Добавить книгу</h2>
    <form action="book" method="post">
        <label for="title">Название:</label>
        <input type="text" id="title" name="title" required>
        <br>
        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" required>
        <br>
        <button type="submit">Добавить</button>
    </form>
</body>
</html>
