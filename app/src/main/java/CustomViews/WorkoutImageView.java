package CustomViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import Interfaces.InfoData;
import Interfaces.WorkoutInput;

abstract public class WorkoutImageView extends androidx.appcompat.widget.AppCompatImageView implements WorkoutInput {
    InfoData parentData;

    public WorkoutImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setText(CharSequence text) {
        System.out.println("This is activated I guess");
        //Called after exiting from fragment to set text.
    }

    public void setParentData(InfoData parentData) {
        this.parentData = parentData;
    }

    abstract public void setTextEditListener();
}
