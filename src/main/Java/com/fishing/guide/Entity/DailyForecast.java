package com.fishing.guide.Entity;

import com.persistence.openWeather.ListItem;

import java.util.List;

public class DailyForecast {
    private String date;
    private double highTemp;
    private double lowTemp;
    private double avgPressure;
    private String pressureTrend;
    private double cloudCover;
    private double precipitation;

    public void setHourlyForecast(List<ListItem> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public List<ListItem> getHourlyForecast() {
        return hourlyForecast;
    }

    private List<ListItem> hourlyForecast;

    public DailyForecast() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(double highTemp) {
        this.highTemp = highTemp;
    }

    public double getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(double lowTemp) {
        this.lowTemp = lowTemp;
    }

    public double getAvgPressure() {
        return avgPressure;
    }

    public void setAvgPressure(double avgPressure) {
        this.avgPressure = avgPressure;
    }

    public String getPressureTrend() {
        return pressureTrend;
    }

    public void setPressureTrend(String pressureTrend) {
        this.pressureTrend = pressureTrend;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "date='" + date + '\'' +
                ", highTemp=" + highTemp +
                ", lowTemp=" + lowTemp +
                ", avgPressure=" + avgPressure +
                ", pressureTrend='" + pressureTrend + '\'' +
                ", cloudCover=" + cloudCover +
                ", precipitation=" + precipitation +
                '}';
    }
}

