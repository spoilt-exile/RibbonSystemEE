<%-- 
    Document   : userinfo
    Created on : 27 бер 2014, 12:13:23
    Author     : spoilt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Інформація користувача ${sessionScope["username"]}</title>
        <link REL="stylesheet" type="text/css" href="/main.css">
    </head>
    <body>
        <div class="info-frame">
            <b>Ім’я користувача:</b> ${user.login} <br/>
            <b>Опис користувача:</b> ${user.description} <br/>
            <b>Дата створення:</b> ${user.formatCrtDate} <br/>
            <b>Дата логіну</b> ${user.formatLogDate} <br/>
            <br/>
            <b>Групи:</b><br/>
            <c:forEach var="group" items="${groupList}">
                <b>${group.name} <i>(${group.description})</i></b><br/>
            </c:forEach>
        </div>
        <br/>
        <div align="center">
            [<a href="#" onclick="window.close();">ЗАКРИТИ</a>]
        </div>
    </body>
</html>
