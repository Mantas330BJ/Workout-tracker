package DataEdit.ImageViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import Pages.Sets.EditExerciseFragment;
import Utils.FragmentMethods;
import Variables.UriPasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutFileView extends WorkoutImageView {


    public WorkoutFileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {

        EditExerciseFragment parentFragment = (EditExerciseFragment)FragmentMethods.getParentFragment(getContext(), 0);
        setOnClickListener(view -> parentFragment.showPermission((UriPasser) parentData));
    }
}
