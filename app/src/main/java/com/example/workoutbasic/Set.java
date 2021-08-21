package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Set {
    private int size;
    private final LinearLayout layout;
    private final SetData setData;

    Set(SetData setData, Context context, boolean shouldEdit) {
        this.setData = setData;
        layout = new LinearLayout(context);
        addDetails(context, shouldEdit);
    }

    public void addDetails(Context context, boolean shouldEdit) {

        TextViewData[] values = new TextViewData[]{setData.getSet(), setData.getWeight(), setData.getReps(),
                setData.getRIR(), setData.getRest(), setData.getComment()};

        int weightIdx = 2;
        for (TextViewData value : values) {
            WorkoutTextView textView;
            textView = new WorkoutTextView(context);
            textView.setWidth(Data.columnWidths[weightIdx]);
            textView.setBaseParams(value);
            if (shouldEdit && weightIdx != 2) {
                textView.setTextEditListener();
            }
            layout.addView(textView);
            textView.measure(0, 0);
            size = textView.getMeasuredHeight(); //TODO: call once or something
            ++weightIdx;
        }


    }

    public int getSize() {
        return size;
    }

    public SetData getSetData() {
        return setData;
    }

    public LinearLayout getLayout() {
        return layout;
    }
}
