package DataEdit.ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
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

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment);
            FragmentManager fm = ((FragmentActivity)getContext()).getSupportFragmentManager();
            NavHostFragment navHostFragment = (NavHostFragment)fm.findFragmentById(R.id.nav_host_fragment);
            navHostFragment.getChildFragmentManager().executePendingTransactions();

            TextFragments calledFragment = (TextFragments) FragmentMethods.getParentFragment(getContext(), 1);
            SharedViewModel viewModel = new ViewModelProvider(calledFragment).get(SharedViewModel.class);
            viewModel.select((TextViewData) parentData);
        });
    }
}
