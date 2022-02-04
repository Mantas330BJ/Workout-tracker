package com.example.workoutbasic.dataedit.dataeditfragments.time;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;
import com.example.workoutbasic.dataedit.dataeditfragments.TextFragments;

import java.time.LocalDate;

public class DatePickFragment extends TextFragments {
    private DatePicker datePicker;
    private LocalDate workoutDate; //TODO: get somehow

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_pick_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void createView(View view) {
        datePicker = view.findViewById(R.id.date_picker);

        int year = workoutDate.getYear();
        int month = workoutDate.getMonthValue();//TODO: look index
        int day = workoutDate.getDayOfMonth();
        datePicker.updateDate(year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        LocalDate workoutDate = LocalDate.of(year, month, day); //TODO: pass somehow

        super.onDismiss(dialog);
    }
}
