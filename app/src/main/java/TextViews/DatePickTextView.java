package TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.DatePickFragment;
import Variables.DatePasser;
@RequiresApi(api = Build.VERSION_CODES.O)

public class DatePickTextView extends WorkoutTextView {

    public DatePickTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        DatePickFragment popup = new DatePickFragment((DatePasser)textData);
        popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "DatePickFragment");
    }
}
