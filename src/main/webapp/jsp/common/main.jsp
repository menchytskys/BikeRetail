<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>BIKETRO - the Bike Retail</title>
</head>
<body>
<header>
    <div class="container">
        <div class="logo-box">
        <h1 class="logo">Biketro</h1>
        <h2>Welcome to the Bike Retail</h2>
        </div>
        <div class="container-dots">
            <form>
                <input type="button" class="login-button" data-toggle="modal" data-target="#myModal" value="Login"
                       text="login" onClick='location.href="/jsp/common/login.jsp"'>
            </form>
        </div>
    </div>
</header>

</body></html>
