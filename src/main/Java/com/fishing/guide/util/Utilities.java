package com.fishing.guide.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Provides utility methods for formatting numeric and date values.
 * @author joshhaefner
 */
public class Utilities {

    /**
     * Formats a double value to a string with two decimal places.
     *
     * @param value the double value to format.
     * @return a formatted string representing the double value with two decimal places.
     */
    public static String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

    /**
     * Converts a date string into a more readable string format.
     * The output format is "Day of the Week, Day Month Year" (e.g. "Wednesday, 01 January 2020").
     *
     * @param dateString the date string to be formatted
     * @return a formatted string representing the date in a more readable format.
     */
    public String formatDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        return date.format(formatter);
    }
}
