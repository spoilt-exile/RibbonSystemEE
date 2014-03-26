<%-- 
    Document   : viewmesg
    Created on : 12 бер 2014, 17:39:17
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="/Ribbon?command=LIST_MESG&dirid=${sessionScope["last_dir"]}&dirname=${sessionScope["last_dir_name"]}" target="MSG">НАЗАД</a><br/>
        <b>ЗАГОЛОВОК:</b> ${mesg.header} <br/>
        <b>АВТОР:</b> ${auth.login} (${auth.description}) <br/>
        <b>НАПРЯМОК:</b> ${sessionScope["last_dir_name"]} <br/>
        <b>ДАТА ВИПУСКУ:</b> ${mesg.postDate} <br/>
        <c:if test="${mesg.isUrgent}">
            <b><font color="red">ТЕРМІНОВО!</font></b><br/>
        </c:if>
        <br/>
        ${mesg.text}
    </body>
</html>
