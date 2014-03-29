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
    </head>
    <body>
        <h1>Групи:</h1><br/>
        <c:forEach var="group" items="${groupList}">
            <b>${group.name} <i>(${group.description})</i></b><br/>
        </c:forEach>
    </body>
</html>
