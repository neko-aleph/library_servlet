<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
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
            h2 {
                margin-top: 100px;
            }
  </style>
  <script>
    function setAction(actionUrl) {
      const form = document.getElementById("auth-form");
      form.action = actionUrl;
      form.submit();
    }
  </script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
<h2>Вход/регистрация</h2>
<form action="user" method="post" id="auth-form">
  <input type="text" id="name-reg" name="name" placeholder="Имя" required>
  <input type="password" id="password-reg" name="password" placeholder="Пароль" required>
  <button type="button" onclick="setAction('user')">Регистрация</button>
  <button type="button" onclick="setAction('login')">Войти</button>
</body>
</html>