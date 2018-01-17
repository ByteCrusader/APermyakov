<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ads</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<br/>
<form action="<%=request.getContextPath()%>/ads.do" method="post">
    Insert Ad:<br/>
    Description : <input type='text' name='description'/><br/>
    Status : <input type='text' name='status'/><br/>
    Make : <input type='text' name='make'/><br/>
    Model : <input type='text' name='model'/><br/>
    Year : <input type='text' name='year'/><br/>
    Engine id : <input type='text' name='engineId'/><br/>
    Gearbox id : <input type='text' name='gearboxId'/><br/>
    Transmission id : <input type='text' name='transmissionId'/><br/>
    User id : <input type='text' name='userId'/><br/>
    <input type='submit' value='Add ad'>
</form>
<div class="container">
    <div class="row">
        <h3>List of ads</h3>
        <div class="col-md-12">
            <table class="table table-bordered">
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Engine</th>
                    <th>Gearbox</th>
                    <th>Transmission</th>
                    <th>Year</th>
                    <th>User name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ads}" var="ad">
                    <tr>
                        <td><c:out value="${ad.id}"/></td>
                        <td><c:out value="${ad.description}"/></td>
                        <td><c:out value="${ad.status}"/></td>
                        <td><c:out value="${ad.car.make}"/></td>
                        <td><c:out value="${ad.car.model}"/></td>
                        <td><c:out value="${ad.car.engine}"/></td>
                        <td><c:out value="${ad.car.gearbox}"/></td>
                        <td><c:out value="${ad.car.transmission}"/></td>
                        <td><c:out value="${ad.car.year}"/></td>
                        <td><c:out value="${ad.user.name}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>