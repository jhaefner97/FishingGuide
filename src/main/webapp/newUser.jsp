<%@include file="head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <div class="card">
                <h5 class="card-header bg-light text-dark">Additional User Information</h5>
                <div class="card-body">
                    <form id="userInfoForm" class="needs-validation" novalidate>
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" required>
                            <div class="invalid-feedback">Please enter your first name.</div>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" required>
                            <div class="invalid-feedback">Please enter your last name.</div>
                        </div>
                        <div class="form-group">
                            <label for="zipCode">Home Zip Code</label>
                            <input type="text" class="form-control" id="zipCode" name="homeZip" placeholder="Enter zip code" required>
                            <div class="invalid-feedback">Please enter your home zip code.</div>
                        </div>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</div>
<script>
    $(document).ready(function() {
        $('#userInfoForm').on('submit', function(event) {
            event.preventDefault();
            if(this.checkValidity() === false) {
                event.stopPropagation();
                $(this).addClass('was-validated');
            } else {
                var formData = {
                    firstName: $('#firstName').val(),
                    lastName: $('#lastName').val(),
                    homeZip: $('#zipCode').val(),
                    action: 'createUser'
                };
                $.ajax({
                    url: '${pageContext.request.contextPath}/profile',
                    type: 'POST',
                    data: formData,
                    success: function() {
                        console.log('Creating Profile');
                        alert('Profile Created Successfully!');
                        window.location.href = '${pageContext.request.contextPath}/home'; // Redirects to the home page
                    },
                    error: function() {
                        alert('Error Creating profile.');
                    }
                });
            }
        });
    });
</script>
</body>
</html>
