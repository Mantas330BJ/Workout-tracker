package com.example.workoutbasic;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;

abstract class Datas {
    public abstract WorkoutLinearLayout getLayout(Context context, boolean addExercise);
}
