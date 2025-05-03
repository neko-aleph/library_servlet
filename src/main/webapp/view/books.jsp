<%@ page import="org.example.java_servlet.app.entities.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Библиотека</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-content: center;
            align-items: center;
            font-family: sans-serif;
        }
        input {
            height: 40px;
            width: 200px;
            border: none;
            outline: none;
            background-color: #d9d9d9;
            padding-left: 12px;
            box-sizing: border-box;
            border-radius: 8px;
        }
        button {
            height: 40px;
            width: 200px;
            border: none;
            background-color: blue;
            color: white;
            font-weight: bold;
            border-radius: 12px;
        }
        button:hover {
            cursor: pointer;
            background-color: #2222ff;
        }
        button:active {
            background-color: #4444ff;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
            justify-content: center;
            align-content: center;
            align-items: center;
        }
        #content {
            display: flex;
            flex-direction: row;
            gap: 50px;
        }
        #back {
            background-color: #333333;
        }
        #back:hover {
            background-color: #555555;
        }
        #back:active {
            background-color: #777777;
        }
        table, th, td {
            border: 1px dotted #d9d9d9;
        }
        table {
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
            border-collapse: separate;
            border-spacing: 0;
        }
        th {
            background-color: #d9d9d9;
        }
        th, td {
            min-width: 100px;
        }
        #tl {
            border-top-left-radius: 8px;
        }
        #tr {
            border-top-right-radius: 8px;
        }
        #header {
            margin-top: 50px;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            align-content: center;
            gap: 400px;
        }
    </style>
</head>
<body>
    <div id="header">
        <h1>Список книг</h1>
        <button onclick="history.back()" id="back">Назад</button>
    </div>
    <div id="content">
    <div id="first">
    <h2>Список</h2>
    <%
        ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("booksList");
        if (books != null) {
    %>
    <table>
        <tr>
            <th id="tl">id</th>
            <th>название</th>
            <th id="tr">автор</th>
        </tr>
    <%
            for (Book book : books) {
    %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
        </tr>
    <%
        }
    %>
    </table>
    <%
    } else {
    %>
    <p>Книг нет.</p>
    <% } %>
    </div>

    <div id="second">
    <h2>Добавить книгу</h2>
    <form action="book" method="post">
        <input type="text" id="title" name="title" placeholder="Название" required>
        <input type="text" id="author" name="author" placeholder="Автор" required>
        <button type="submit">Добавить</button>
    </form>
    </div>

    <div id="third">
    </form>
    <h2>Выдача и возврат книг</h2>
     <form id="bookForm" method="post">
       <input type="text" id="name" name="name" placeholder="Имя" required>
       <input type="password" id="password" name="password"  placeholder="Пароль" required>
       <input type="number" id="bookId" name="bookId"  placeholder="ID книги" required>

       <!-- Скрытое поле для определения действия -->
       <input type="hidden" id="action" name="action" value="borrow">

       <button type="submit" onclick="setAction('borrow')">Выдать книгу</button>
       <button type="submit" onclick="setAction('return')">Вернуть книгу</button>
     </form>
     <script>
        function setAction(action) {
            form = document.getElementById("bookForm")
            form.action = action;
        }
      </script>
    </div>

    </div>
</body>
</html>
