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
	<h1>QUẢN LÝ NGƯỜI DÙNG</h1>
    <c:url var="url" value="/user/crud"/>
    <form method="post">
        <!-- Nếu đang chỉnh sửa (có id), trường id sẽ không thể sửa -->
        <label for="id">ID Người dùng:</label>
        <input name="id" type="text" value="${user.id}" placeholder="ID" ${user.id != null ? 'readonly' : ''}><br>
        <label for="password">Mật khẩu:</label>
        <input name="password" type="password" value="${user.password}" placeholder="Password" ${user.password != null ? 'readonly' : ''}><br>
        <label for="fullname">Tên đầy đủ:</label>
        <input name="fullname" type="text" value="${user.fullname}" placeholder="Full Name"><br>
        <label for="email">Email:</label>
        <input name="email" type="text" value="${user.email}" placeholder="Email"><br>
        
        <label>Vai trò:</label>
        <input name="admin" type="radio" value="true" ${user.admin == true ? 'checked' : ''}> Admin
        <input name="admin" type="radio" value="false" ${user.admin == false ? 'checked' : ''}> User
        <hr>
        
        <!-- Các nút chức năng CRUD -->
        <input type="submit" formaction="${url}/create" value="Create">
        <input type="submit" formaction="${url}/update" value="Update">
        <input type="submit" formaction="${url}/delete" value="Delete">
        <input type="submit" formaction="${url}/reset" value="Reset">
    </form>

    <!-- Hiển thị thông báo thành công hoặc lỗi -->
    <c:if test="${not empty message}">
        <div style="color: green; text-align: center; font-weight: bold; margin: 10px;">
            ${message}
        </div>
    </c:if>

    <hr>
    <h1>DANH SÁCH NGƯỜI DÙNG</h1>

    <!-- Bảng hiển thị danh sách người dùng -->
    <c:if test="${not empty users}">
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.password}</td>
                        <td>${u.fullname}</td>
                        <td>${u.email}</td>
                        <td>${u.admin ? 'Admin' : 'User'}</td>
                        <td><a href="${url}/edit/${u.id}">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty users}">
        <div style="text-align: center; font-weight: bold;">
            Không có người dùng nào.
        </div>
    </c:if>
	
</body>
</html>