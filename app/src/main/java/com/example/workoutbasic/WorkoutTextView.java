package com.example.workoutbasic;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.InputType;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView {
    private final Context context;
    private TextViewData textData; //Used in dialog fragments.

    public WorkoutTextView(Context context) {
        super(context);
        this.context = context;
    }


    public void setBaseParams(TextViewData text) {
        textData = text;
        //setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
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

            ((OnInputListener)context).setCurrentClicked(this);
        });
    }

    public void setParamsAndListener(TextViewData text, int mode) {
        setBaseParams(text);
        textData.setListener(() -> setText(textData.toString()), mode);

        switch (mode) {
            case Data.EXERCISE:
                setOnClickListener((view) -> {
                    int exerciseIdx = getChildIndex(R.id.exercise_layout);
                    Intent intent = new Intent(context, EditExerciseActivity.class);
                    intent.putExtra(Data.WORKOUT_IDX, ((EditWorkoutActivity)context).workoutIdx);
                    intent.putExtra(Data.EXERCISE_IDX, exerciseIdx);
                    context.startActivity(intent);
                });
                break;
            case Data.EDIT:
                setTextEditListener();
        }
    }
}

