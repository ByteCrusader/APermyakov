<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %><%--
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
    <%DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");%>
    <br/>
    <form action="<%=request.getContextPath()%>/user/post?createDate=<%=dateFormat.format(Calendar.getInstance().getTime())%>&" method="post">
        Insert new user:<br/>
        Name : <input type='text' name='name'/><br/>
        Login : <input type='text' name='login'/><br/>
        Email : <input type='text' name='email'/><br/>
        <input type='submit' value='Add new user'>
    </form>
</body>
</html>
