<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="menu.title" var="title"/>
    <fmt:message key="menu.admin_page" var="admin_page"/>
    <fmt:message key="menu.stations" var="stations"/>
    <fmt:message key="menu.station_red" var="red"/>
    <fmt:message key="menu.station_green" var="green"/>
    <fmt:message key="menu.station_blue" var="blue"/>
    <fmt:message key="menu.station_black" var="black"/>
    <fmt:message key="menu.users" var="users"/>
    <fmt:message key="menu.bikes" var="bikes"/>
    <fmt:message key="menu.logout" var="logout"/>
    <fmt:message key="menu.language" var="language"/>
</fmt:bundle>

<div class="container-center">
    <h1>${admin_page}</h1>
    <nav>
        <ul>
            <li><a href="#">${stations}</a>
                <ul class='sub-menu'>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=1">${red}</a></li>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=2">${green}</a></li>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=3">${blue}</a></li>
                    <li><a href="${pageContext.request.contextPath}/Controller?command=admin_show_admin_station&id=4">${black}</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/Controller?command=admin_show_all_users">${users}</a></li>
            <li><a href="${pageContext.request.contextPath}/Controller?command=admin_show_all_bikes">${bikes}</a></li>
            <li><a href="${pageContext.request.contextPath}/Controller?command=common_logout">${logout}</a></li>
        </ul>
    </nav>
</div>