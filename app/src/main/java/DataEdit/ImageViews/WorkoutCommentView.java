package DataEdit.ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import Activities.DatabaseActivity;
import DataEdit.DataEditFragments.Text.CommentEditFragment;
import Interfaces.TextViewData;
import Variables.StringPasser;
import ViewModels.SharedViewModel;

public class WorkoutCommentView extends WorkoutImageView {

    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void createFragment() {
        calledFragment = new CommentEditFragment();
        calledFragment.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "CommentEditFragment");
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            createFragment();
            ((FragmentActivity)getContext()).getSupportFragmentManager().executePendingTransactions();

            SharedViewModel viewModel = new ViewModelProvider(calledFragment).get(SharedViewModel.class);
            viewModel.select((TextViewData) parentData);
        });
    }
}
