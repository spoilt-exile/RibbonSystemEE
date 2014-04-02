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
        <link REL="stylesheet" type="text/css" href="/main.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${acc_mode gt 0}">
                <div align="center"><h1>Повідомлення з напрямку ${param.dirname}</h1></div>
                <c:choose>
                    <c:when test="${acc_mode eq 1}">
                        <font color="green">
                            Ви можете читати повідомлення.<br/>
                        </font>
                        <font color="red">
                            Ви не можете випускати повідомлення.<br/>
                        </font>
                    </c:when>
                    <c:when test="${acc_mode eq 2}">
                        <font color="green">
                            Ви можете читати повідомлення.<br/>
                            Ви можете випускати повідомлення.<br/>
                            Ви можете редагувати/видаляти власні повідомлення.<br/>
                        </font>
                        <font color="red">
                            Ви не можете редагувати/видаляти повідомлення інших.<br/>
                        </font>
                    </c:when>
                    <c:when test="${acc_mode eq 3}">
                        <font color="green">
                            Ви можете читати повідомлення.<br/>
                            Ви можете випускати повідомлення.<br/>
                            Ви можете редагувати/видаляти будь-які повідомлення.<br/>
                        </font>
                    </c:when>
                </c:choose>
                <br/>
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
                            <td>
                                [Перевипустити]
                                <c:choose>
                                    <c:when test="${author.login == sessionScope.username}">
                                        [Редагувати]
                                        [Видалити]
                                    </c:when>
                                    <c:when test="${acc_mode eq 3}">
                                        [Редагувати]
                                        [Видалити]
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:when test="${acc_mode eq 0}">
                <div align="center">
                    <font color="red">
                        Цей напрямок обемжено для перегляду. Якщо вам потрібно отримувати інформацію з цього напрямку, зв’яжіться з черговим адміністратором.
                    </font>
                </div>
            </c:when>
            <c:when test="${acc_mode eq null}">
                <div align="center"><h1>Ласкаво просимо до системи!</h1></div>
                <br/>
                Щоб розпочати роботу з системою оберіть напрямок з лівої колонки. 
                Ви отримаєте список повідомлень на напрямку і можливі дії для них 
                (перевипуск, редагування і видалення). Для можливості виконання усіх дій над 
                повідомленнями ви повинні мати відповідні привілеї у системі. За замовчуванням ви зможете читати 
                повідомлення у системі на усіх напрямках.<br/>
                <br/>
                Перебуваючи на напрямку ви можете додати повідомлення на нього. Для цього натисніть 
                на посилання НОВЕ ПОВІДОМЛЕННЯ. Випустивши повідомлення ви або адміністратор напрямку 
                зможе редагувати чи видалити його. Якщо ви є адміністратором напрямку, то ви можете редагувати 
                чи видаляти усі повідомлення на напрямку.<br/>
                <br/>
                Стосовно привілеїв у системи, створення напрямків і створення схем експорту продукції звертайтесь до 
                чергового адміністратора.
            </c:when>
        </c:choose>
    </body>
</html>
