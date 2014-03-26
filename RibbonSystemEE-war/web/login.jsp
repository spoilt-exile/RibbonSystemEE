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
    </head>
    <body>
        <% if (response.getHeader("login_error") != null) { %>
            <font color="red"><%= response.getHeader("login_error") %></font><br/>
        <% } %>
        <div align="center">
        <form action="/Ribbon" method="POST" name="user-add"> <br/>
            <input type="hidden" name="command" value="LOGIN">
            <b>ЛОГІН:</b> <input type="text" name="login" maxlength="125"> <br/>
            <b>ПАРОЛЬ:</b> <input type="password" name="passw"> <br/>
            <input type="submit" value="Увійти до системи">
        </form>
        </div>
    </body>
</html>
