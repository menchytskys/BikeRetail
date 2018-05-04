<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="page_content">
    <fmt:message key="menu.bike_brand" var="brand"/>
    <fmt:message key="menu.bike_model" var="model"/>
    <fmt:message key="menu.price_on_hour" var="price"/>
    <fmt:message key="menu.add_bike" var="add"/>
    <fmt:message key="menu.station_Red" var="red"/>
    <fmt:message key="menu.station_Green" var="green"/>
    <fmt:message key="menu.station_Blue" var="blue"/>
    <fmt:message key="menu.station_Black" var="black"/>
    <fmt:message key="menu.station" var="Station"/>
    <fmt:message key="menu.delete_bike" var="delete"/>
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
    <form name="addBikeForm" method="POST" action="${pageContext.request.contextPath}/Controller">
        <input type="hidden" name="command" value="add_bike" />
        <p>${brand}</p>
        <input type="text" name="brand" value="" />
        <p> ${model}</p>
        <input type="text" name="model" value="" />
        <p> ${price} </p>
        <input type="text" name="priceOnHour" value="" />
        <br/>
        <select name=${Station}>
            <option value="1">${red}</option>
            <option value="2">${green}</option>
            <option value="3">${blue}</option>
            <option value="4">${black}</option>
        </select>
        <input type="submit" value=${add} />
    </form>

<table class='table table-hover'>
    <thead class='thead-dark'>
    <tr>
        <th>${brand}</th>
        <th>${model}</th>
        <th>${price}</th>
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
                    <input class='table-button' type="submit" value=${delete} />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
