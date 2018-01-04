<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="account.view.title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="account.view.table.id"/></th>
            <td class="content">${account.id}</td>
        </tr>
        <tr>
            <th><fmt:message key="account.view.table.client"/></th>
            <td class="content">${account.client}</td>
        </tr>
        <tr>
            <th><fmt:message key="account.view.table.balance"/></th>
            <td class="content">${account.balance}</td>
        </tr>
    </table>
    <c:url var="urlAccountEdit" value="/account/edit.html">
        <c:param name="id" value="${account.id}"/>
    </c:url>
    <a href="${urlAccountEdit}" class="edit-button"><fmt:message key="account.view.button.edit"/></a>
</u:html>