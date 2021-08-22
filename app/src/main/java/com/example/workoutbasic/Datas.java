package com.example.workoutbasic;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;

abstract class Datas {
    public abstract boolean emptyChildren();
    public abstract LinearLayout getLayout(Context context, boolean addExercise);
}
