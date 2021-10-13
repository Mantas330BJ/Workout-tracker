package DataEdit.ImageViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import Activities.DatabaseActivity;
import DataEdit.DataEditFragments.Text.CommentEditFragment;
import Interfaces.TextViewData;
import Variables.StringPasser;

public class WorkoutCommentView extends WorkoutImageView {
    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextClickListener() {
        setOnClickListener(view -> {
            CommentEditFragment popup = new CommentEditFragment();
            popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TextEditPopupFragment");
            ((FragmentActivity)getContext()).getSupportFragmentManager().executePendingTransactions();

            ((DatabaseActivity)getContext()).getModel().select((TextViewData) parentData);
        });
    }
}
