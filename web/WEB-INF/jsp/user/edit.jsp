<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty user}">
    <jsp:useBean id="user" class="domain.User"/>
</c:if>
<c:choose>
    <c:when test="${not empty user.id}">
        <c:set var="title" value="Редактирование данных пользователя"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового пользователя"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <c:url var="urlCss" value="/main.css"/>
        <link href="${urlCss}" rel="stylesheet">
    </head>
    <body>
        <h1>Банк «Рога и копыта»</h1>
        <h2>${title}</h2>
        <c:url var="urlUserList" value="/user/list.html"/>
        <c:url var="urlUserSave" value="/user/save.html"/>
        <form action="${urlUserSave}" method="post">
            <c:if test="${not empty user.id}">
                <input name="id" value="${user.id}" type="hidden">
            </c:if>
            <label for="login">Имя пользователя:</label>
            <input id="login" name="login" value="${user.login}">
            <label for="role">Роль:</label>
            <select id="role" name="role">
                <c:forEach var="role" items="${roles}">
                    <c:choose>
                        <c:when test="${role.id == user.role.id}">
                            <c:set var="selected" value="selected"/>
                        </c:when>
                        <c:otherwise>
                            <c:remove var="selected"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${role.id}" ${selected}>${role.name}</option>
                </c:forEach>
            </select>
            <button class="save">Сохранить</button>
            <button class="reset" type="reset">Сброс</button>
            <button class="back" formaction="${urlUserList}" formmethod="get">Отмена</button>
        </form>
    </body>
</html>