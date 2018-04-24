<%--
  Created by IntelliJ IDEA.
  User: Narayana
  Date: 31.03.2018
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add bike page</title>
</head>
<body>
<p>Add bike</p>
<form name="addBikeForm" method="POST" action="${pageContext.request.contextPath}/Controller">
    <input type="hidden" name="command" value="add_bike" />
    Brand:<br/>
    <input type="text" name="brand" value="" />
    <br/> Model:<br/>
    <input type="text" name="model" value="" />
    <br/> Price on Hour: <br/>
    <input type="text" name="priceOnHour" value="" />
    <br/>
    <select name="station">
        <option value="1">Station red</option>
        <option value="2">Station green</option>
        <option value="3">Station blue</option>
        <option value="4">Station black</option>
    </select>
    <input type="submit" value="add bike" />
    <a href="/jsp/admin/admin.jsp">back</a>
</form>
</body>
</html>
