package com.example.workoutbasic.utils;

import java.time.LocalDate;
import java.util.Locale;

public class StringConverter {

    private StringConverter() {
    }

    public static String convertDouble(double dbl) {
        int precision = 0;
        double tDbl = dbl;
        double epsilon = 0.001;
        while (Math.abs(Math.round(tDbl) - tDbl) > epsilon) {
            tDbl *= 10;
            ++precision;
        }

        String format = "%." + precision + "f";
        return String.format(Locale.getDefault(), format, dbl);
    }

    public static String convertInt(int integer) {
        return Integer.toString(integer);
    }

    public static String convertRest(int seconds) {
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }

    public static String convertDate(LocalDate date) {
        return date.toString();
    }
}
