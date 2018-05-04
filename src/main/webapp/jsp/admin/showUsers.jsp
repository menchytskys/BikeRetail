<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="page_content">
    <fmt:message key="users.name" var="name"/>
    <fmt:message key="users.last_name" var="lastname"/>
    <fmt:message key="users.balance" var="balance"/>
    <fmt:message key="users.user_status" var="status"/>
    <fmt:message key="users.blocked" var="blocked"/>
    <fmt:message key="users.active" var="active"/>
    <fmt:message key="users.change_status" var="status"/>
</fmt:bundle>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere|Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin-style.css">
    <title>BIKETRO Admin Page</title>
</head>

<body>
<tag:adminMenu/>
<div class="container-center">
<table>
    <thead>
    <tr>
        <th>${name}</th>
        <th>${lastname}</th>
        <th>${balance}</th>
        <th>${status}</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.balance}</td>
            <td>
                <c:choose>
                    <c:when test="${user.activeStatus == 1}">
                        ${blocked}
                    </c:when>
                    <c:when test="${user.activeStatus == 0}">
                        ${active}
                    </c:when>
                </c:choose>
            </td>
            <td>
                <a class="button" href="${pageContext.request.contextPath}/Controller?command=change_user_status&userId=${user.id}">${status}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
