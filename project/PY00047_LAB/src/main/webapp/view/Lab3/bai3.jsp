<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Video yêu thích của ${user.fullname}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .error {
            color: red;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh sách Video yêu thích của ${user.fullname}</h1>

        <!-- Form để nhập ID người dùng -->
        <form action="Bai3Servlet" method="GET">
            <label for="userId">Nhập ID người dùng:</label>
            <input type="text" id="userId" name="userId" required>
            <input type="submit" value="Xem">
        </form>

        <c:if test="${not empty favorites}">
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tiêu đề Video</th>
                        <th>Ngày yêu thích</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="favorite" items="${favorites}" varStatus="status">
                        <tr>
                            <!-- Sử dụng status.index để hiển thị số thứ tự -->
                            <td>${status.index + 1}</td>
                            <td>${favorite.video.title}</td>
                            <td>${favorite.likeDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty favorites}">
            <p class="error">Không có video yêu thích nào.</p>
        </c:if>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</body>
</html>
