
<%@ page import="java.util.List" %>
<%@ page import="com.epam.webdemoapp.models.User" %>
<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Edit User </title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%User userToEdit = (User)request.getAttribute("userToEdit");%>
<div class="wrapper">
    <h2>Edit user</h2>
    <br>
    <form action="/webdemo_war/edituser" method="post">
        <div class="input-box">
            <input name="name" type="text" placeholder="Enter user name" required value="<%=userToEdit.getName()%>">
        </div>
        <div class="input-box">
            <input name="lastName" type="text" placeholder="Enter user last name" required value="<%=userToEdit.getLastName()%>">
        </div>
        <div class="input-box">
            <input name="email" type="text" placeholder="Enter user email" required value="<%=userToEdit.getEmail()%>">
        </div>
        <input type="hidden" name="userId" value="<%=userToEdit.getId()%>">

        <div class="input-box button">
            <input type="Submit" value="Edit user">
        </div>
        <div class="text">
            <h3>Back to login page? <a href="/">Login now</a></h3>
        </div>
    </form>
    <br>
    <a href="/">Login</a>
</div>

</body>
</html>
