package com.example.workoutbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/*
TODO:
 Return long click listener to exercise name.
 Look why workouts do not get removed (probably issue of updating recycler).
 Remove notifyDataSetChanged().
 Add scrolls based on click location.
 Restrict more when copying exercise.
 Fix inconsistencies on empty data.
 Think about sending input.
 Multiple fragment click bug fix.
 Analyze cell line count.
 Call context less often
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Data {

    public static final String WORKOUT_IDX = "widx";
    public static final String EXERCISE_IDX = "eidx";
    public static final String SET_IDX = "setidx";

    public static final String METHOD = "method";

    public static final float textSize = 20;

    private static ArrayList<WorkoutData> workoutDatas = new ArrayList<>();

    public static SetData createEmptySet() {
        return new SetData(new Int(1), new Flt(0), new Flt(0), new Flt(0), new Drt(0), new Str(""));
    }

    public static ExerciseData createEmptyExercise() {
        ArrayList<SetData> setDatas = new ArrayList<>();
        setDatas.add(createEmptySet());
        return new ExerciseData(new Str("No exercise"), setDatas);
    }

    public static WorkoutData createEmptyWorkout() {
        ArrayList<ExerciseData> exerciseDatas = new ArrayList<>();
        exerciseDatas.add(createEmptyExercise());
        return new WorkoutData(new Dte(new Date()), exerciseDatas);
    }

    public static void initializeData(Context context) {
        SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(context);
        try {
            SQLiteDatabase db = workoutDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from WORKOUT", null);
            if (cursor.moveToFirst()) {
                do {
                    String storedObject = cursor.getString(1);
                    Gson gson = new Gson();
                    WorkoutData workoutData = gson.fromJson(storedObject, WorkoutData.class);
                    workoutDatas.add(workoutData);
                } while (cursor.moveToNext());
            }
            db.close();
            cursor.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(context, context.getString(R.string.database_unavailable), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void addWorkoutData(SQLiteDatabase db, WorkoutData workoutData) {
        ContentValues workoutValues = new ContentValues();
        Gson gson =  new Gson();
        String storedObject = gson.toJson(workoutData, WorkoutData.class);
        workoutValues.put("DATA", storedObject);
        db.insert("WORKOUT", null, workoutValues);
    }

    public static WorkoutData copyWorkout(WorkoutData workoutData, int setIncrement) {
        //Date date = workoutData.getDate().getDate();
        ArrayList<ExerciseData> exerciseDatasDestination = new ArrayList<>();
        ArrayList<ExerciseData> exerciseDatasSource = workoutData.getExercises();
        for (ExerciseData exerciseData : exerciseDatasSource) {
            exerciseDatasDestination.add(copyExercise(exerciseData, setIncrement));
        }
        return new WorkoutData(new Dte(new Date()), exerciseDatasDestination);
    }

    public static ExerciseData copyExercise(ExerciseData exerciseData, int setIncrement) {
        String exercise = exerciseData.getExercise().toString();
        ArrayList<SetData> setDatasDestination = new ArrayList<>();
        ArrayList<SetData> setDatasSource = exerciseData.getSets();
        for (SetData setData : setDatasSource) {
            setDatasDestination.add(copySet(setData, setIncrement));
        }
        return new ExerciseData(new Str(exercise), setDatasDestination);
    }

    public static void addExerciseData(ArrayList<ExerciseData> exerciseDatas, String exercise, ArrayList<SetData> setDatas) {
        ExerciseData exerciseData = new ExerciseData(new Str(exercise), setDatas);
        exerciseDatas.add(exerciseData);
    }

    public static SetData copySet(SetData setData, int setIncrement) {
        int set = setData.getSet().getVal() + setIncrement;
        float weight = setData.getWeight().getFlt();
        float reps = setData.getReps().getFlt();
        float RIR = setData.getRIR().getFlt();
        int rest = setData.getRest().getDuration();
        //String comment = setData.getComment().toString();
        return new SetData(new Int(set), new Flt(weight), new Flt(reps), new Flt(RIR), new Drt(rest), new Str(""));
    }


    public static void addSetData(ArrayList<SetData> setDatas, int set, float weight, float reps, float RIR,
                                  int rest, String comment) {
        SetData setData = new SetData(new Int(set), new Flt(weight), new Flt(reps), new Flt(RIR), new Drt(rest), new Str(comment));
        setDatas.add(setData);
    }

    public static ArrayList<WorkoutData> getWorkoutDatas() {
        return workoutDatas;
    }
}
