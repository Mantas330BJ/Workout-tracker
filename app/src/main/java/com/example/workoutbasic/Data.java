package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

/*
TODO:
 Freeze header.
 Date picker
 Multiple fragment click bug fix.
 Implement button methods.
 Clean code.
 Analyze cell line count.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Data {
    public static int[] columnWidths = {350, 350, 200, 200, 200, 200, 1000}; //TODO: automated widths


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

    public static void addText(TextView columnRowText, String columnName, Context context) {
        columnRowText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        columnRowText.setTextSize(Data.textSize);
        columnRowText.setSingleLine(true);
        columnRowText.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.border_right_bottom, null));
        columnRowText.setText(columnName);

        columnRowText.setOnClickListener((view) -> {
            MainActivity.currentClicked = columnRowText;
            Bundle bundle = new Bundle();
            bundle.putString(TextEditPopupFragment.EXTRA_STRING, columnRowText.getText().toString());
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.setArguments(bundle);
            popup.show(((FragmentActivity)context).getSupportFragmentManager(), "TextEditPopupFragment");
        });
    }

}
