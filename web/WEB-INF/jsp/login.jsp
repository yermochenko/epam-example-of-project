<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="login.title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <c:if test="${not empty param.message}">
        <p class="error"><fmt:message key="${param.message}"/></p>
    </c:if>
    <c:url var="urlLogin" value="/login.html"/>
    <form action="${urlLogin}" method="post">
        <label for="login"><fmt:message key="login.form.login"/>:</label>
        <input id="login" name="login">
        <label for="password"><fmt:message key="login.form.password"/>:</label>
        <input id="password" name="password" type="password">
        <button class="login"><fmt:message key="login.button.login"/></button>
    </form>
</u:html>