<%@include file="head.jsp"%> <!-- -->
<html>
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp"%> <!--Nav-->
        <div class="col-md-10"> <!--Main Body-->
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
    </div>
    <%@include file="footer.jsp"%> <!--Footer-->
</div>
</body>
</html>
