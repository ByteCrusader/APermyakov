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
    Title : <input type='text' name='title'/><br/>
    Status : <input type='text' name='status'/><br/>
    Make : <input type='text' name='make'/><br/>
    Model : <input type='text' name='model'/><br/>
    Year : <input type='text' name='year'/><br/>
    Engine type : <input type='text' name='engine'/><br/>
    Horsepower : <input type='text' name='horsepower'/><br/>
    Gearbox type : <input type='text' name='gearbox'/><br/>
    User id : <input type='text' name='userId'/><br/>
    <input type='submit' value='Add advert'>
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
                    <th>Horsepower</th>
                    <th>Gearbox</th>
                    <th>Year</th>
                    <th>User name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${adverts}" var="advert">
                    <tr>
                        <td><c:out value="${advert.id}"/></td>
                        <td><c:out value="${advert.title}"/></td>
                        <td><c:out value="${advert.status}"/></td>
                        <td><c:out value="${advert.car.make}"/></td>
                        <td><c:out value="${advert.car.model}"/></td>
                        <td><c:out value="${advert.car.engine}"/></td>
                        <td><c:out value="${advert.car.horsepower}"/></td>
                        <td><c:out value="${advert.car.gearbox}"/></td>
                        <td><c:out value="${advert.car.year}"/></td>
                        <td><c:out value="${advert.user.name}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>