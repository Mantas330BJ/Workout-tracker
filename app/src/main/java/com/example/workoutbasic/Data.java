package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

/*
TODO:
 Implement listeners for button actions
 Think about sending input.
 Freeze header.
 Multiple fragment click bug fix.
 Analyze cell line count.
 Call context less often
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Data {
    public static final int[] columnWidths = {350, 350, 200, 200, 200, 200, 1000}; //TODO: automated widths

    public static final int WORKOUT = 0;
    public static final int EXERCISE = 1;
    public static final int EDIT = 2;
    //public static final int PARAMETER_DEPTH = 4;

    //Modes:
    // 0 - Add workout listener
    // 1 - Add exercise listener
    // 2 - Edit cell
    // 3 - Edit date

    public static final String WORKOUT_IDX = "widx";
    public static final String EXERCISE_IDX = "eidx";


    public static final String[] columnNames = {"Date",
        "Exercise",
        "Set",
        "Reps",
        "RIR",
        "Rest",
        "Comments"};

    public static final float textSize = 20;

    private static ArrayList<WorkoutData> workoutDatas;

    public static void initializeData() {
        workoutDatas = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            ArrayList<ExerciseData> exerciseDatas = new ArrayList<>();
            for (int j = 0; j < 3; ++j) {
                ArrayList<SetData> setDatas = new ArrayList<>();
                for (int k = 0; k < 5; ++k) {
                    addSetData(setDatas, k + 1, (float)j / (k + 1)
                    , i, Duration.ofMinutes(2 * k + 1), "Goot.");
                }
                addExerciseData(exerciseDatas, "Broadas" + i + " " + j, setDatas);
            }
            addWorkoutData(workoutDatas, new Date(), exerciseDatas);
        }
    }


    public static void addWorkoutData(ArrayList<WorkoutData> workoutDatas, Date date, ArrayList<ExerciseData> exerciseDatas) {
        WorkoutData workoutData = new WorkoutData(new Dte(date), exerciseDatas);
        workoutDatas.add(workoutData);
    }

    public static WorkoutData copyWorkout(WorkoutData workoutData) {
        Date date = workoutData.getDate().getDate();
        ArrayList<ExerciseData> exerciseDatasDestination = new ArrayList<>();
        ArrayList<ExerciseData> exerciseDatasSource = workoutData.getExercises();
        for (ExerciseData exerciseData : exerciseDatasSource) {
            exerciseDatasDestination.add(copyExercise(exerciseData));
        }
        return new WorkoutData(new Dte(date), exerciseDatasDestination);
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

    public static void addExerciseData(ArrayList<ExerciseData> exerciseDatas, String exercise, ArrayList<SetData> setDatas) {
        ExerciseData exerciseData = new ExerciseData(new Str(exercise), setDatas);
        exerciseDatas.add(exerciseData);
    }

    public static SetData copySet(SetData setData) {
        int set = setData.getSet().getVal();
        float weight = setData.getWeight().getFlt();
        float RIR = setData.getRIR().getFlt();
        Duration rest = Duration.ofSeconds(setData.getRest().getDuration().getSeconds());
        String comment = setData.getComment().toString();
        return new SetData(new Int(set), new Flt(weight), new Flt(RIR), new Drt(rest), new Str(comment));
    }


    public static void addSetData(ArrayList<SetData> setDatas, int set, float weight, float RIR,
                                  Duration rest, String comment) {
        SetData setData = new SetData(new Int(set), new Flt(weight), new Flt(RIR), new Drt(rest), new Str(comment));
        setDatas.add(setData);
    }


    public static LinearLayout createColumnNames(Context context, int i) {
        LinearLayout row = new LinearLayout(context);
        while (i < Data.columnNames.length) {
            String columnName = Data.columnNames[i];
            WorkoutTextView columnRowText = new WorkoutTextView(context);
            columnRowText.setWidth(Data.columnWidths[i]);
            columnRowText.setBaseParams(new Str(columnName));

            columnRowText.setTextAppearance(context, android.R.style.TextAppearance_Large);
            row.addView(columnRowText);
            ++i;
        }
        return row;
    }

    public static ArrayList<WorkoutData> getWorkoutDatas() {
        return workoutDatas;
    }
}
