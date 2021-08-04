package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

/*
TODO:
 Freeze header.
 EditText hide newlines, allow scroll.
 Date picker
 Multiple fragment click bug fix.
 Implement button methods.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Data {
    public static String[] columnNames = {"Date",
        "Exercise",
        "Set",
        "Reps",
        "RIR",
        "Rest",
        "Comments"};

    public static float textSize = 20;

    public static WorkoutData[] workouts = {new WorkoutData(new Date(), "Broadas", 1, 5.19f, 3, Duration.ofMinutes(2), "Goot.")};

    public static String getStringDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String getStringFloat(float flt) {
        if ((int)(flt) == flt)
            return String.format(Locale.getDefault(), "%d", (int)flt);
        return String.format(Locale.getDefault(), "%.1f", flt);
    }

    public static String getStringDuration(Duration duration) {
        long seconds = duration.getSeconds();
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }
}
