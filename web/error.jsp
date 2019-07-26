<%--
  Created by IntelliJ IDEA.
  User: atul.singh
  Date: 7/26/2019
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Oh No!Error Occured.</title>
</head>
<body>
    <center>
        <h2>Oh No! Something has gone wrong. Have a coffee till we fix it.
        <%=  exception.getMessage()%><br><br>>
        </h2>
    </center>

</body>
</html>
