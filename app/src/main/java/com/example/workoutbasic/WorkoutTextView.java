package com.example.workoutbasic;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Context context;
    private int depth = -1;
    private TextViewData textData; //Used in dialog fragments.
    private Datas infoData; //Used in changing data.      change data -> need dialog

    public WorkoutTextView(Context context, int depth, Datas infoData) { //TODO: create more text views??
        super(context);
        this.context = context;
        this.depth = depth;
        this.infoData = infoData;
    }

    public WorkoutTextView(Context context) {
        super(context);
        this.context = context;
    }


    public void setBaseParams(TextViewData text) {
        textData = text;
        setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        setTextSize(Data.textSize);
        setSingleLine(true);
        setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.border_right_bottom, null));
        setText(text.toString());
    }

    public int getChildIndex(int resourceId) {
        ViewGroup curr = (ViewGroup)getParent();
        ViewGroup parent = (ViewGroup)curr.getParent();
        int parentId = parent.getId();
        while (parentId != resourceId) {
            curr = parent;
            parent = (ViewGroup)curr.getParent();
            parentId = parent.getId();
        }
        return parent.indexOfChild(curr);
    }

    public void setTextEditListener() {
        setOnClickListener((view) -> {
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.show(((FragmentActivity)context).getSupportFragmentManager(), "TextEditPopupFragment");
            ((FragmentActivity)context).getSupportFragmentManager().executePendingTransactions();
            textData.setFragmentInput(popup);
            popup.setParentData(textData);

            ((EditExerciseActivity)context).currentClicked = this; //TODO: add interface
        });
    }

    public void setDatePickListener() { //TODO: add listeners in constructors??
        setOnClickListener((view) -> {
            DatePickPopupFragment popup = new DatePickPopupFragment(); //TODO: add arguments
            popup.show(((FragmentActivity)context).getSupportFragmentManager(), "DatePickPopupFragment");
            ((EditWorkoutActivity)context).currentClicked = this;
            System.out.println(getText());
        });
    }

    public void setParamsAndListener(TextViewData text, int mode) {
        setBaseParams(text);
        switch (mode) {
            case 0:
                setOnClickListener((view) -> {
                    int exerciseIdx = getChildIndex(R.id.exercise_layout);
                    Intent intent = new Intent(context, EditExerciseActivity.class);
                    intent.putExtra(Data.WORKOUT_IDX, ((EditWorkoutActivity)context).workoutIdx);
                    intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
                    context.startActivity(intent);
                });
                break;
            case 1:
                setOnClickListener((view) -> {
                    int workoutIdx = getChildIndex(R.id.table);
                    Intent intent = new Intent(context, EditWorkoutActivity.class);
                    intent.putExtra(Data.WORKOUT_IDX, workoutIdx);
                    context.startActivity(intent);
                });
                break;
            case 2:
                setTextEditListener();
                break;
            case 3:
                setDatePickListener();
        }
    }

    public Datas getInfoData() {
        return infoData;
    }

    public int getDepth() {
        return depth;
    }


    /*
    public Object getParentData() {
        switch (depth) {
            case Data.WORKOUT_DEPTH:
                return (WorkoutData)parentData;
            case Data.EXERCISE_DEPTH:
                return (ExerciseData)parentData;
            case Data.SET_DEPTH:
                return (SetData)parentData;
        }
        return null;
    }

     */
}

