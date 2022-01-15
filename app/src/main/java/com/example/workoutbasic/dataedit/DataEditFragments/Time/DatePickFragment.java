package com.example.workoutbasic.dataedit.DataEditFragments.Time;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import java.util.Calendar;

import com.example.workoutbasic.dataedit.DataEditFragments.TextFragments;
import com.example.workoutbasic.variables.DatePasser;

public class DatePickFragment extends TextFragments {
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_pick_fragment, container, false);
    }

    @Override
    public void createView(View view) {
        datePicker = view.findViewById(R.id.date_picker);

        Calendar cal = Calendar.getInstance();
        cal.setTime(((DatePasser)parentData).getDate());
        int year = cal.get(Calendar.YEAR), month = cal.get(Calendar.MONTH), day = cal.get(Calendar.DATE);
        datePicker.updateDate(year, month, day);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        Calendar cal = Calendar.getInstance();
        int year = datePicker.getYear(), month = datePicker.getMonth(), day = datePicker.getDayOfMonth();
        cal.set(year, month, day);
        ((DatePasser)parentData).setDate(cal.getTime());
        super.onDismiss(dialog);
    }
}
