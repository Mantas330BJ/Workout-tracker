package DataEdit.TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.Time.DatePickFragment;
import Variables.DatePasser;
@RequiresApi(api = Build.VERSION_CODES.O)

public class DatePickTextView extends WorkoutTextView {

    public DatePickTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        navController.navigate(R.id.action_editWorkoutFragment_to_datePickFragment);
    }
}
