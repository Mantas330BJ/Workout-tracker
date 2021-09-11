package com.example.workoutbasic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class WorkoutImageView extends androidx.appcompat.widget.AppCompatImageView implements WorkoutInput {
    TextViewData parentData;

    public WorkoutImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setText(CharSequence text) {
        //Called after exiting from fragment to set text.
    }

    public void setParentData(TextViewData parentData) {
        this.parentData = parentData;
    }

    public void setTextEditListener() {
        setOnClickListener((view) -> {
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TextEditPopupFragment");
            ((FragmentActivity)getContext()).getSupportFragmentManager().executePendingTransactions();
            parentData.setFragmentInput(popup);
            popup.setParentData(parentData);
            ((OnInputListener)getContext()).setCurrentClicked(this);
        });
    }
}
