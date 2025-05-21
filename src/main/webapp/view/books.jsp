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
            background: #ffffff;
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

        @media (max-width: 768px) {
            #header {
                margin-top: 20px;
                gap: 10px;
                flex-direction: column;
            }
            #content {
                flex-direction: column;
                gap: 10px;
            }
            th, td {
                min-width: 40px;
                font-size: 10px;
            }
            .table-button {
                width: 100px;
                height: 20px;
                border-radius: 6px;
            }
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
    <div id="header">
        <h1>Список книг</h1>
        <button onclick="location.href='logout'" id="back">Выйти</button>
    </div>
    <div id="content">
    <div id="first">
    <h2>Каталог</h2>
    <%
        ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("booksList");
        if (books != null) {
    %>
    <table>
        <tr>
            <th class="tl">id</th>
            <th>название</th>
            <th>автор</th>
            <th class="tr">действие</th>
        </tr>
    <%
            for (Book book : books) {
    %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td>
                <form action="borrow" method="post" style="margin:0;">
                    <input type="hidden" name="bookId" value="<%= book.getId() %>" />
                    <button type="submit" class="table-button">Взять</button>
                </form>
            </td>
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
        <h2>Мои книги</h2>
        <%
            ArrayList<Book> userBooks = (ArrayList<Book>) request.getAttribute("userBooksList");
            if (books != null) {
        %>
        <table>
            <tr>
                <th class="tl">id</th>
                <th>название</th>
                <th>автор</th>
                <th class="tr">действие</th>
            </tr>
            <%
                for (Book book : userBooks) {
            %>
            <tr>
                <td><%= book.getId() %></td>
                <td><%= book.getTitle() %></td>
                <td><%= book.getAuthor() %></td>
                <td>
                    <form action="return" method="post" style="margin:0;">
                        <input type="hidden" name="bookId" value="<%= book.getId() %>" />
                        <button type="submit" class="table-button">Вернуть</button>
                    </form>
                </td>
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

    <%
        boolean isAdmin = (boolean) request.getAttribute("admin");
        if (isAdmin) {
    %>
    <div id="third">
    <h2>Добавить книгу</h2>
    <form action="book" method="post">
        <input type="text" id="title" name="title" placeholder="Название" required>
        <input type="text" id="author" name="author" placeholder="Автор" required>
        <button type="submit">Добавить</button>
    </form>
    <h2>Удалить книгу</h2>
    <form action="deleteBook" method="post">
        <input type="number" name="id" placeholder="id" required>
        <button type="submit">Удалить</button>
    </form>
    <h2>Обновить книгу</h2>
    <form action="updateBook" method="post">
        <input type="number" name="id" placeholder="id" required>
        <input type="text" id="title" name="title" placeholder="Название" required>
        <input type="text" id="author" name="author" placeholder="Автор" required>
        <button type="submit">Обновить</button>
    </form>
    </div>
    <%
        }
    %>

    </div>
</body>
</html>
