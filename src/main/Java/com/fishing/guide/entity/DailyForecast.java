package com.fishing.guide.entity;

import com.fishing.guide.util.Utilities;
import com.persistence.openWeather.ListItem;

import java.util.List;

/**
 * Represents the daily weather forecast, containing details like temperature, pressure, cloud cover, and precipitation.
 * This data is used to calculate a fishing score based on weather conditions.
 * @author joshhaefner
 */
public class DailyForecast {
    private String date;
    private double highTemp;
    private double lowTemp;
    private double avgPressure;
    private String pressureTrend;
    private double cloudCover;
    private double precipitation;
    private List<ListItem> hourlyForecast;

    /**
     * Constructor for DailyForecast.
     */
    public DailyForecast() {
    }

    /**
     * Gets the hourly forecast data.
     *
     * @return a list of {@link ListItem} representing the hourly weather data.
     */
    public List<ListItem> getHourlyForecast() {
        return hourlyForecast;
    }

    /**
     * Sets the hourly forecast data.
     *
     * @param hourlyForecast a list of {@link ListItem} representing the hourly weather data.
     */
    public void setHourlyForecast(List<ListItem> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    /**
     * Gets the date of the forecast.
     *
     * @return the date as a String.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the forecast.
     *
     * @param date the date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the high temperature of the day.
     *
     * @return the high temperature as a double.
     */
    public double getHighTemp() {
        return highTemp;
    }

    /**
     * Sets the high temperature of the day.
     *
     * @param highTemp the high temperature to set.
     */
    public void setHighTemp(double highTemp) {
        this.highTemp = highTemp;
    }

    /**
     * Gets the low temperature of the day.
     *
     * @return the low temperature as a double.
     */
    public double getLowTemp() {
        return lowTemp;
    }

    /**
     * Sets the low temperature of the day.
     *
     * @param lowTemp the low temperature to set.
     */
    public void setLowTemp(double lowTemp) {
        this.lowTemp = lowTemp;
    }

    /**
     * Gets the average pressure of the day.
     *
     * @return the average pressure as a double.
     */
    public double getAvgPressure() {
        return avgPressure;
    }

    /**
     * Sets the average pressure of the day.
     *
     * @param avgPressure the average pressure to set.
     */
    public void setAvgPressure(double avgPressure) {
        this.avgPressure = avgPressure;
    }

    /**
     * Gets the pressure trend of the day.
     *
     * @return the pressure trend as a String.
     */
    public String getPressureTrend() {
        return pressureTrend;
    }

    /**
     * Sets the pressure trend of the day.
     *
     * @param pressureTrend the pressure trend to set.
     */
    public void setPressureTrend(String pressureTrend) {
        this.pressureTrend = pressureTrend;
    }

    /**
     * Gets the cloud cover percentage.
     *
     * @return the cloud cover as a double.
     */
    public double getCloudCover() {
        return cloudCover;
    }

    /**
     * Sets the cloud cover percentage.
     *
     * @param cloudCover the cloud cover percentage to set.
     */
    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     * Gets the precipitation amount.
     *
     * @return the precipitation amount as a double.
     */
    public double getPrecipitation() {
        return precipitation;
    }

    /**
     * Sets the precipitation amount.
     *
     * @param precipitation the precipitation to set.
     */
    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    /**
     * Calculates a fishing score based on various weather parameters.
     * The score is influenced by temperature, pressure, pressure trend, cloud cover, and precipitation levels.
     *
     * @return the calculated fishing score as a double.
     */
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

    /**
     * Formats the date from the date field into a more readable string format.
     *
     * @return the formatted date string.
     */
    public String formattedDate() {
        Utilities utils = new Utilities();
        return utils.formatDate(this.date);
    }

    /**
     * Determines the background color for UI representation based on the fishing score.
     *
     * @return a string representing the bootstrap class for background color.
     */
    public String forecastColor() {
        double fishingScore = calculateFishingScore();
        if (fishingScore <= 3) {
            return "bg-danger";
        } else if (fishingScore <= 6) {
            return "bg-warning";
        } else {
            return "bg-success";
        }
    }

    /**
     * Provides a string representation of the DailyForecast object, including all weather parameters.
     *
     * @return a string detailing all significant fields of the DailyForecast.
     */
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
