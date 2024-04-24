<%@include file="head.jsp"%> <!-- -->
<html>
<body>

<!--Edit Profile MODAL--->
<div class="modal" tabindex="-1" role="dialog" id="editProfileModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit User Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="editProfileForm" novalidate>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="newFirstName">First Name:</label>
                        <input type="text" class="form-control" value="${user.firstName}" id="newFirstName" required>
                        <div class="invalid-feedback">
                            Please enter a first name.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newLastName">Last Name:</label>
                        <input type="text" class="form-control" value="${user.lastName}" id="newLastName" required>
                        <div class="invalid-feedback">
                            Please enter a last name.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newZipCode">Home Zipcode:</label>
                        <input type="text" class="form-control" value="${user.homeZip}" id="newZipCode" pattern="\d{5}" required>
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

<!--Edit Location MODAL--->
<div class="modal" tabindex="-1" role="dialog" id="editLocationModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Location</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Enter a nickname for this location!</p>
                <label for="newLocationName">Location Name:</label>
                <input type="text" placeholder="New Location Name" id="newLocationName">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="editLocationButton" data-id="${location.id}">Edit Location</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--DELETE MODAL--->
<div class="modal" tabindex="-1" role="dialog" id="deleteModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete Location</h5>
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
        <%@include file="nav.jsp"%> <!--Nav-->

        <!--User Data-->
        <div class="col-md-4"> <!--Main Body-->
            <div class="card bg-default">
                <div class="card-header">
                    <h5>
                        Hello, ${user.firstName} ${user.lastName}
                        <i class="bi bi-pencil" style="margin-left:1em" id="editProfile"></i>
                    </h5>

                </div>
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

        <!--Saved Locations-->
        <c:if test="${not empty user.savedLocations}">
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
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="location" items="${user.savedLocations}">
                            <tr>
                                <td>${location.zipCode}</td>
                                <td>${location.locationAlias}</td>
                                <td>${location.dateSaved}</td>
                                <td>
                                    <i class="bi bi-pencil edit-icon" style="margin-right:0.5em;" data-id="${location.id}"></i>
                                    <i class="bi bi-trash delete-icon" data-id="${location.id}"></i>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </c:if>
    </div>
    <%@include file="footer.jsp"%> <!--Footer-->
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
                    url: '/castAndClimate_war/profile',
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
            url: '/castAndClimate_war/profile',
            type: 'POST',
            data: { locationId: locationId },
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
                url: '/castAndClimate_war/profile', // Endpoint for updating a location
                method: 'POST',
                data: {
                    editLocationId: locationId,
                    newLocationName: newLocationName
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
