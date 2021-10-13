package DataEdit.TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEdit.DataEditFragments.Text.StringFragment;
import Variables.StringPasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class StringTextView extends WorkoutTextView {

    public StringTextView(Context context) {
        super(context);
    }

    public StringTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        calledFragment = new StringFragment();
        calledFragment.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "StringFragment");
    }
}
