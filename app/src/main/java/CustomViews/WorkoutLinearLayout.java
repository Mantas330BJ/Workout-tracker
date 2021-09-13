package CustomViews;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;

import com.example.workoutbasic.R;

public class WorkoutLinearLayout extends LinearLayout {
    public WorkoutLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setParams();
    }

    public WorkoutLinearLayout(Context context) {
        super(context);
        setParams();
    }

    public void setParams() {
        setDividerDrawable(new ColorDrawable(this.getResources().getColor(R.color.black)));
        setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        setMinimumHeight(100);
    }
}
