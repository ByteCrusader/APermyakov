<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%--
  Created by IntelliJ IDEA.
  User: apermyakov
  Date: 08.12.2017
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <br/>
        <c:out value="${users}" escapeXml="false"/>
    <br/>
    <form action="${requestScope.sessionContext.contextPath}/user/post" method="get">
        <input type="submit" value="Add new user">
    </form>
    <form action="${requestScope.sessionContext.contextPath}/user/put" method="get">
        <input type="submit" value="Edit user">
    </form>
    <form action="${requestScope.sessionContext.contextPath}/user/delete" method="get">
        <input type="submit" value="Delete user">
    </form>
</body>
</html>
