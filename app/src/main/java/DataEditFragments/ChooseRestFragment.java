package DataEditFragments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;

import java.util.Objects;

import Variables.Drt;
import Variables.Str;

//TODO: add workout time picker layout to xml
public class ChooseRestFragment extends TextFragments { //Float, Int
    private Drt parentData;
    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;

    public ChooseRestFragment(Drt parentData) {
        this.parentData = parentData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_rest_fragment, container, false);
        minutesPicker = view.findViewById(R.id.minutes_picker);
        minutesPicker.setMaxValue(59);

        secondsPicker = view.findViewById(R.id.seconds_picker);
        secondsPicker.setMaxValue(59);

        int seconds = parentData.getDuration();
        minutesPicker.setValue(seconds / 60);
        secondsPicker.setValue(seconds % 60);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setSeconds(minutesPicker.getValue() * 60 + secondsPicker.getValue());
        getOnInputListener().sendInput(parentData.toString());
    }

}
