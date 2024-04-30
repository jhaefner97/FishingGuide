package com.fishing.guide.Entity;

import java.util.List;

/**
 * Represents a package of weather data for a specific city, including a five-day weather forecast.
 * This class encapsulates all the relevant weather information needed for planning and analysis
 * related to fishing activities or other weather-dependent events.
 * @author joshhaefner
 */
public class WeatherPackage {

    private final String cityName;
    private final List<DailyForecast> fiveDayForecast;

    /**
     * Constructs a new WeatherPackage object with a specified city name and a list of daily forecasts.
     *
     * @param cityName the name of the city for which the weather data is relevant.
     * @param fiveDayForecast a list of {@link DailyForecast} representing the weather forecast over five days.
     */
    public WeatherPackage(String cityName, List<DailyForecast> fiveDayForecast) {
        this.cityName = cityName;
        this.fiveDayForecast = fiveDayForecast;
    }

    /**
     * Gets the name of the city associated with this weather package.
     *
     * @return the name of the city as a String.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Gets the list of daily forecasts that make up the five-day weather forecast for the city.
     *
     * @return a list of {@link DailyForecast} which provides detailed weather information for each day.
     */
    public List<DailyForecast> getFiveDayForecast() {
        return fiveDayForecast;
    }
}
