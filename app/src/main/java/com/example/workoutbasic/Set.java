package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.Duration;
@RequiresApi(api = Build.VERSION_CODES.O)

public class Set {
    private int set;
    private float weight;
    private float RIR;
    private Duration rest;
    private String comment;
    private LinearLayout layout;
    private int size;

    Set(int set, float weight, float RIR, Duration rest, String comment, Context context) {
        this.set = set;
        this.weight = weight;
        this.RIR = RIR;
        this.rest = rest;
        this.comment = comment;
        layout = new LinearLayout(context);
        addDetails(context);
    }

    public void addDetails(Context context) {
        String[] values = new String[]{Integer.toString(set), Data.getStringFloat(weight),
                Data.getStringFloat(RIR), Data.getStringDuration(rest), comment};

        int weightIdx = 2;
        for (String value : values) {
            TextView textView = new TextView(context);
            textView.setWidth(Data.columnWidths[weightIdx++]);
            Data.addText(textView, value, context);
            layout.addView(textView);
            textView.measure(0, 0);
            size = textView.getMeasuredHeight(); //TODO: call once or something
        }
    }

    public int getSet() {
        return set;
    }

    public float getWeight() {
        return weight;
    }

    public float getRIR() {
        return RIR;
    }

    public Duration getRest() {
        return rest;
    }

    public String getComment() {
        return comment;
    }

    public int getSize() {
        return size;
    }

    public LinearLayout getLayout() {
        return layout;
    }
}
