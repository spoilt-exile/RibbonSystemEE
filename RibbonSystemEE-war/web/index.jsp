<%-- 
    Document   : index
    Created on : 12 лют 2014, 12:16:50
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Система обробки повідомлень "СТРІЧКА"</title>
    </head>
    <body>
        <% if (request.getSession().getAttribute("username") != null) { %>
            <% response.sendRedirect("/Ribbon?command=MAIN"); %>
        <% } else { %>
            <% response.sendRedirect("/login.jsp"); %>
        <% } %>
    </body>
</html>
