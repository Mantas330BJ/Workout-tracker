package DataEdit.DataEditFragments.Time;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.SetFragments;

//TODO: add workout time picker layout to xml
public class ChooseRestFragment extends SetFragments { //Float, Int
    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_rest_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void createView(View view) {
        minutesPicker = view.findViewById(R.id.minutes_picker);
        minutesPicker.setMaxValue(59);

        secondsPicker = view.findViewById(R.id.seconds_picker);
        secondsPicker.setMaxValue(59);

        int seconds = (int)setData.getGetter(methodIndex);
        minutesPicker.setValue(seconds / 60);
        secondsPicker.setValue(seconds % 60);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        setData.getSetter(minutesPicker.getValue() * 60 + secondsPicker.getValue(), methodIndex);
        super.onDismiss(dialog);
    }

}
