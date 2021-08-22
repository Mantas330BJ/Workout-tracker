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
        setTextSize(Data.textSize);
        setSingleLine(true);
        setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.border_right_bottom, null));
        setText(text.toString());
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
}

