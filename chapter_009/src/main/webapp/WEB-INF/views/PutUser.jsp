<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: apermyakov
  Date: 08.12.2017
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
    <br/>
    <form action="${requestScope.sessionContext.contextPath}/user/put" method="post">
        Insert ID of user, that need to be edit:<br/>
        ID : <input type='text' name='id'/><br/>
        Insert new information about user:<br/>
        Name : <input type='text' name='name'/><br/>
        Login : <input type='text' name='login'/><br/>
        Email : <input type='text' name='email'/><br/>
        <input type='submit' value='Edit user'>
    </form>
</body>
</html>
