<%@ page import="com.epam.webdemoapp.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 09.10.2023
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My page</title>
</head>
<body>
<h1>Welcome dear <%=((User)session.getAttribute("user")).getLastName()%></h1>
</body>
</html>
