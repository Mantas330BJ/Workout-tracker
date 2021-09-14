package CustomViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.workoutbasic.OnInputListener;
import Fragments.TextEditPopupFragment;

import Interfaces.WorkoutInput;
import Variables.TextViewData;

abstract public class WorkoutImageView extends androidx.appcompat.widget.AppCompatImageView implements WorkoutInput {
    TextViewData parentData;

    public WorkoutImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setText(CharSequence text) {
        //Called after exiting from fragment to set text.
    }

    public void setParentData(TextViewData parentData) {
        this.parentData = parentData;
    }

    abstract public void setTextEditListener();
}
