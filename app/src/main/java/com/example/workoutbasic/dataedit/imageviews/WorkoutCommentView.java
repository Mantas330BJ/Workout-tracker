package com.example.workoutbasic.dataedit.imageviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.variables.TextViewData;
import com.example.workoutbasic.viewmodels.SharedViewModel;

public class WorkoutCommentView extends WorkoutImageView {

    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            SharedViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(SharedViewModel.class);
            viewModel.select((TextViewData) parentData);
            viewModel.getSelected().observe((LifecycleOwner)getContext(), text -> {
                    if (!viewModel.firstTime)
                        viewModel.getSelected().removeObservers((LifecycleOwner) getContext());
                    viewModel.firstTime = !viewModel.firstTime;
                }
            );
            navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment);
        });
    }
}
