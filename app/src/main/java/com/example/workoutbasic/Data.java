package com.example.workoutbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

import Datas.ExerciseData;
import Datas.SetData;
import Datas.WorkoutData;
import Variables.Drt;
import Variables.Dte;
import Variables.Flt;
import Variables.Int;
import Variables.Str;
import Variables.wUri;

/*
TODO:
 File changed error.
 Add URI to setData.
 Improve database efficiency.
 Clean code.
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
        return new SetData(new Int(1), new Flt(0), new Flt(0), new Flt(0), new Drt(0), new Str(""), new wUri(null));
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
        Gson gson = new Gson();
        String storedObject = gson.toJson(workoutData, WorkoutData.class);
        System.out.println(storedObject);
        workoutValues.put("DATA", storedObject);
        db.insert("WORKOUT", null, workoutValues);
    }

    public static WorkoutData copyWorkout(WorkoutData workoutData) {
        //Date date = workoutData.getDate().getDate();
        ArrayList<ExerciseData> exerciseDatasDestination = new ArrayList<>();
        ArrayList<ExerciseData> exerciseDatasSource = workoutData.getExercises();
        for (ExerciseData exerciseData : exerciseDatasSource) {
            exerciseDatasDestination.add(copyExercise(exerciseData));
        }
        return new WorkoutData(new Dte(new Date()), exerciseDatasDestination);
    }

    public static ExerciseData copyExercise(ExerciseData exerciseData) {
        String exercise = exerciseData.getExercise().toString();
        ArrayList<SetData> setDatasDestination = new ArrayList<>();
        ArrayList<SetData> setDatasSource = exerciseData.getSets();
        for (SetData setData : setDatasSource) {
            setDatasDestination.add(copySet(setData));
        }
        return new ExerciseData(new Str(exercise), setDatasDestination);
    }

    public static SetData copySet(SetData setData) {
        int set = setData.getSet().getVal();
        float weight = setData.getWeight().getFlt();
        float reps = setData.getReps().getFlt();
        float RIR = setData.getRIR().getFlt();
        int rest = setData.getRest().getDuration();
        //String comment = setData.getComment().toString();
        return new SetData(new Int(set), new Flt(weight), new Flt(reps), new Flt(RIR), new Drt(rest), new Str(""), new wUri(null));
    }

    public static ArrayList<WorkoutData> getWorkoutDatas() {
        return workoutDatas;
    }
}
