package DataEdit.ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.TextFragments;
import Interfaces.Variables.TextViewData;
import Utils.FragmentMethods;
import ViewModels.SharedViewModel;

public class WorkoutCommentView extends WorkoutImageView {

    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    boolean firstTime = true;
    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            SharedViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(SharedViewModel.class);
            viewModel.select((TextViewData) parentData);
            viewModel.getSelected().observe((LifecycleOwner)getContext(), text -> {
                    if (!firstTime)
                        viewModel.getSelected().removeObservers((LifecycleOwner) getContext());
                    firstTime = !firstTime;
                }
            );
            navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment);
        });
    }
}
