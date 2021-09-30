package ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.CommentEditFragment;
import Interfaces.WorkoutInput;
import Variables.Str;

public class WorkoutCommentView extends WorkoutImageView {
    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            CommentEditFragment popup = new CommentEditFragment((Str)parentData);
            popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TextEditPopupFragment");
        });
    }
}
