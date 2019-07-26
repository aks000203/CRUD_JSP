<%--
  Created by IntelliJ IDEA.
  User: atul.singh
  Date: 7/25/2019
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management System </title>
</head>
<body>
    <center>
        <h1>UserManagement</h1>
        <h2>
            <a href="new">Add New User</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All User</a>
        </h2>
    </center>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>
            <%--<%--%>
                <%--int len = 5;%>--%>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.id}" ></c:out></td>
                    <td><c:out value="${user.name}" ></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.country}"></c:out></td>
                    <td>
                        <a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
