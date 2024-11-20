<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="POST">
    <label for="usernameOrEmail">Username/Email:</label>
    <input type="text" id="usernameOrEmail" name="usernameOrEmail" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Login</button>
</form>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
	
</body>
</html>