<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty account}"><jsp:useBean id="account" class="domain.Account"/></c:if>
<c:choose>
    <c:when test="${not empty account.id}">
        <fmt:message var="title" key="account.edit.title.edit"/>
        <c:url var="urlBack" value="/account/view.html">
            <c:param name="id" value="${account.id}"/>
        </c:url>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="account.edit.title.add"/>
        <c:url var="urlBack" value="/account/list.html"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlAccountSave" value="/account/save.html"/>
    <c:url var="urlAccountDelete" value="/account/delete.html"/>
    <form action="${urlAccountSave}" method="post">
        <c:if test="${not empty account.id}">
            <label for="id"><fmt:message key="account.edit.form.id"/>:</label>
            <input id="id" name="id" value="${account.id}" readonly>
        </c:if>
        <label for="client"><fmt:message key="account.edit.form.client"/>:</label>
        <input id="client" name="client" value="${account.client}">
        <c:if test="${not empty account.id}">
            <label for="balance"><fmt:message key="account.edit.form.balance"/>:</label>
            <input id="balance" value="${account.balance}" readonly>
        </c:if>
        <button class="save"><fmt:message key="account.edit.button.save"/></button>
        <c:if test="${not empty account.id}">
            <c:if test="${not empty account.history}"><c:set var="disabled" value="disabled"/></c:if>
            <button class="delete" formaction="${urlAccountDelete}" formmethod="post" ${disabled}><fmt:message key="account.edit.button.delete"/></button>
        </c:if>
        <button class="reset" type="reset"><fmt:message key="account.edit.button.reset"/></button>
        <a class="back" href="${urlBack}"><fmt:message key="account.edit.button.cancel"/></a>
    </form>
    <c:if test="${not empty account.id}">
        <h3><fmt:message key="account.edit.subtitle"/></h3>
        <table>
            <tr>
                <th colspan="2"><fmt:message key="account.edit.history.table.source"/></th>
                <th colspan="2"><fmt:message key="account.edit.history.table.destination"/></th>
                <th rowspan="2"><fmt:message key="account.edit.history.table.amount"/></th>
                <th rowspan="2"><fmt:message key="account.edit.history.table.date"/></th>
                <th rowspan="2"><fmt:message key="account.edit.history.table.operator"/></th>
            </tr>
            <tr>
                <th><fmt:message key="account.edit.history.table.account.id"/></th>
                <th><fmt:message key="account.edit.history.table.account.client"/></th>
                <th><fmt:message key="account.edit.history.table.account.id"/></th>
                <th><fmt:message key="account.edit.history.table.account.client"/></th>
            </tr>
            <c:forEach var="transfer" items="${account.history}">
                <tr>
                    <c:choose>
                        <c:when test="${not empty transfer.source}">
                            <td class="content">${transfer.source.id}</td>
                            <td class="content">${transfer.source.client}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="2" class="content"><fmt:message key="account.edit.history.table.source.empty"/></td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${not empty transfer.destination}">
                            <td class="content">${transfer.destination.id}</td>
                            <td class="content">${transfer.destination.client}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="2" class="content"><fmt:message key="account.edit.history.table.destination.empty"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="content">${transfer.amount}</td>
                    <td class="content"><fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${transfer.date}"/></td>
                    <td class="content">${transfer.operator.login}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>