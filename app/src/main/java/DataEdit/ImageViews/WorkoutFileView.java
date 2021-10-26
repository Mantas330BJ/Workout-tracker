package DataEdit.ImageViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import Pages.Sets.EditExerciseFragment;
import Utils.FragmentMethods;
import Variables.UriPasser;
import ViewModels.FileViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutFileView extends WorkoutImageView {


    public WorkoutFileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //TODO: analyze removals
    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            FileViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(FileViewModel.class);
            viewModel.select((UriPasser) parentData);
        });
    }
}
