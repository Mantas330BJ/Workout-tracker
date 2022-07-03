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
import androidx.lifecycle.LiveData;

import com.example.workoutbasic.R;
import com.example.workoutbasic.dataedit.dataeditfragments.AbstractDialogFragment;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.viewmodels.SetViewModel;

import java.time.LocalDate;

public class DatePickFragment extends AbstractDialogFragment<LocalDate> {
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_pick_fragment, container, false);
    }

    @Override
    protected LiveData<GetterSetter<LocalDate>> getLiveData(SetViewModel viewModel) {
        return viewModel.getSelectedLocalDate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void createView(View view) {
        datePicker = view.findViewById(R.id.date_picker);

        LocalDate workoutDate = getterSetter.get();
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
        getterSetter.set(LocalDate.of(year, month, day));

        super.onDismiss(dialog);
    }
}
