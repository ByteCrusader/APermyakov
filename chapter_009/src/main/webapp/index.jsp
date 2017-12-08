<%@ page import="ru.apermyakov.servlets.UserStore" %><%--
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
        <%=UserStore.getInstance().get()%>
    <br/>
    <form action="<%=request.getContextPath()%>/post.jsp" method="get">
        <input type="submit" value="Add new user">
    </form>
    <form action="<%=request.getContextPath()%>/put.jsp" method="get">
        <input type="submit" value="Edit user">
    </form>
    <form action="<%=request.getContextPath()%>/delete.jsp" method="get">
        <input type="submit" value="Delete user">
    </form>
</body>
</html>
