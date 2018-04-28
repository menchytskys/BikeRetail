
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="customtags"%>
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
            <a class='logo-box' href='biketro.by'>
                <h1 class="logo">BIKEtro</h1>
                <h2>Welcome to the Bike Retail</h2>
            </a>
            <ul class="menu">
                <li><a href='#map' class='button'>Stations on map</a></li>
                <li><a href='#' class="button" data-toggle="modal" data-target="#myModal"> Login</a></li>
                <!-- The Modal -->
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">LogIn</h4>
                                <button type="button" class="close" data-dismiss="modal">×</button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body">
                                <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/Controller">
                                    <input type="hidden" name="command" value="login" />
                                    <div class="form-group">
                                        <label for="usr">Enter your login:</label>
                                        <input type="text" name="login" id="usr" class="form-control" value=""/>
                                    </div>
                                    <div class="form-group">
                                        <label for="pwd"> & password:</label>
                                        <input type="password" name="password" class="form-control" id="pwd" value=""/>
                                        <div style="padding-top: 10px;">
                                            ${loginError}
                                        </div>
                                    </div>
                                    <input type="submit" value="log in"/>
                                </form>
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="button" data-dismiss="modal">Close</button>
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
        <h3>3 types of bike for your type of ride</h3>
        <br>
        <!-- Tab panes -->
        <div class="tab-content">
            <div id="mountain" class="container tab-pane active">
                <img src="images/Mountain_bike.png" width='50%'> <br>
            </div>
            <div id="city" class="container tab-pane fade">
                <img src="images/City_bike.png" width="50%">
                <br>
            </div>
            <div id="foldable" class="container tab-pane fade">
                <img src="images/Foldable_bike.png" width="50%">
                <br>
            </div>
        </div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#mountain">Mountain</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#city">City</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#foldable">Foldable</a>
            </li>
        </ul>
    </div>
</main>


<iframe id='map' src="https://yandex.ru/map-widget/v1/?um=constructor%3A581589c149735bd8db283782500690dd7df0060d47294823a70d843589e19358&amp;source=constructor" width="100%" height="100%" frameborder="0"></iframe>
</body>

</html>






<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="ctg" uri="customtags"%>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere" rel="stylesheet">--%>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">--%>
    <%--<title>BIKETRO - the Bike Retail</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<header>--%>
    <%--<div class="container">--%>
        <%--<div class="logo-box">--%>
        <%--<h1 class="logo">Biketro</h1>--%>
        <%--<h2>Welcome to the Bike Retail</h2>--%>
        <%--</div>--%>
        <%--<div class="container-dots">--%>
            <%--<form>--%>
                <%--<input type="button" class="login-button" data-toggle="modal" data-target="#myModal" value="Login"--%>
                       <%--text="login" onClick='location.href="/jsp/common/login.jsp"'>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</header>--%>

<%--</body></html>--%>
