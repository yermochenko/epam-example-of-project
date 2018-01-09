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
    <c:if test="${currentUser.role == 'MANAGER'}">
        <a href="${urlAccountEdit}" class="edit-button"><fmt:message key="account.view.button.edit"/></a>
        <c:url var="urlAccountList" value="/account/list.html"/>
    </c:if>
    <a href="${urlAccountList}" class="back"><fmt:message key="account.view.button.cancel"/></a>
    <h3><fmt:message key="account.view.subtitle"/></h3>
    <table>
        <tr>
            <th colspan="2"><fmt:message key="account.view.history.table.source"/></th>
            <th colspan="2"><fmt:message key="account.view.history.table.destination"/></th>
            <th rowspan="2"><fmt:message key="account.view.history.table.amount"/></th>
            <th rowspan="2"><fmt:message key="account.view.history.table.date"/></th>
            <th rowspan="2"><fmt:message key="account.view.history.table.operator"/></th>
        </tr>
        <tr>
            <th><fmt:message key="account.view.history.table.account.id"/></th>
            <th><fmt:message key="account.view.history.table.account.client"/></th>
            <th><fmt:message key="account.view.history.table.account.id"/></th>
            <th><fmt:message key="account.view.history.table.account.client"/></th>
        </tr>
        <c:forEach var="transfer" items="${account.history}">
            <tr>
                <c:choose>
                    <c:when test="${not empty transfer.source}">
                        <td class="content">${transfer.source.id}</td>
                        <td class="content">${transfer.source.client}</td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="2" class="content"><fmt:message key="account.view.history.table.source.empty"/></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty transfer.destination}">
                        <td class="content">${transfer.destination.id}</td>
                        <td class="content">${transfer.destination.client}</td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="2" class="content"><fmt:message key="account.view.history.table.destination.empty"/></td>
                    </c:otherwise>
                </c:choose>
                <td class="content">${transfer.amount}</td>
                <td class="content"><fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${transfer.date}"/></td>
                <td class="content">${transfer.operator.login}</td>
            </tr>
        </c:forEach>
    </table>
</u:html>