package DataEdit.TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEdit.DataEditFragments.Time.ChooseRestFragment;
import Variables.DurationPasser;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ChooseRestTextView extends WorkoutTextView {

    public ChooseRestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        calledFragment = new ChooseRestFragment();
        calledFragment.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "ChooseRestFragment");
    }
}
