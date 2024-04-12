<%@include file="head.jsp"%> <!-- -->
<html>
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp"%> <!--Nav-->
        <div class="col-md-4"> <!--Main Body-->
            <div class="card bg-default">
                <h5 class="card-header">
                    Hello, ${user.firstName} ${user.lastName}
                </h5>
                <div class="card-body">
                    <p class="card-text">
                        First Name: ${user.firstName}
                        <br>
                        Last Name: ${user.lastName}
                        <br>
                        Email: ${user.email}
                        <br>
                        Home ZipCode: ${user.homeZip}
                        <br>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card bg-default">
                <h5 class="card-header">
                    Saved Locations
                </h5>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Zip Code</th>
                            <th>Nickname</th>
                            <th>Date Saved</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="location" items="${user.savedLocations}">
                            <tr>
                                <td>${location.zipCode}</td>
                                <td>Test</td>
                                <td>DateGoesHere</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%> <!--Footer-->
</div>
</body>
</html>
