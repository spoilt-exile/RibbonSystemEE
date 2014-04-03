<%-- 
    Document   : header
    Created on : 9 бер 2014, 20:45:54
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Заголовок</title>
        <link REL="stylesheet" type="text/css" href="/header.css">
    </head>
    <body>
        <table width="100%">
            <tr>
                <td align="left">
                    <img src="/images/logo.png">
                </td>
                <td align="right" valign="bottom">
                    ${sessionScope["username"]} 
                    <c:if test="${sessionScope.isAdmin == 'true'}">
                        [КЕРУВАННЯ] 
                    </c:if>
                    [<a href="#" onClick="window.open('/Ribbon?command=USER_INFO', '_blank', 'Toolbar=0, Scrollbars=1, Resizable=0, Width=640, resize=no, Height=480');">ІНФО</a>] 
                    [<a href="/Ribbon?command=LOGOUT" target="_top">ВИЙТИ</a>]
                </td>
            </tr>
        </table>
    </body>
</html>
