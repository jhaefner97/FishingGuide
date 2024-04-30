<%@include file="head.jsp"%>
<html>
<body>

<!-- Edit Profile Modal -->
<div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog" aria-labelledby="editProfileModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProfileModalLabel">Edit User Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="editProfileForm" class="needs-validation" novalidate>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="newFirstName">First Name:</label>
                        <input type="text" class="form-control" id="newFirstName" value="${user.firstName}" required placeholder="Enter first name">
                        <div class="invalid-feedback">
                            First name is required.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newLastName">Last Name:</label>
                        <input type="text" class="form-control" id="newLastName" value="${user.lastName}" required placeholder="Enter last name">
                        <div class="invalid-feedback">
                            Last name is required.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newZipCode">Home Zipcode:</label>
                        <input type="text" class="form-control" id="newZipCode" value="${user.homeZip}" pattern="\d{5}" required placeholder="Enter 5-digit zipcode">
                        <div class="invalid-feedback">
                            Please enter a valid 5-digit zipcode.
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Edit Location Modal -->
<div class="modal fade" id="editLocationModal" tabindex="-1" role="dialog" aria-labelledby="editLocationModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editLocationModalLabel">Edit Location</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Enter a nickname for this location:</p>
                <input type="text" class="form-control" id="newLocationName" placeholder="New Location Name">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="editLocationButton" data-id="${location.id}">Edit Location</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Delete Location</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to remove this saved location?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="deleteLocationBtn" data-id="${location.id}">Delete Location</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp"%> <!-- Navigation bar included here -->

        <!-- User Data -->
        <div class="col-12 col-md-5"> <!-- Adjusted for equal width on medium to large screens -->
            <div class="card bg-light mb-3"> <!-- Added background color and margin for better spacing -->
                <div class="card-header">Hello, ${user.firstName} ${user.lastName}</div>
                <div class="card-body">
                    <p class="card-text">
                        First Name: ${user.firstName}<br>
                        Last Name: ${user.lastName}<br>
                        Email: ${user.email}<br>
                        Home ZipCode: ${user.homeZip}
                    </p>
                    <button class="btn btn-primary" id="editProfile">Edit Profile</button> <!-- Added a button for direct interaction -->
                </div>
            </div>
        </div>

        <!-- Saved Locations -->
        <div class="col-12 col-md-5"> <!-- Same adjustment for consistent layout -->
            <div class="card bg-light mb-3">
                <div class="card-header">Saved Locations</div>
                <div class="card-body">
                    <table class="table table-responsive-sm">
                        <thead>
                        <tr>
                            <th>Zip Code</th>
                            <th>Nickname</th>
                            <th>Date Saved</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="location" items="${user.savedLocations}">
                            <tr>
                                <td>${location.zipCode}</td>
                                <td>${location.locationAlias}</td>
                                <td>${location.dateSaved}</td>
                                <td>
                                    <i class="bi bi-pencil edit-icon" style="cursor:pointer; margin-right:0.5em;" data-id="${location.id}"></i>
                                    <i class="bi bi-trash delete-icon" style="cursor:pointer;" data-id="${location.id}"></i>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%> <!-- Footer included here -->
</div>

<script>

    // Edit Profile
    $(document).on('click', '#editProfile', function() {
        console.log('Editing User Profile');

        $('#editProfileModal').modal('show');
    });
    $(document).ready(function() {
        $('#editProfileForm').on('submit', function(event) {
            if (!this.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            $(this).addClass('was-validated');

            if (this.checkValidity()) {
                var formData = {
                    firstName: $('#newFirstName').val(),
                    lastName: $('#newLastName').val(),
                    homeZip: $('#newZipCode').val(),
                    action: 'updateProfile'
                };

                $.ajax({
                    url: '${pageContext.request.contextPath}/profile',
                    type: 'POST',
                    data: formData,
                    success: function() {
                        console.log('Updating Profile');
                        alert('Profile updated successfully!');
                        $('#editProfileModal').modal('hide');
                        location.reload();
                    },
                    error: function() {
                        alert('Error updating profile.');
                    }
                });
                event.preventDefault();
            }
        });
    });

    // Delete Selected Location
    $(document).on('click', '.delete-icon', function() {
        var locationId = $(this).data('id');
        console.log('Delete location with ID:', locationId);

        $('#deleteLocationBtn').data('id', locationId);

        $('#deleteModal').modal('show');
    });
    $('#deleteLocationBtn').click(function() {
        var locationId = $(this).data('id');

        $.ajax({
            url: '${pageContext.request.contextPath}/profile',
            type: 'POST',
            data: { locationId: locationId,
                    action: "deleteLocation"},
            success: function(response) {
                $('#deleteModal').modal('hide');
                alert('Location deleted successfully!');
                location.reload();
            },
            error: function(xhr, status, error) {
                alert('Error deleting location');
            }
        });
    });

    // Edit Location
    $(document).on('click', '.edit-icon', function() {
        var locationId = $(this).data('id');
        console.log('Edit location with ID:', locationId);

        $('#editLocationButton').data('id', locationId);

        $('#editLocationModal').modal('show');
    });
    $(document).ready(function() {
        $('#editLocationButton').click(function() {
            var locationId = $(this).data('id'); // Assuming you set this correctly when showing the modal
            var newLocationName = $('#newLocationName').val();

            $.ajax({
                url: '${pageContext.request.contextPath}/profile', // Endpoint for updating a location
                method: 'POST',
                data: {
                    editLocationId: locationId,
                    newLocationName: newLocationName,
                    action: "editLocation"
                },
                success: function(response) {
                    alert('Location updated successfully');
                    $('#editLocationModal').modal('hide');
                    location.reload();
                },
                error: function() {
                    alert('Error updating location');
                }
            });
        });
    });

</script>

</body>
</html>
