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

    public double calculateFishingScore() {
        // Define weights for each attribute
        double tempWeight = 0.25;
        double avgPressureWeight = 0.25;
        double pressureTrendWeight = 0.2;
        double cloudCoverWeight = 0.15;
        double precipitationWeight = 0.15;

        // Calculate scores for each attribute
        double tempScore = (highTemp >= 60 && highTemp <= 75) && (lowTemp >= 50 && lowTemp <= 65) ? 1 : 0;
        double avgPressureScore = avgPressure >= 1013 && avgPressure <= 1020 ? 1 : 0;
        double pressureTrendScore = "Rising".equals(pressureTrend) ? 1 : 0;
        double cloudCoverScore = cloudCover >= 40 && cloudCover <= 80 ? 1 : 0;
        double precipitationScore = precipitation >= 0 && precipitation <= 0.2 ? 1 : 0;

        // Calculate total score
        double totalScore = (tempScore * tempWeight) +
                (avgPressureScore * avgPressureWeight) +
                (pressureTrendScore * pressureTrendWeight) +
                (cloudCoverScore * cloudCoverWeight) +
                (precipitationScore * precipitationWeight);

        return Double.parseDouble(String.format("%.1f", totalScore * 10));
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

