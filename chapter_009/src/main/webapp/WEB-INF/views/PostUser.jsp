<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: apermyakov
  Date: 08.12.2017
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
    <br/>
    <form action="${requestScope.sessionContext.contextPath}/user/post?createDate=${dateFormat.format(calendar)}&" method="post">
        Insert new user:<br/>
        Name : <input type='text' name='name'/><br/>
        Login : <input type='text' name='login'/><br/>
        Email : <input type='text' name='email'/><br/>
        <input type='submit' value='Add new user'>
    </form>
</body>
</html>
