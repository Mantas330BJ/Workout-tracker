package DataEdit.ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.Text.CommentEditFragment;
import Interfaces.TextViewData;
import ViewModels.SharedViewModel;

public class WorkoutCommentView extends WorkoutImageView {

    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> navController.navigate(R.id.action_editExerciseFragment_to_commentEditFragment));
    }
}
