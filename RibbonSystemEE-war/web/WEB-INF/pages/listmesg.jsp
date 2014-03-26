<%-- 
    Document   : listmesg
    Created on : 9 бер 2014, 23:02:18
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ПОВІДОМЛЕННЯ</title>
    </head>
    <body>
        <div align="center"><h1>Повідомлення з напрямку ${param.dirname}</h1></div>
        <table width="100%" border="1px">
            <tr>
                <td>Заголовок</td>
                <td>Автор</td>
                <td>Дата випуску</td>
                <td>Дії</td>
            </tr>
            <c:forEach var="message" items="${mlist}">
                <tr>
                    <c:choose>
                        <c:when test="${message.isUrgent}">
                            <td><a href="Ribbon?command=VIEW_MESG&id=${message.id}" target="MSG"><font color="red">${message.header}</font></a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="Ribbon?command=VIEW_MESG&id=${message.id}" target="MSG">${message.header}</a></td>
                        </c:otherwise>
                    </c:choose>
                    <c:set var="author" value="${message.authId}">
                    </c:set>
                    <td>${author.login}</td>
                    <td>${message.postDate}</td>
                    <td>[РЕДАГУВАТИ] [ВИДАЛИТИ]</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
