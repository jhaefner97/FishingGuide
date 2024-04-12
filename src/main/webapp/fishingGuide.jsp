<%@include file="head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<body>
<script>
    $(document).ready(function() {
        <c:if test="${not empty locationError}">
        alert('${locationError}');
        </c:if>
        <c:if test="${not empty locationSaved}">
        alert('${locationSaved}');
        </c:if>
    });
</script>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp" %>
        <div class="col-md-10">
            <div class="card bg-default" id="fishingGuideCard">
                <div class="card-header">
                    <h5>Weather Analysis</h5>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/guide" method="POST" onsubmit="validateForm()">
                        <div class="form-group row">
                            <label for="zipCode" class="col-md-2 col-form-label">Zip Code</label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="zipCode" id="zipCode" value="${currentZipcode}" />
                            </div>
                            <div class="col-md-5 text-right">
                                <c:if test="${newLocation == true}">
                                    <input type="submit" value="Save Location" name="saveLocation" class="btn btn-success">
                                </c:if>
                                <input type="submit" value="Submit" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>
                    <c:if test="${not empty cityName}">
                        <h5>Results for ${cityName}!</h5>
                        <div class="row">
                            <c:forEach var="forecast" items="${fiveDayForecast}">
                                <div class="col-md-2">
                                    <h5>Fishing Score: <fmt:formatNumber value="${forecast.calculateFishingScore()}" pattern="#.#"/></h5>
                                    <ul class="ForecastData">
                                        <li><span>Date:</span> ${forecast.date}</li>
                                        <li><span>Temp High:</span> <fmt:formatNumber value="${forecast.highTemp}" pattern="#"/>&#8457;</li>
                                        <li><span>Temp Low:</span> <fmt:formatNumber value="${forecast.lowTemp}" pattern="#"/>&#8457;</li>
                                        <li><span>Avg Pressure:</span> <fmt:formatNumber value="${forecast.avgPressure}" pattern="#.##"/> hPa</li>
                                        <li><span>Pressure Trend:</span> ${forecast.pressureTrend}</li>
                                        <li><span>Clouds</span> <fmt:formatNumber value="${forecast.cloudCover}" pattern="#"/>%</li>
                                        <li><span>Precip:</span> <fmt:formatNumber value="${forecast.precipitation}" pattern="#.##"/>"</li>
                                    </ul>
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
        $("form").submit(function(event) {
            var zipCode = $("#zipCode").val();
            if (zipCode == "") {
                event.preventDefault(); // Prevent the form from submitting
                alert("Zip Code must be filled out");
            }
        });
    });
</script>
</script>
</body>
</html>
