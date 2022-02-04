package com.example.workoutbasic.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;
import com.example.workoutbasic.WorkoutDatabaseHelper;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.models.Workout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/*
TODO:
 Pass should have listener to binding
 Analyse two way binding
 Show active tab in navigation.
 Fix empty string parse error
 File changed error.
 Improve database efficiency.
 Fix inconsistencies on empty data.
 Multiple fragment click bug fix.
 Analyze cell line count.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataRetriever {

    private DataRetriever() {
    }

    private static final ArrayList<Workout> WORKOUTS = new ArrayList<>();

    public static void initializeData(Context context) {
        SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(context);
        try {
            SQLiteDatabase db = workoutDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from WORKOUT", null);
            if (cursor.moveToFirst()) {
                do {
                    String storedObject = cursor.getString(1);
                    Gson gson = new Gson();
                    Workout workout = gson.fromJson(storedObject, Workout.class);
                    WORKOUTS.add(workout);
                } while (cursor.moveToNext());
            }
            db.close();
            cursor.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(context, context.getString(R.string.database_unavailable), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void addWorkoutData(SQLiteDatabase db, Workout workout) { //TODO: transition to db
        ContentValues workoutValues = new ContentValues();
        Gson gson = new Gson();
        String storedObject = gson.toJson(workout, Workout.class);
        workoutValues.put("DATA", storedObject);
        db.insert("WORKOUT", null, workoutValues);
    }

    public static void incrementSets(int position, List<Set> sets) {
        for (int i = position; i < sets.size(); ++i) {
            sets.get(i).setSetIdx(i + 1);
        }
    }

    public static List<Workout> getWorkoutDatas() {
        return WORKOUTS;
    }
}
