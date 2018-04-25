<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere|Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin-style.css">
    <title>BIKETRO ShowUsers Page</title>
</head>
<body>
<h1>Admin Page</h1>
<hr/>
<nav>
    <ul>
        <li>Stations
            <ul>
                <li><a href="">Red</a></li>
                <li><a href="">Green</a></li>
                <li><a href="">Blue</a></li>
                <li><a href="">Black</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/Controller?command=show_all_users">Users</a></li>
        <li><a href="">Bikes</a></li>
    </ul>
    <button><a href="Controller?command=logout">log out</a></button>
</nav>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Last Name</th>
        <th>Balance</th>
        <th>User status</th>
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
                        Blocked
                    </c:when>
                    <c:when test="${user.activeStatus == 0}">
                        Active
                    </c:when>
                </c:choose>
            </td>
            <td>
                <button><a href="${pageContext.request.contextPath}/Controller?command=change_user_status&userId=${user.id}">Change status</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp">back</a>
</body>
</html>
