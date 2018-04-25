
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<h1>Admin Page</h1>
<hr/>
<nav>
    <ul class="topmenu">
        <li>Stations
            <ul>
                <li><a href="">Red</a></li>
                <li><a href="">Green</a></li>
                <li><a href="">Blue</a></li>
                <li><a href="">Black</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/Controller?command=show_all_users">Users</a></li>
        <li><a href="">Bikes</a></li>
    </ul>
    <button><a href="Controller?command=logout">log out</a></button>
</nav>

<%--<div>--%>
    <%--<form name="ShowAllStationForm" method="POST" action="${pageContext.request.contextPath}/Controller">--%>
        <%--<input type="hidden" name="command" value="show_all_stations" />--%>
        <%--<input type="submit" value="show stations" />--%>
    <%--</form>--%>
    <%--<form name="ShowAllUsersForm" method="POST" action="${pageContext.request.contextPath}/Controller">--%>
        <%--<input type="hidden" name="command" value="show_all_users" />--%>
        <%--<input type="submit" value="show users" />--%>
    <%--</form>--%>
    <%--<form name="ShowAllUsersForm" method="POST" action="${pageContext.request.contextPath}/Controller">--%>
        <%--<input type="hidden" name="command" value="show_all_bikes" />--%>
        <%--<input type="submit" value="show users" />--%>
    <%--</form>--%>
    <%--<br/>--%>
    <%--<a href="/jsp/admin/addBike.jsp">Add bike</a>--%>
    <%--<br/>--%>
<%--</div>--%>
<%--<a href="Controller?command=logout">Logout</a>--%>
</body>
</html>
