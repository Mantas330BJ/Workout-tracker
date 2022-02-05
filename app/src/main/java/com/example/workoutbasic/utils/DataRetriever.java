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
import com.example.workoutbasic.models.Exercise;
import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.models.Workout;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public static Workout convert(WorkoutO workoutO) {
        Workout workout = new Workout();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss");
        workout.setWorkoutDate(LocalDate.parse(workoutO.date.date, formatter));

        workout.setExercises(convert(workoutO.exercises));
        return workout;
    }

    public static List<Exercise> convert(List<ExerciseO> exercises) {
        return exercises.stream()
                .map(exercise -> new Exercise(exercise.exercise.s, converts(exercise.sets)))
                .collect(Collectors.toList());
    }

    public static List<Set> converts(List<SetO> sets) {
        return sets.stream()
                .map(set -> new Set(
                        set.set.val,
                        set.weight.dbl,
                        set.reps.dbl,
                        set.RIR.dbl,
                        set.rest.seconds,
                        set.comment.s,
                        Optional.ofNullable(set.filePath).map(path -> path.uri).orElse(null)
                )).collect(Collectors.toList());
    }

    public static class WorkoutO {
        public Ddate date;
        public List<ExerciseO> exercises;
    }

    public static class Ddate {
        public String date;
    }

    public static class ExerciseO {
        public Eexercise exercise;
        public List<SetO> sets;
    }

    public static class Eexercise {
        public String s;
    }

    public static class SetO {
        public Ddbl RIR;
        public Eexercise comment;
        public Ddbl reps;
        public Rrest rest;
        public Iint set;
        public Ddbl weight;
        public Uuri filePath;
    }

    public static class Ddbl {
        public double dbl;
    }

    public static class Iint {
        public int val;
    }

    public static class Rrest {
        public int seconds;
    }

    public static class Uuri {
        public String uri;
    }

    private DataRetriever() {
    }

    private static final ArrayList<Workout> WORKOUTS = new ArrayList<>();

    public static void initializeData(Context context) {
        SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(context);
        List<Workout> coolStuff = new ArrayList<>();
        try {
            SQLiteDatabase db = workoutDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from WORKOUT", null);
            if (cursor.moveToFirst()) {
                do {
                    String storedObject = cursor.getString(1);
                    Gson gson = new Gson();
                    WorkoutO workout = gson.fromJson(storedObject, WorkoutO.class);
                    Workout w = convert(workout);
                    coolStuff.add(w);
//                    WORKOUTS.add(workout);
                } while (cursor.moveToNext());
            }
            db.close();
            cursor.close();
            Gson gson = new Gson();
            String s = gson.toJson(coolStuff);
            Files.write(Paths.get("/data/user/0/com.example.workoutbasic/databases/newTypeDb.json"), s.getBytes());
        } catch (SQLiteException | IOException e) {
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
