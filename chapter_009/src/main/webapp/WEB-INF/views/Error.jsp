<%--
  Created by IntelliJ IDEA.
  User: apermyakov
  Date: 11.12.2017
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add user error</title>
</head>
<body>
    <br/>
    <c:out value="You cannot add new user or edit/delete another user, please, contact the administrator"/>
    <br/>
    <br/>
    <form action="${requestScope.sessionContext.contextPath}/" method="get">
        <input type="submit" value="Back to previous page">
    </form>
</body>
</html>
