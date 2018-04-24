<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>stationPage</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Stations</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="station">
        <tr>
            <td>${station.nameStation}</td>
            <td>
                <form name="ShowAdminStationForm" method="POST" action="${pageContext.request.contextPath}/Controller">
                        <input type="hidden" name="command" value="show_admin_station" />
                        <input type="hidden" name="id" value="${station.id}">
                        <input type="submit" value="edit station" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
