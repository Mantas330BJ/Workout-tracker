package CustomViews;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;

import Interfaces.Editable;
import Variables.Drt;
import Variables.TextViewData;

@RequiresApi(api = Build.VERSION_CODES.O)


//TODO: allow custom rest increments.
public class WorkoutTimePicker implements Editable {
    private final WorkoutLinearLayout pickerLayout;
    private final NumberPicker minutesPicker;
    private final NumberPicker secondsPicker;

    public WorkoutTimePicker(Context context) {
        pickerLayout = new WorkoutLinearLayout(context);
        minutesPicker = new NumberPicker(context);
        minutesPicker.setMaxValue(59);

        TextView delimiter = new TextView(context);
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

    public void setDuration(int seconds) {
        minutesPicker.setValue(seconds / 60);
        secondsPicker.setValue(seconds % 60);
    }

    public WorkoutLinearLayout getPickerLayout() {
        return pickerLayout;
    }

    public int getMinutes() {
        return minutesPicker.getValue();
    }

    public int getSeconds() {
        return secondsPicker.getValue();
    }

    @Override
    public void displayData(TextViewData parentData) {
        setDuration(((Drt)parentData).getDuration());
    }

    @Override
    public View getView() {
        return pickerLayout;
    }
}
