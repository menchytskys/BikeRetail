<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="page_content">
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
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/admin-style.css">
    <title>BIKETRO Admin Page</title>
</head>

<body>

<tag:adminMenu/>
<div class="container-center">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-danger">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=1">${red}</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-success">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=2">${green}</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-info">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=3">${blue}</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body bg-dark">
                        <a class="button"
                           href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=4">${black}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

