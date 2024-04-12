package com.fishing.guide.util;

import java.text.DecimalFormat;

public class Utilities {

    public static String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

}
