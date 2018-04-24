<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Station page</title>
</head>
<body>
<h3>Admin station</h3>
<hr/>
<h1>${station.nameStation} Station</h1>
<hr/>
<table>
    <thead>
    <tr>
        <th>bike brand</th>
        <th>bike model</th>
        <th>price on hour</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bikes}" var="bike">
        <tr>
            <td>${bike.brand}</td>
            <td>${bike.model}</td>
            <td>${bike.priceOnHour}</td>
            <td>
                <form name="DeleteForm" method="POST" action="${pageContext.request.contextPath}/Controller">
                    <input type="hidden" name="command" value="delete_bike" />
                    <input type="hidden" name="bikeId" value="${bike.id}" />
                    <input type="hidden" name="stationId" value="${station.id}" />
                    <input type="submit" value="delete bike" />
                </form>
            </td>
            <td>
                <form name="MoveBikeForm" method="POST" action="${pageContext.request.contextPath}/Controller">
                    <input type="hidden" name="command" value="move_bike_to_another_station" />
                    <br/>
                    <select name="station">
                        <option value="1">Station red</option>
                        <option value="2">Station green</option>
                        <option value="3">Station blue</option>
                        <option value="4">Station black</option>
                    </select>
                    <br/>
                    <input type="hidden" name="bikeId" value="${bike.id}">
                    <input type="submit" value="move to another station" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
