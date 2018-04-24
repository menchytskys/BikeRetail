<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showUsers</title>
</head>
<body>
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
                <button><a href="/Controller?command=change_user_status&userId=${user.id}">Change status</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/jsp/admin/admin.jsp">back</a>
</body>
</html>
