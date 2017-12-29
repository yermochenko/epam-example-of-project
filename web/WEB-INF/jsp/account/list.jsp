<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="account.list.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="account.list.table.client"/></th>
            <th><fmt:message key="account.list.table.balance"/></th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td class="content">${account.client}</td>
                <td class="content">${account.balance}</td>
                <td class="empty">
                    <c:url var="urlAccountView" value="/account/view.html">
                        <c:param name="id" value="${account.id}"/>
                    </c:url>
                    <a href="${urlAccountView}" class="view"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="urlAccountEdit" value="/account/edit.html"/>
    <a href="${urlAccountEdit}" class="add-button"><fmt:message key="account.list.button.add"/></a>
</u:html>