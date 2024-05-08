<%@include file="head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp" %>
        <div class="col-md-10">
            <div class="card bg-light" id="fishingGuideCard">
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/guide" method="POST" class="needs-validation" novalidate>
                        <div class="form-group row">
                            <label for="zipCode" class="col-md-2 col-form-label">Zip Code</label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="zipCode" id="zipCode" value="${currentZipcode}" required pattern="\d{5}" title="Enter a 5-digit zip code">
                                <div class="invalid-feedback">
                                    Please enter a valid 5-digit zip code.
                                </div>
                            </div>
                            <div class="col-md-5 text-right">
                                <c:if test="${newLocation == true}">
                                    <input type="submit" value="Save Location" name="saveLocation" id="saveLocationButton" class="btn btn-success">
                                </c:if>
                                <input type="submit" value="Submit" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>
                    <c:if test="${not empty cityName}">
                        <h5>Results for ${cityName}</h5>
                        <div class="row row-cols-12 row-cols-md-3 g-2">
                            <c:forEach var="forecast" items="${fiveDayForecast}">
                                <div class="col">
                                    <div class="card mb-3">
                                        <h6 class="card-header bg-secondary text-white">${forecast.formattedDate()}</h6>
                                        <ul class="ForecastData card-body">
                                            <li><span>Temp High:</span> <fmt:formatNumber value="${forecast.highTemp}" pattern="#"/>&#8457;</li>
                                            <li><span>Temp Low:</span> <fmt:formatNumber value="${forecast.lowTemp}" pattern="#"/>&#8457;</li>
                                            <li><span>Avg Pressure:</span> <fmt:formatNumber value="${forecast.avgPressure}" pattern="#.##"/> hPa</li>
                                            <li><span>Pressure Trend:</span> ${forecast.pressureTrend}</li>
                                            <li><span>Clouds:</span> <fmt:formatNumber value="${forecast.cloudCover}" pattern="#"/>%</li>
                                            <li><span>Precip:</span> <fmt:formatNumber value="${forecast.precipitation}" pattern="#.##"/>"</li>
                                        </ul>
                                        <h5 class="card-footer ${forecast.forecastColor()} text-white">Fishing Score: <fmt:formatNumber value="${forecast.calculateFishingScore()}" pattern="#.#"/></h5>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</div>

<script>
    $(document).ready(function() {
        (function() {
            'use strict';
            var forms = document.getElementsByClassName('needs-validation');
            Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });

            $('#zipCode').on('input', function() {
                var zipCodePattern = /^\d{5}$/;
                if (!zipCodePattern.test(this.value)) {
                    $(this).removeClass('is-valid').addClass('is-invalid');
                    $('#zipCode').siblings('.invalid-feedback').text('Please enter a valid 5-digit zip code.');
                } else {
                    $(this).removeClass('is-invalid').addClass('is-valid');
                }
            });
        })();

        $('#saveLocationButton').on('click', function(event) {
            event.preventDefault(); // Prevent form submission
            var zipCode = $('#zipCode').val();
            if ($('#zipCode').hasClass('is-invalid') || !zipCode) {
                alert("Please enter a valid Zip Code before saving."); // Alert if zip code is invalid
                return;
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/guide',
                type: 'POST',
                data: {
                    "saveLocation": true,
                    "zipCode": zipCode
                },
                success: function(response) {
                    alert('Location saved successfully');
                    window.location.href = '${pageContext.request.contextPath}/guide'; // Redirect if success
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('Error saving location: ' + textStatus); // Alert on AJAX error
                }
            });
        });
    });
</script>

</body>
</html>
