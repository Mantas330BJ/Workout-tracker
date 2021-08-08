package com.example.workoutbasic;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
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

    //Modes:
    // 1 - Add intent listener
    // 2 - Edit cell

    public static final String WORKOUT_IDX = "widx";
    public static final String EXERCISE_IDX = "eidx";


    public static String[] columnNames = {"Date",
        "Exercise",
        "Set",
        "Reps",
        "RIR",
        "Rest",
        "Comments"};

    public static float textSize = 20;

    public static ArrayList<WorkoutData> workoutDatas;

    public static void initializeData() {
        workoutDatas = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            ArrayList<ExerciseData> exerciseDatas = new ArrayList<>(3);
            for (int j = 0; j < 3; ++j) {
                ArrayList<SetData> setDatas = new ArrayList<>(5);
                for (int k = 0; k < 5; ++k) {
                    SetData setData = new SetData(k + 1, (float)j / (k + 1), i, Duration.ofMinutes(2 * k + 1), "Goot.");
                    setDatas.add(k, setData);
                }
                ExerciseData exerciseData = new ExerciseData("Broadas" + j, setDatas);
                exerciseDatas.add(j, exerciseData);
            }
            WorkoutData workoutData = new WorkoutData(new Date(), exerciseDatas);
            workoutDatas.add(i, workoutData);
        }
    }


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

    public static LinearLayout createColumnNames(Context context, int i) {
        LinearLayout row = new LinearLayout(context);
        while (i < Data.columnNames.length) {
            String columnName = Data.columnNames[i];
            WorkoutTextView columnRowText = new WorkoutTextView(context);
            columnRowText.setWidth(Data.columnWidths[i]);
            columnRowText.setBaseParams(columnName);

            columnRowText.setTextAppearance(context, android.R.style.TextAppearance_Large);
            row.addView(columnRowText);
            ++i;
        }
        return row;
    }


}
