<%@tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <c:url var="urlCss" value="/main.css"/>
        <link href="${urlCss}" rel="stylesheet">
    </head>
    <body>
        <h1><fmt:message key="application.title"/></h1>
        <c:if test="${not empty currentUser}">
            <c:url var="urlLogout" value="/logout.html"/>
            <c:url var="urlPasswordEdit" value="/password/edit.html"/>
            <p>
                <fmt:message key="application.welcome"/> ${currentUser.login}
                (<fmt:message key="${currentUser.role.name}"/>).
                <a href="${urlLogout}"><fmt:message key="application.button.logout"/></a>.
                <a href="${urlPasswordEdit}"><fmt:message key="application.button.password.change"/></a>.
            </p>
        </c:if>
        <jsp:doBody/>
    </body>
</html>