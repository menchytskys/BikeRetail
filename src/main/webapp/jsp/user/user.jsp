<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="user.welcome" var="welcome"/>
    <fmt:message key="user.hello" var="hello"/>
    <fmt:message key="user.balance" var="balance"/>
    <fmt:message key="user.log_out" var="logout"/>
    <fmt:message key="user.select_station" var="select"/>
    <fmt:message key="user.to_take" var="take"/>
    <fmt:message key="user.to_return" var="Return"/>
    <fmt:message key="menu.station_Red" var="red"/>
    <fmt:message key="menu.station_Green" var="green"/>
    <fmt:message key="menu.station_Blue" var="blue"/>
    <fmt:message key="menu.station_Black" var="black"/>
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
    <title>BIKETRO User Page</title>
</head>
<body>
<div class="container-center">
    <h1>${welcome}</h1>
    <hr>
    <h3>${hello} ${user.name}!</h3>
    <h3>${balance} ${user.balance}</h3>
    <a class="button" href="${pageContext.request.contextPath}/Controller?command=common_logout">${logout}</a>
    <hr/>
    <div>
        <h3>${select}</h3>
        <c:choose>
            <c:when test="${user.rentStatus == 0}">
                <h3>${take}</h3>
            </c:when>
            <c:when test="${user.rentStatus == 1}">
                <h3>${Return}</h3>
            </c:when>
        </c:choose>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-danger">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=user_show_user_station&id=1">${red}
                            </a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-success">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=user_show_user_station&id=2">${green}
                            </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-info">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=user_show_user_station&id=3">${blue}
                            </a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-dark">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=user_show_user_station&id=4">${black}
                            </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
