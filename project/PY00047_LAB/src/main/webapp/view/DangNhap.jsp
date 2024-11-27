<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <style>
  body {
    font-family: 'Verdana', sans-serif;
    background-color: #e3f2fd;
    display: flex;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    margin: 0;
    background: linear-gradient(135deg, #a1c4fd, #c2e9fb);
  }
  .container {
    background-color: #ffffff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
    width: 380px;
    max-width: 90%;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  .container:hover {
    transform: scale(1.03);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  }
  h1 {
    text-align: center;
    color: #1565c0;
    margin-bottom: 25px;
    font-weight: 600;
  }
  input[type="text"],
  input[type="password"] {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    border: 1px solid #90caf9;
    border-radius: 8px;
    box-sizing: border-box;
    font-size: 15px;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
  }
  input[type="text"]:focus,
  input[type="password"]:focus {
    border-color: #42a5f5;
    outline: none;
    box-shadow: 0 0 8px rgba(66, 165, 245, 0.6);
  }
  button[type="submit"] {
    background-color: #42a5f5;
    color: white;
    padding: 12px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    width: 100%;
    font-size: 16px;
    font-weight: bold;
    transition: background-color 0.3s ease, transform 0.2s ease;
  }
  button[type="submit"]:hover {
    background-color: #1e88e5;
  }
  button[type="submit"]:active {
    transform: scale(0.97);
  }
  label {
    font-weight: bold;
    margin-bottom: 6px;
    color: #1976d2;
    display: block;
  }
  .message {
    text-align: center;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 6px;
    font-size: 15px;
    font-weight: bold;
  }
  .success {
    background-color: #66bb6a;
    color: #ffffff;
  }
  .error {
    background-color: #ef5350;
    color: #ffffff;
  }
</style>

  
</head>
<body>
  <div class="container">
    <h1>LOGIN</h1>
    <!-- Hiển thị thông báo -->
    <%@ include file="/view/Lab5/header.jsp" %>
    

    <form method="post" action="DangNhapServlet">
      <div>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
      </div>

      <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
      </div>
          <c:if test="${not empty successMessage}">
      <div class="message success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
      <div class="message error">${errorMessage}</div>
    </c:if>
      
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>
