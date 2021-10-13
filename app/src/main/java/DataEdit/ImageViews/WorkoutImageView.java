package DataEdit.ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import DataEdit.DataEditFragments.TextFragments;
import Interfaces.InfoData;
import Interfaces.WorkoutInput;

abstract public class WorkoutImageView extends androidx.appcompat.widget.AppCompatImageView implements WorkoutInput {
    protected InfoData parentData;
    protected TextFragments calledFragment;

    public WorkoutImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setParentData(InfoData parentData) {
        this.parentData = parentData;
    }
}
