package DataEdit.TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.R;

@RequiresApi(api = Build.VERSION_CODES.O)

public class ExerciseTextView extends WorkoutTextView {

    public ExerciseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        navController.navigate(R.id.action_exercisesFragment_to_exerciseStatsFragment);
    }
}
