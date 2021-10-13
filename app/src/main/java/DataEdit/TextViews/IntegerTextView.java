package DataEdit.TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEdit.DataEditFragments.Numbers.IntegerFragment;

@RequiresApi(api = Build.VERSION_CODES.O)

public class IntegerTextView extends WorkoutTextView {

    public IntegerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        new IntegerFragment().show(((FragmentActivity)getContext()).getSupportFragmentManager(), "IntegerFragment");
    }
}
