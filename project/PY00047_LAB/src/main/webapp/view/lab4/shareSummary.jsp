<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Share Summary</title>
</head>
<body>
    <h1>Thông Tin Chia Sẻ Tổng Hợp</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Tiêu Đề Video</th>
                <th>Số Lượt Chia Sẻ</th>
                <th>Ngày Chia Sẻ Đầu Tiên</th>
                <th>Ngày Chia Sẻ Cuối Cùng</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="video" items="${videoShareSummary}">
                <tr>
                    <td>${video.title}</td>
                    <td>${video.shareCount}</td>
                    <td>${video.firstShareDate}</td>
                    <td>${video.lastShareDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
