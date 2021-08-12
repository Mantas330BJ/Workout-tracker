package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Set {
    private int size;
    private int depth = Data.SET_DEPTH;
    private LinearLayout layout;
    private SetData setData;


    Set(SetData setData, Context context, int mode) {
        this.setData = setData;
        layout = new LinearLayout(context);
        addDetails(context, mode);
    }

    public void addDetails(Context context, int mode) {
        TextViewData[] values = new TextViewData[]{setData.getSet(), setData.getWeight(),
                setData.getRIR(), setData.getRest(), setData.getComment()};

        int weightIdx = 2;
        System.out.println("Mode " + mode);
        for (TextViewData value : values) {
            WorkoutTextView textView;
            textView = new WorkoutTextView(context);
            textView.setWidth(Data.columnWidths[weightIdx++]);
            textView.setParamsAndListener(value, mode);
            layout.addView(textView);
            textView.measure(0, 0);
            size = textView.getMeasuredHeight(); //TODO: call once or something
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
