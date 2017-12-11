<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: apermyakov
  Date: 08.12.2017
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
    <br/>
    <form action="${requestScope.sessionContext.contextPath}/user/delete" method="post">
        Insert ID of user, that need to be delete:
        <br/>
        ID : <input type='text' name='id'/>
        <input type='submit' value='Delete user'>
    </form>
    <form action="${requestScope.sessionContext.contextPath}/" method="get">
        <input type='submit' value='Back'>
    </form>
</body>
</html>
