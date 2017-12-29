<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<u:html title="Стартовая страница">
    <h2>Стартовая страница</h2>
    <c:url var="accountList" value="/account/list.html"/>
    <p><a href="${accountList}">Список клиентов</a></p>
    <c:url var="userList" value="/user/list.html"/>
    <p><a href="${userList}">Список пользователей</a></p>
</u:html>