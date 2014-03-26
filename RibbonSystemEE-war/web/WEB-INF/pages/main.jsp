<%-- 
    Document   : main
    Created on : 5 бер 2014, 19:59:03
    Author     : Stanislav Nepochatov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ГОЛОВНА СТОРІНКА "СТРІЧКА"</title>
    </head>
    <frameset rows="95px,*" cols="*" border="2px">
        <frame src="/Ribbon?command=HEADER" noresize scrolling="no">
        <frameset rows="*" cols="20%,80%" border="2px">
            <frame src="/Ribbon?command=LIST_DIRS">
            <frame name="MSG" src="/Ribbon?command=LIST_MESG">
        </frameset>
    </frameset>
</html>
