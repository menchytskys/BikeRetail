
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="main.stations_on_map" var="stations"/>
    <fmt:message key="main.login" var="login"/>
    <fmt:message key="main.enter_login" var="enter"/>
    <fmt:message key="main.password" var="password"/>
    <fmt:message key="main.close" var="close"/>
    <fmt:message key="main.type_of_bikes" var="type"/>
    <fmt:message key="main.mountain" var="mountain"/>
    <fmt:message key="main.city" var="city"/>
    <fmt:message key="main.foldable" var="foldable"/>
</fmt:bundle>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/FP-style.css">
    <title>BIKETRO - the Bike Retail</title>
</head>

<body>
<header>
    <div>
        <nav class='container'>
            <a class='logo-box' href='#'>
                <h1 class="logo">BIKEtro</h1>
                <h2>Welcome to the Bike Retail</h2>
            </a>
            <ul class="menu">
                <li><a href='#map' class='button'>${stations}</a></li>
                <li><a href='#' class="button" data-toggle="modal" data-target="#myModal">${login}</a></li>
                <li> <a href="#">${language}</a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/Controller?command=common_change_language&locale=ru">RU</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/Controller?command=common_change_language&locale=by">BY</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/Controller?command=common_change_language&locale=en">EN</a>
                        </li>
                    </ul>
                </li>
                <!-- The Modal -->
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">${login}</h4>
                                <button type="button" class="close" data-dismiss="modal">×</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body">
                                <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/Controller">
                                    <input type="hidden" name="command" value="common_login" />
                                    <div class="form-group">
                                        <label for="usr">${enter}</label>
                                        <input type="text" name="login" id="usr" class="form-control" value=""/>
                                    </div>
                                    <div class="form-group">
                                        <label for="pwd">${password}</label>
                                        <input type="password" name="password" class="form-control" id="pwd" value=""/>
                                        <div style="padding-top: 10px;">
                                            ${loginError}
                                        </div>
                                    </div>
                                    <input class="button" type="submit" value="${login}"/>
                                </form>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="button" data-dismiss="modal">${close}</button>
                            </div>

                        </div>
                    </div>
                </div>
            </ul>
        </nav>
    </div>
</header>
<main>
    <div class="bike-tabs-container mt-3">
        <h3>${type}</h3>
        <br>
        <!-- Tab panes -->
        <div class="tab-content">
            <div id="mountain" class="container tab-pane active">
                <img src="images/Mountain_bike.png" width='70%'> <br>
            </div>
            <div id="city" class="container tab-pane fade">
                <img src="images/City_bike.png" width="70%">
                <br>
            </div>
            <div id="foldable" class="container tab-pane fade">
                <img src="images/Foldable_bike.png" width="70%">
                <br>
            </div>
        </div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#mountain">${mountain}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#city">${city}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#foldable">${foldable}</a>
            </li>
        </ul>
    </div>
</main>


<iframe id='map' src="https://yandex.ru/map-widget/v1/?um=constructor%3A581589c149735bd8db283782500690dd7df0060d47294823a70d843589e19358&amp;source=constructor" width="100%" height="100%" frameborder="0"></iframe>
</body>

</html>




