package TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.IntegerFragment;
import Variables.Int;

@RequiresApi(api = Build.VERSION_CODES.O)

public class IntegerTextView extends WorkoutTextView {

    public IntegerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        IntegerFragment popup = new IntegerFragment((Int)textData);
        popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "IntegerFragment");
    }
}
