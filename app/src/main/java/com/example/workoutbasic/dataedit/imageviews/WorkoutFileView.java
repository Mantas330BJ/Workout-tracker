package com.example.workoutbasic.dataedit.imageviews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.workoutbasic.viewmodels.FileViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutFileView extends WorkoutImageView {
    private String fileUrl;

    public WorkoutFileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            FileViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(FileViewModel.class);
//            viewModel.select((UriPasser) parentData);
        });
    }
}
