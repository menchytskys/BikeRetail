<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere|Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin-style.css">
    <title>BIKETRO User Page</title>
</head>
<body>
<div class="container-center">
    <h1> Welcome to your personal account at BIKETRO</h1>
    <hr>
    <h3>Hello, ${user.name}!</h3>
    <h3>Your balance: ${user.balance}</h3>
    <button><a href="${pageContext.request.contextPath}/Controller?command=logout">log out</a></button>
    <hr/>
    <div>
        <h3>SELECT THE STATION</h3>
        <c:choose>
            <c:when test="${user.rentStatus == 0}">
                <h3>to take a bike</h3>
            </c:when>
            <c:when test="${user.rentStatus == 1}">
                <h3>to return the bike</h3>
            </c:when>
        </c:choose>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-danger">
                        <button><a href="${pageContext.request.contextPath}/Controller?command=show_user_station&id=1">Red station</a></button>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-success">
                        <button><a href="${pageContext.request.contextPath}/Controller?command=show_user_station&id=2">Green station</a></button>
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
                        <button><a href="${pageContext.request.contextPath}/Controller?command=show_user_station&id=3">Blue station</a></button>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-dark">
                        <button><a href="${pageContext.request.contextPath}/Controller?command=show_user_station&id=4">Black station</a></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
