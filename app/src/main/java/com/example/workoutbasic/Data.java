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
import Variables.DurationPasser;
import Variables.DatePasser;
import Variables.DoublePasser;
import Variables.IntPasser;
import Variables.StringPasser;
import Variables.UriPasser;

/*
TODO:
 Show active tab in navigation. Look the best ways to initialize fragments.
 Fix empty string parse error
 File changed error.
 Improve database efficiency.
 Fix inconsistencies on empty data.
 Multiple fragment click bug fix.
 Analyze cell line count.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Data {

    public static final String WORKOUT_IDX = "widx";
    public static final String EXERCISE_IDX = "eidx";
    public static final String SET_IDX = "setidx";
    public static final String DEST_WORKOUT_IDX = "destworkoutidx";

    private static final ArrayList<WorkoutData> workoutDatas = new ArrayList<>();

    public static SetData createEmptySet() {
        return new SetData(new IntPasser(1), new DoublePasser(0), new DoublePasser(0), new DoublePasser(0), new DurationPasser(0), new StringPasser(""), new UriPasser(null));
    }

    public static ExerciseData createEmptyExercise() {
        ArrayList<SetData> setDatas = new ArrayList<>();
        setDatas.add(createEmptySet());
        return new ExerciseData(new StringPasser("No exercise"), setDatas);
    }

    public static WorkoutData createEmptyWorkout() {
        ArrayList<ExerciseData> exerciseDatas = new ArrayList<>();
        exerciseDatas.add(createEmptyExercise());
        return new WorkoutData(new DatePasser(new Date()), exerciseDatas);
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
        return new WorkoutData(new DatePasser(new Date()), exerciseDatasDestination);
    }

    public static ExerciseData copyExercise(ExerciseData exerciseData) {
        String exercise = exerciseData.getExercise().toString();
        ArrayList<SetData> setDatasDestination = new ArrayList<>();
        ArrayList<SetData> setDatasSource = exerciseData.getSets();
        for (SetData setData : setDatasSource) {
            setDatasDestination.add(copySet(setData));
        }
        return new ExerciseData(new StringPasser(exercise), setDatasDestination);
    }

    public static SetData copySet(SetData setData) {
        int set = setData.getSet().getVal();
        double weight = setData.getWeight().getDouble();
        double reps = setData.getReps().getDouble();
        double RIR = setData.getRIR().getDouble();
        int rest = setData.getRest().getDuration();
        //String comment = setData.getComment().toString();
        return new SetData(new IntPasser(set), new DoublePasser(weight), new DoublePasser(reps), new DoublePasser(RIR), new DurationPasser(rest), new StringPasser(""), new UriPasser(null));
    }

    public static ArrayList<WorkoutData> getWorkoutDatas() {
        return workoutDatas;
    }
}
