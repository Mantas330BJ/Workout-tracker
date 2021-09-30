package TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.StringFragment;
import Variables.Str;

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
        StringFragment popup = new StringFragment((Str)textData);
        popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "DatePickFragment");
    }
}
