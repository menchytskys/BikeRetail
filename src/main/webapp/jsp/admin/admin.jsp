
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Admin Page</h1>
<hr/>
<p>Name: ${user.name}</p>
<p>Role: ${user.userRole}</p>
<hr/>
<div>
    <form name="ShowAllStationForm" method="POST" action="${pageContext.request.contextPath}/Controller">
        <input type="hidden" name="command" value="show_all_stations" />
        <input type="submit" value="show stations" />
    </form>
    <form name="ShowAllUsersForm" method="POST" action="${pageContext.request.contextPath}/Controller">
        <input type="hidden" name="command" value="show_all_users" />
        <input type="submit" value="show users" />
    </form>
    <form name="ShowAllUsersForm" method="POST" action="${pageContext.request.contextPath}/Controller">
        <input type="hidden" name="command" value="show_all_bikes" />
        <input type="submit" value="show users" />
    </form>
    <br/>
    <a href="/jsp/admin/addBike.jsp">Add bike</a>
    <br/>
</div>
<a href="Controller?command=logout">Logout</a>
</body>
</html>
