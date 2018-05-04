<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="menu.station" var="Station"/>
    <fmt:message key="user.hello" var="hello"/>
    <fmt:message key="user.balance" var="balance"/>
    <fmt:message key="user.log_out" var="logout"/>
    <fmt:message key="user.to_stations" var="toStations"/>
    <fmt:message key="user.take" var="take"/>
    <fmt:message key="user.return" var="Return"/>
    <fmt:message key="user.choose" var="choose"/>
    <fmt:message key="menu.bike_brand" var="brand"/>
    <fmt:message key="menu.bike_model" var="model"/>
    <fmt:message key="menu.price_on_hour" var="price"/>
    <fmt:message key="user.rent_time" var="time"/>
    <fmt:message key="user.hour" var="hour"/>
    <fmt:message key="user.hours" var="hours"/>
</fmt:bundle>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere|Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<div class="container-center">
    <h1>${station.nameStation} ${Station}</h1>
    <hr>
    <h3>${hello} ${user.name}!</h3>
    <h3>${balance} ${user.balance}</h3>
    <a class="button" href="${pageContext.request.contextPath}/Controller?command=common_logout">${logout}</a>
    <a class="button" href="${pageContext.request.contextPath}/jsp/user/user.jsp">${toStations}</a>
    <hr/>

    <c:choose>
        <c:when test="${user.rentStatus == 1}">
            <form name="ReturnBikeForm" method="POST" action="${pageContext.request.contextPath}/Controller">
                <input type="hidden" name="command" value="user_return_bike"/>
                <input type="hidden" name="userId" value="${user.id}">
                <input type="hidden" name="stationId" value="${station.id}">
                <input type="submit" value=${Return}/>
            </form>
        </c:when>
        <c:when test="${user.rentStatus == 0}">
            <h2 style="padding: 15px 0px;">${choose}</h2><br>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>${brand}</th>
                        <th>${model}</th>
                        <th>${price}</th>
                        <th>${time}</th>
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
                                    <input type="hidden" name="command" value="user_take_bike"/>
                                    <input type="hidden" name="bikeId" value="${bike.id}">
                                    <select name="rentTime">
                                        <option value="1">1 ${hour}</option>
                                        <option value="2">2 ${hours}</option>
                                        <option value="3">3 ${hours}</option>
                                        <option value="4">4 ${hours}</option>
                                    </select>
                                    <input type="submit" value=${take}/>
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
