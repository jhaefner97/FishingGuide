package com.persistence.database;

import com.fishing.guide.Entity.DailyForecast;
import com.persistence.openWeather.OpenWeatherForecastData;
import com.persistence.openWeather.OpenWeatherGeo;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class OpenWeatherDAOTest {
    OpenWeatherDAO openWeatherDAO;

    @BeforeEach
    void setup (){
        openWeatherDAO = new OpenWeatherDAO();
    }

    @Test
    void getLocationDataSuccess() throws Exception {
        OpenWeatherGeo geoResponse = openWeatherDAO.getLocationData(53590);
        assertEquals("Sun Prairie", geoResponse.getName()) ;
    }

    @Test
    void getForecastDataSuccess() throws Exception {
        OpenWeatherGeo geoResponse = openWeatherDAO.getLocationData(53590);
        OpenWeatherForecastData forecastResponse = openWeatherDAO.getForecastData(geoResponse.getLat(), geoResponse.getLon());
        assertEquals(40, forecastResponse.getList().size());
    }

    @Test
    void getDailyForecastSuccess() throws Exception {
        OpenWeatherGeo geoResponse = openWeatherDAO.getLocationData(53590);
        List<DailyForecast> generatedForecasts = openWeatherDAO.buildDailyForecasts(geoResponse.getLat(), geoResponse.getLon());
        assertNotNull(generatedForecasts);
    }
}