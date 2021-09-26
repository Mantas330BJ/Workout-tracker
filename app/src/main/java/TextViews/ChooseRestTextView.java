package TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.ChooseRestFragment;
import Variables.Drt;
@RequiresApi(api = Build.VERSION_CODES.O)

public class ChooseRestTextView extends WorkoutTextView {

    public ChooseRestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void createFragment() {
        ChooseRestFragment popup = new ChooseRestFragment((Drt)textData);
        popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "ChooseRestFragment");
    }
}
