<%@include file="head.jsp"%> <!-- -->
<html>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10"> <!--Main Body-->
            <div class="card bg-default">
                <h5 class="card-header">
                    Additional User Information
                </h5>
                <div class="card-body">
                    <form class="form-group" action="${pageContext.request.contextPath}/profile" method="POST">
                        <label>First Name</label>
                        <input type="text" class="form-control" name="firstName" placeholder="First Name">
                        <label>Last Name</label>
                        <input type="text" class="form-control" name="lastName" placeholder="Last Name">
                        <label>Home Zip Code</label>
                        <input type="text" class="form-control" name="homeZip" placeholder="Home Zip Code">
                        <input type="submit" value="Submit" class="btn btn-primary"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%> <!--Footer-->
</div>
</body>
</html>
