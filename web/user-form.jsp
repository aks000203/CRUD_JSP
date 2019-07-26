<%--
  Created by IntelliJ IDEA.
  User: atul.singh
  Date: 7/26/2019
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Add New User</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="list">List All Users</a>
    </h2>
</center>
<div align="center">
    <c:if test="${user!=null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${user==null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user!=null}">
                            Edit User
                        </c:if>
                        <c:if test="${user==null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user!=null}">
                <caption>IDIDID:<input type="text" name="id" value=${param.id}></caption>
            </c:if>
            <tr>
                <th>User Name: </th>
                <td>
                    <input type="text" name="name" size="40"
                           value="<c:out value='${user.name}'/>">
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                <input type="email" name="email" size="40"
                       value="<c:out value='${user.email}'/> "/>
                </td>
            </tr>
            <tr>
                <th>Country: </th>
                <td>
                    <input type="text" name="country" size="40"
                           value="<c:out value='${user.country}'/> "/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                <input type="submit" value="ADD">
                </td>
            </tr>
            </table>
        </form>
    </div>
</body>
</html>
