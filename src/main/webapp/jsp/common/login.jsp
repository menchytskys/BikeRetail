<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Fredericka+the+Great|Karma|Pompiere|Rokkitt" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin-style.css">
    <title>Login to the BIKETRO</title>
</head>
<body>
<div class="container-login">
    <h1>Log in to select a bike</h1>
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
</body></html>
