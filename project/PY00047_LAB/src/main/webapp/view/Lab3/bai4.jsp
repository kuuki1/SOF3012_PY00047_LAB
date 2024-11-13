<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Yêu Thích</title>
    <style>
        /* Global Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 800px;
        }

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            border-bottom: 2px solid #ddd;
        }

        td {
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* Message Styling */
        p {
            text-align: center;
            font-size: 16px;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh sách Yêu Thích</h1>

        <c:if test="${not empty favorites}">
            <table>
                <tr>
                    <th>STT</th>
                    <th>Tiêu đề Video</th>
                    <th>Người Dùng</th>
                    <th>Ngày Yêu Thích</th>
                </tr>
                <c:forEach var="favorite" items="${favorites}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${favorite.video.title}</td>
                        <td>${favorite.user.fullname}</td>
                        <td>${favorite.likeDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty favorites}">
            <p>Không có video yêu thích nào được tìm thấy.</p>
        </c:if>
    </div>
</body>
</html>
