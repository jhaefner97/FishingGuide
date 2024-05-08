package com.persistence.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishing.guide.entity.DailyForecast;
import com.fishing.guide.util.PropertiesLoader;
import com.fishing.guide.util.Utilities;
import com.persistence.openWeather.ListItem;
import com.persistence.openWeather.OpenWeatherForecastData;
import com.persistence.openWeather.OpenWeatherGeo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class OpenWeatherDAO implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Utilities utils = new Utilities();

    private String getApiKey() throws Exception {
        Properties properties = loadProperties("/secrets.properties");
        return properties.getProperty("openweather.key");
    }

    public OpenWeatherGeo getLocationData (Integer zipCode) throws Exception {
        Client client = ClientBuilder.newClient();
        URI endpoint = URI.create("http://api.openweathermap.org/geo/1.0/zip");
        WebTarget target = client.target(endpoint)
                .queryParam("appid", getApiKey())
                .queryParam("zip", zipCode);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        OpenWeatherGeo locationResponse = null;
        try {
            locationResponse = mapper.readValue(response, OpenWeatherGeo.class);
        } catch (JsonProcessingException e) {
            logger.info("Error Extracting Data");
            throw new RuntimeException(e);
        }
        return locationResponse;
    }

    public OpenWeatherForecastData getForecastData (String lat, String lon) throws Exception {
        Client client = ClientBuilder.newClient();
        URI endpoint = URI.create("http://api.openweathermap.org/data/2.5/forecast");
        WebTarget target = client.target(endpoint)
                .queryParam("appid", getApiKey())
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "imperial");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        OpenWeatherForecastData forecastResponse = null;
        try {
            forecastResponse = mapper.readValue(response, OpenWeatherForecastData.class);
        } catch (JsonProcessingException e) {
            logger.info("Error Extracting Data");
            throw new RuntimeException(e);
        }
        return forecastResponse;
    }

    public List<DailyForecast> buildDailyForecasts (String lat, String lon) throws Exception {
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        OpenWeatherForecastData forecastData = getForecastData(lat, lon);

        // Group forecast data by date here
        Map<String, List<ListItem>> groupedForecastsByDate = forecastData.getList()
                .stream().collect(Collectors.groupingBy(forecast -> forecast.getDtTxt().split(" ")[0]));

        for (Map.Entry<String, List<ListItem>> entry : groupedForecastsByDate.entrySet()) {
            String date = entry.getKey();
            double highTemp = Double.MIN_VALUE;
            double lowTemp = Double.MAX_VALUE;
            double totalPressure = 0;
            double totalCloudCover = 0;
            double totalPrecipitation = 0;
            Double previousPressure = null;
            boolean pressureRising = true;
            DailyForecast dailyForecast = new DailyForecast();

            List<ListItem> forecasts = entry.getValue();
            for (ListItem forecast : forecasts) {
                double temp = forecast.getMain().getTemp();
                highTemp = Math.max(highTemp, temp);
                lowTemp = Math.min(lowTemp, temp);

                double pressure = forecast.getMain().getPressure();
                totalPressure += pressure;

                if (previousPressure != null && pressure < previousPressure) {
                    pressureRising = false;
                }
                previousPressure = pressure;

                totalCloudCover += forecast.getClouds().getAll();

                if (forecast.getRain() != null) {
                    totalPrecipitation += (double) forecast.getRain().getJsonMember3h();
                }
            }
            double avgPressure = totalPressure / forecasts.size();
            String pressureTrend = pressureRising ? "Rising" : "Falling";
            double avgCloudCover = totalCloudCover / forecasts.size();

            dailyForecast.setHourlyForecast(forecasts);
            dailyForecast.setDate(date);
            dailyForecast.setHighTemp(highTemp);
            dailyForecast.setLowTemp(lowTemp);
            dailyForecast.setAvgPressure(avgPressure);
            dailyForecast.setPressureTrend(pressureTrend);
            dailyForecast.setCloudCover(avgCloudCover);
            dailyForecast.setPrecipitation(totalPrecipitation / 25.4);

            dailyForecasts.add(dailyForecast);
        }

        return dailyForecasts;
    }

}
