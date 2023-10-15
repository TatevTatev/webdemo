<%@ page import="com.epam.webdemoapp.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.webdemoapp.models.Book" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.10.2023
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Sign Up form</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="wrapper">
    <h2>Welcome dear <%=((User) session.getAttribute("user")).getName()%>
    </h2>
    <h3>Please add your book here</h3>


    <form action="/webdemo_war/addbook" method=post>
        <%
            if (request.getAttribute("bookAdded") != null) {
        %>
        <span style="color: green"><%=request.getAttribute("bookAdded")%></span>
        <%}%>
        <div class="input-box">
            <input name="bookName" type="text" placeholder="Enter Book's Name" required>
        </div>
        <div class="input-box">
            <input name="author" type="text" placeholder="Enter author's name" required>
        </div>
        <div class="input-box button">
            <input type="Submit" value="Confirm">
        </div>
        <div class="text">

        </div>
    </form>
</div>
<%
    List<User> userList = (List<User>) request.getAttribute("users");
    List<Book> bookList = (List<Book>) request.getAttribute("books");
%>

<div class="wrapper">
    <form action="/webdemo_war/assign" method=post>
        <%
            if (request.getAttribute("successAssign") != null) {
        %>
        <span style="color: green"><%=request.getAttribute("successAssign")%></span>
        <%}%>

        <div class="input-box">
            <label>Choose a user:</label>
            <select name="selectedUser" id="selectedUser" >
                <% for (User user : userList) {
                %>
                <option value="<%=user.getId()%>"><%=user.getName()%>/<%=user.getLastName()%>
                </option>
                <%}%>
            </select>
            </label>
            <br><br>
        </div>
        <br>
        <br>

        <div class="input-box">
            <label>Assigned books :</label>
            <select name="selectedBook" id="selectedBook">
                <% for (Book book : bookList) {
                %>
                <option value="<%=book.getId()%>"><%=book.getBookName()%>/<%=book.getAuthor()%>
                    <%book.getUserId();%>
                </option>
                <%}%>
            </select>
        </label>
            <br><br>
            <input type="submit" value="Assign">

</div>
    </form>
<div class="text">

</div>





</form>
</div>

</body>
</html>