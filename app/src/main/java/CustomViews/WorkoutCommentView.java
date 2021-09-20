package CustomViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.workoutbasic.OnInputListener;

import Fragments.CommentEditFragment;
import Fragments.TextEditPopupFragment;
import Variables.TextViewData;
import Variables.commentStr;

public class WorkoutCommentView extends WorkoutImageView {
    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextEditListener() {
        setOnClickListener(view -> {
            CommentEditFragment popup = new CommentEditFragment((commentStr)parentData);
            popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TextEditPopupFragment");
        });
    }
}
