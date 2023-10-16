<%@ page import="com.epam.webdemoapp.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.webdemoapp.models.Book" %>
<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com-->
<html lang="en" dir="ltr">


    <body>
    <h2>Users</h2>


    <%
        List<User> userList = (List<User>) request.getAttribute("users");
        List<Book> bookList = (List<Book>) request.getAttribute("books");
    %>


        <table border="2">
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Assigned Books</th>
            </tr>

            <%for (User user : userList) {%>
            <tr>
                <td><%=user.getName()%>
                </td>
                <td><%=user.getLastName()%>
                </td>
                <td><a href="/webdemo_war/edituser?id=<%=user.getId()%>">Edit</a></td>
                <td><a href="/webdemo_war/deleteuser?id=<%=user.getId()%>">Delete</a></td>

            </tr>
            <tr>
                <% for (Book book : bookList) {%>
                <%if (book.getUserId() == user.getId()) {%>
                <td><%=book.getBookName() %></td>
                <td><a href="/webdemo_war/unassign?id=<%=book.getId()%>">Unassign</a></td>
            </tr>
            <tr>
                <td><%=user.getName()%>
                </td>
                <td><%=user.getLastName()%>
                </td>
            </tr>

            <%}%><%}%><%}%>
        </table>


    </body>

</html>

