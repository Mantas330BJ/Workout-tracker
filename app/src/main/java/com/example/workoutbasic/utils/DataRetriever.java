package com.example.workoutbasic.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.models.Set;
import com.example.workoutbasic.models.Workout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH));
        }
    }

    private DataRetriever() {
    }

    private static final ArrayList<Workout> WORKOUTS = new ArrayList<>();

    public static void initializeData(Context context) {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
            String strings = String.join("\n", Files.readAllLines(Paths.get("/data/user/0/com.example.workoutbasic/databases/newTypeDb.json")));
            Type listType = new TypeToken<ArrayList<Workout>>(){}.getType();

            WORKOUTS.addAll(gsonBuilder.create().fromJson(strings, listType));
        } catch (IOException e) {
            e.printStackTrace();
        }


//        SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(context);
//        List<Workout> coolStuff = new ArrayList<>();
//        try {
//
//            SQLiteDatabase db = workoutDatabaseHelper.getReadableDatabase();
//            Cursor cursor = db.rawQuery("select * from WORKOUT", null);
//            if (cursor.moveToFirst()) {
//                do {
//                    String storedObject = cursor.getString(1);
//                    Gson gson = new Gson();
//                    WorkoutO workout = gson.fromJson(storedObject, WorkoutO.class);
//                    Workout w = convert(workout);
//                    coolStuff.add(w);
////                    WORKOUTS.add(workout);
//                } while (cursor.moveToNext());
//            }
//            db.close();
//            cursor.close();
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
//            String s = gsonBuilder.create().toJson(coolStuff);
//            Files.write(Paths.get("/data/user/0/com.example.workoutbasic/databases/newTypeDb.json"), s.getBytes());
//        } catch (SQLiteException | IOException e) {
//            Toast toast = Toast.makeText(context, context.getString(R.string.database_unavailable), Toast.LENGTH_SHORT);
//            toast.show();
//        }
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
