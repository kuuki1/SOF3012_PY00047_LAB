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
	<form action="search" method="GET">
	    <input type="text" name="keyword" placeholder="Search videos..." required>
	    <button type="submit">Search</button>
	</form>
	
	<table>
	    <thead>
	        <tr>
	            <th>Title</th>
	            <th>Description</th>
	            <th>Views</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="video" items="${videos}">
	            <tr>
	                <td>${video.title}</td>
	                <td>${video.description}</td>
	                <td>${video.views}</td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	
</body>
</html>