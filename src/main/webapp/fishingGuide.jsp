<%@include file="head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<body>
<div class="container-fluid">
    <div class="row">
        <%@include file="nav.jsp"%>
        <div class="col-md-10">
            <div class="card bg-default">
                <div class="card-header">
                    <h5>Weather Analysis</h5>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/guide" method="POST">
                        <div class="form-group row">
                            <label for="zipCode" class="col-md-2 col-form-label">Zip Code</label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="zipCode" id="zipCode" />
                            </div>
                            <div class="col-md-5 text-right">
                                <input type="submit" value="Submit" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>
                    <c:if test="${not empty cityName}">
                        <h5>Results for ${cityName}!</h5>
                        <div class="row">
                            <c:forEach var="forecast" items="${fiveDayForecast}">
                                <div class="col-md-2">
                                    <ul class="ForecastData">
                                        <li><span>Date:</span> ${forecast.date}</li>
                                        <li><span>Temperature High:</span> ${forecast.highTemp}</li>
                                        <li><span>Temperature Low:</span> ${forecast.lowTemp}</li>
                                        <li><span>Avg Daily Pressure:</span> ${forecast.avgPressure}</li>
                                        <li><span>Pressure Trend:</span> ${forecast.pressureTrend}</li>
                                        <li><span>Cloud Cover:</span> ${forecast.cloudCover}</li>
                                        <li><span>Precipitation:</span> ${forecast.precipitation}</li>
                                        <div class="popup-content">
                                            <c:forEach var="item" items="${forecast.hourlyForecast}">
                                                <div class="hourlyForecast">
                                                    <p><strong>Weather Details:</strong></p>
                                                    <p>Date: ${item.dtTxt}</p>
                                                    <p>Temperature: ${item.main.temp}°C</p>
                                                    <p>Humidity: ${item.main.humidity}%</p>
                                                    <p>Wind Speed: ${item.wind.speed} m/s</p>
                                                    <p>Clouds: ${item.clouds.all}%</p>
                                                    <p>Pressure: ${item.main.pressure} hPa</p>
                                                    <p>Visibility: ${item.visibility} meters</p>
                                                    <c:if test="${item.rain != null}">
                                                        <p>Rain: ${item.rain['3h']} mm</p>
                                                    </c:if>
                                                    <c:if test="${item.weather != null and !item.weather.isEmpty()}">
                                                        <p>Weather: ${item.weather[0].description}</p>
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </div>
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
        $('.ForecastData').hover(function() {
            $(this).find('.popup-content').show();
        }, function() {
            $(this).find('.popup-content').hide();
        });
    });
    $(document).ready(function() {
        $('.ForecastData').click(function() {
            $(this).find('.popup-content').toggle();
        });
    });

</script>
</body>
</html>
