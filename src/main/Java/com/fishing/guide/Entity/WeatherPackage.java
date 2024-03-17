package com.fishing.guide.Entity;

import java.util.List;

public class WeatherPackage {

    private final String cityName;
    private final List<DailyForecast> fiveDayForecast;

    public WeatherPackage(String cityName, List<DailyForecast> fiveDayForecast) {
        this.cityName = cityName;
        this.fiveDayForecast = fiveDayForecast;
    }

    public String getCityName() {
        return cityName;
    }

    public List<DailyForecast> getFiveDayForecast() {
        return fiveDayForecast;
    }
}
