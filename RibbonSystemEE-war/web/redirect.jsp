<%-- 
    Document   : redirect
    Created on : 1 квіт 2014, 11:51:51
    Author     : spoilt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Перенаправлення</title>
        <link REL="stylesheet" type="text/css" href="/main.css">
        <script type="text/javascript">
            setTimeout(function(){window.location.href = "/index.jsp"}, 3000);
        </script>
    </head>
    <body>
        <div align="center">
            <br/>
            <h1>ПЕРЕНАПРАВЛЕННЯ...</h1>
            <br/>
            Якщо автоматичне перенаправлення не працює, натисніть на <a href="/index.jsp">посилання</a>
        </div>
    </body>
</html>
