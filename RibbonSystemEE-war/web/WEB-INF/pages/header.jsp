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
                    <a href="/Ribbon?command=USER_INFO" target="_blank">${sessionScope["username"]}</a> <a href="/Ribbon?command=LOGOUT" target="_top">ВИЙТИ</a>
                </td>
            </tr>
        </table>
    </body>
</html>
