package com.example.workoutbasic.dataedit.dataeditfragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.interfaces.variables.TextViewData;
import com.example.workoutbasic.viewmodels.SharedViewModel;

public abstract class TextFragments extends DialogFragment {
    protected TextViewData parentData;
    protected SharedViewModel viewModel;

    public abstract void createView(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelected().observe(requireActivity(), data -> {
            parentData = data;
            createView(view);
        });
    }

    public void dismissWithoutSettingText(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        viewModel.select(parentData);
    }
}
