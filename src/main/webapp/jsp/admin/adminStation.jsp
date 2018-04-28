<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container-center">
    <h1>Admin Page</h1>
    <nav>
        <ul>
            <li class='active'><a href='${pageContext.request.contextPath}/jsp/admin/admin.jsp'>Stations</a>
                <ul class='sub-menu'>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=1">Red</a></li>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=2">Green</a></li>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=3">Blue</a></li>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=4">Black</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/Controller?command=show_all_users">Users</a></li>
            <li><a href="${pageContext.request.contextPath}/Controller?command=show_all_bikes">Bikes</a></li>
            <li><a href="${pageContext.request.contextPath}/Controller?command=logout">log out</a></li>
        </ul>
    </nav>
</div>
<h1>${station.nameStation}</h1>
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
