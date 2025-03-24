<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="book">Список книг</a>
<h2>Регистрация</h2>
<form action="user" method="post">
  <label for="name-reg">Имя:</label>
  <input type="text" id="name-reg" name="name" required>
  <br>
  <label for="password-reg">Пароль:</label>
  <input type="password" id="password-reg" name="password" required>
  <br>
  <button type="submit">Регистрация</button>
  <h1>Выдача и возврат книг</h1>
  <form id="bookForm" method="post">
    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label for="bookId">ID книги:</label>
    <input type="number" id="bookId" name="bookId" required>
    <br>

    <!-- Скрытое поле для определения действия -->
    <input type="hidden" id="action" name="action" value="borrow">

    <button type="submit" onclick="setAction('borrow')">Выдать книгу</button>
    <button type="submit" onclick="setAction('return')">Вернуть книгу</button>
  </form>
</form>
</body>
</html>