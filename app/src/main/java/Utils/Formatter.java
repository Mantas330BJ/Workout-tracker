package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Formatter {

    public static String formatDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String formatDouble(double dbl) {
        int precision = 0;
        double tflt = dbl, epsilon = 0.001;
        while (Math.abs(Math.round(tflt) - tflt) > epsilon) {
            tflt *= 10;
            ++precision;
        }

        String format = "%." + precision + "f";
        return String.format(Locale.getDefault(), format, dbl);
    }

    public static String formatInteger(int integer) {
        return Integer.toString(integer);
    }

    public static String formatDuration(int seconds) {
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }
}
