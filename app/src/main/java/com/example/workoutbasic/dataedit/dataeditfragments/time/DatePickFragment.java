package com.example.workoutbasic.dataedit.dataeditfragments.time;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.viewmodels.SetViewModel;

import java.time.LocalDate;

public class DatePickFragment extends DialogFragment {
    private DatePicker datePicker;
    private GetterSetter<LocalDate> getterSetter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_pick_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetViewModel viewModel = new ViewModelProvider(requireActivity()).get(SetViewModel.class);
        viewModel.getSelectedLocalDate().observe(requireActivity(), modifier -> {
            getterSetter = modifier;
            createView(view);
        });
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
