<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere|Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/admin-style.css">
    <title>BIKETRO Admin Page</title>
</head>

<body>
<div class="container-center">
    <h1>Admin Page</h1>
    <nav>
        <ul>
            <li class='active'><a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp">Stations</a>
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

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body bg-danger">
                    <button><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=1">Red station</a></button>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body bg-success">
                    <button><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=2">Green station</a></button>
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
                    <button><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=3">Blue station</a></button>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body bg-dark">
                    <button><a href="${pageContext.request.contextPath}/Controller?command=show_admin_station&id=4">Black station</a></button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

