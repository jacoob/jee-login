<%@ page import="mahdi.learning.jee.loginwebprofile.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: m313a
  Date: 2/20/2024
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<h2>Profile</h2>
<% UserDto user = (UserDto) request.getSession().getAttribute("user"); %>
<p>Welcome, <%= user.getProfileDto().getName() %>!</p>
<p>Email: <%= user.getProfileDto().getEmail() %></p>
<a href="logout">Logout</a>
</body>
</html>
