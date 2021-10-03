package TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.FloatFragment;
import Variables.DoublePasser;

@RequiresApi(api = Build.VERSION_CODES.O)

public class FloatTextView extends WorkoutTextView {

    public FloatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        FloatFragment popup = new FloatFragment((DoublePasser)textData);
        popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "FloatFragment");
    }
}
