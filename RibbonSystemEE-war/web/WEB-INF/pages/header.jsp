<%-- 
    Document   : header
    Created on : 9 бер 2014, 20:45:54
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Заголовок</title>
    </head>
    <body>
        <table width="100%">
            <tr>
                <td align="left">
                    <img src="/images/logo.png">
                </td>
                <td align="right" valign="bottom">
                    <%= request.getSession().getAttribute("username") %> <a href="/Ribbon?command=LOGOUT" target="_top">ВИЙТИ</a>
                </td>
            </tr>
        </table>
    </body>
</html>
