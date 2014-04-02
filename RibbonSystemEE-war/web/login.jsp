<%-- 
    Document   : login
    Created on : 6 бер 2014, 20:34:57
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ВХІД ДО СИСТЕМИ</title>
        <link REL="stylesheet" type="text/css" href="/main.css">
    </head>
    <body>
        <br/><br/><br/><br/>
        <div align="center">
        <div class="login-frame">
            <img src="/images/logo.png" />
        <form action="/Ribbon" method="POST" name="user-add">
            <input type="hidden" name="command" value="LOGIN">
            <b>ЛОГІН:</b><br/>
            <input type="text" name="login" maxlength="125" required> <br/>
            <b>ПАРОЛЬ:</b><br/>
            <input type="password" name="passw" required> <br/>
            <input type="submit" value="Увійти"> 
            <input type="reset" value="Скасувати">
        </form>
        </div>
        <% if (response.getHeader("login_error") != null) { %>
            <span class="ribbon_error_header"><%= response.getHeader("login_error") %></span><br/>
        <% } %>
        </div>
    </body>
</html>
