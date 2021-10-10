package ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import DataEditFragments.Text.CommentEditFragment;
import Variables.StringPasser;

public class WorkoutCommentView extends WorkoutImageView {
    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            CommentEditFragment popup = new CommentEditFragment((StringPasser)parentData);
            popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TextEditPopupFragment");
        });
    }
}
