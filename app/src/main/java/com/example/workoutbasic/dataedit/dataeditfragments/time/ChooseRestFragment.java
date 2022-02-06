package com.example.workoutbasic.dataedit.dataeditfragments.time;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.viewmodels.SetViewModel;

public class ChooseRestFragment extends DialogFragment {
    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;
    private GetterSetter<Integer> getterSetter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_rest_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetViewModel viewModel = new ViewModelProvider(requireActivity()).get(SetViewModel.class);
        viewModel.getSelectedInteger().observe(requireActivity(), modifier -> {
            getterSetter = modifier;
            createView(view);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void createView(View view) {
        minutesPicker = view.findViewById(R.id.minutes_picker);
        minutesPicker.setMaxValue(59);

        secondsPicker = view.findViewById(R.id.seconds_picker);
        secondsPicker.setMaxValue(59);

        int restSeconds = getterSetter.get();
        minutesPicker.setValue(restSeconds / 60);
        secondsPicker.setValue(restSeconds % 60);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        getterSetter.set(minutesPicker.getValue() * 60 + secondsPicker.getValue());
        super.onDismiss(dialog);
    }

}
