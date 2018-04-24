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
</head>
<body>
<div class="container-center">
    <h1>${station.nameStation} Station</h1>
    <hr>
    <h3>Hello, ${user.name}!</h3>
    <h3>Your balance: ${user.balance}</h3>
    <button><a href="Controller?command=logout">log out</a></button>
    <button><a href="/jsp/user/user.jsp">to stations</a></button>
    <hr/>

    <c:choose>
        <c:when test="${user.rentStatus == 1}">
            <form>
                <input type="hidden" name="command" value="return_bike"/>
                <input type="hidden" name="userId" value="${user.id}">
                <input type="hidden" name="stationId" value="${station.id}">
                <input type="submit"  value="return bike"/>
            </form>
        </c:when>
        <c:when test="${user.rentStatus == 0}">
            <h2 style="padding: 15px 0px;">CHOOSE YOUR BIKE FOR TODAY ;)</h2><br>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>bike brand</th>
                        <th>bike model</th>
                        <th>price per hour</th>
                        <th>rent time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bikes}" var="bike">
                        <tr>
                            <td>${bike.brand}</td>
                            <td>${bike.model}</td>
                            <td>${bike.priceOnHour}</td>
                            <td>
                                <form name="TakeBikeForm" method="POST"
                                      action="${pageContext.request.contextPath}/Controller">
                                    <input type="hidden" name="command" value="take_bike"/>
                                    <input type="hidden" name="bikeId" value="${bike.id}">
                                    <select name="rentTime">
                                        <option value="1">1 hour</option>
                                        <option value="2">2 hours</option>
                                        <option value="3">3 hours</option>
                                        <option value="4">4 hours</option>
                                    </select>
                                    <input type="submit" value="take bike"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:when>
    </c:choose>
</div>
</body>
</html>
