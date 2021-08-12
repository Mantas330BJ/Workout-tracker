package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.time.Duration;
@RequiresApi(api = Build.VERSION_CODES.O)


//TODO: allow custom rest increments.
public class WorkoutTimePicker implements Editable {
    private LinearLayout pickerLayout;
    private NumberPicker minutesPicker;
    private TextView delimiter;
    private NumberPicker secondsPicker;

    private Duration duration;

    public WorkoutTimePicker(Context context) {
        pickerLayout = new LinearLayout(context);
        minutesPicker = new NumberPicker(context);
        minutesPicker.setMaxValue(59);

        delimiter = new TextView(context);
        secondsPicker = new NumberPicker(context);
        secondsPicker.setMaxValue(59);

        pickerLayout.setOrientation(LinearLayout.HORIZONTAL);
        pickerLayout.addView(minutesPicker);
        pickerLayout.addView(delimiter);

        delimiter.setText(R.string.delimiter);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) delimiter.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        delimiter.setLayoutParams(layoutParams);

        pickerLayout.addView(secondsPicker);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
        int seconds = (int)duration.getSeconds();
        minutesPicker.setValue(seconds / 60);
        secondsPicker.setValue(seconds % 60);
    }

    public LinearLayout getPickerLayout() {
        return pickerLayout;
    }

    public int getMinutes() {
        return minutesPicker.getValue();
    }

    public int getSeconds() {
        return secondsPicker.getValue();
    }

}
