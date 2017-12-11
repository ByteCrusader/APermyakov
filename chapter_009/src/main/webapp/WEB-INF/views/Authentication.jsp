<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: apermyakov
  Date: 08.12.2017
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
    <br/>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        For further work, please, sign in<br/>
        Login : <input type="text" name="login"><br/>
        Password : <input type="password" name="password"><br/>
        <input type="submit" value="Sign In"><br/>
        <c:if test="${greeting != ''}">
            <c:out value="${greeting}"/>
        </c:if>
    </form>
    <br/>
</body>
</html>
