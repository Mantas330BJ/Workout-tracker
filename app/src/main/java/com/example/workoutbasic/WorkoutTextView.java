package com.example.workoutbasic;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView {
    Context context;

    public WorkoutTextView(Context context) {
        super(context);
        this.context = context;
    }


    public void setBaseParams(String text) {
        setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        setTextSize(Data.textSize);
        setSingleLine(true);
        setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.border_right_bottom, null));
        setText(text);
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
            Bundle bundle = new Bundle();
            bundle.putString(TextEditPopupFragment.EXTRA_STRING, getText().toString());
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.setArguments(bundle);
            popup.show(((FragmentActivity)context).getSupportFragmentManager(), "TextEditPopupFragment");
            ((EditExerciseActivity)context).currentClicked = this;
        });
    }

    public void setParamsAndListener(String text, int mode) {
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
        }
    }
}

