package DataEditFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import Variables.Dte;
import Variables.TextViewData;

public class DatePickFragment extends TextFragments {
    private Dte parentData;
    private DatePicker datePicker;

    public DatePickFragment(Dte parentData) {
        this.parentData = parentData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_pick_fragment, container, false);
        datePicker = view.findViewById(R.id.date_picker); //Called from derived variables with edit text.
        Calendar cal = Calendar.getInstance();
        cal.setTime(parentData.getDate());
        return view;
    }


    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        Calendar cal = Calendar.getInstance();
        int year = datePicker.getYear(), month = datePicker.getMonth(), day = datePicker.getDayOfMonth();
        cal.set(year, month, day);
        String updatedInfo = new Dte(cal.getTime()).toString();
        getOnInputListener().sendInput(updatedInfo); //Send strings??
    }
}
