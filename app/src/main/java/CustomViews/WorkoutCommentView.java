package CustomViews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.workoutbasic.OnInputListener;

import Fragments.TextEditPopupFragment;

public class WorkoutCommentView extends WorkoutImageView {
    public WorkoutCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextEditListener() {
        setOnClickListener(view -> {
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.show(((FragmentActivity)getContext()).getSupportFragmentManager(), "TextEditPopupFragment");
            ((FragmentActivity)getContext()).getSupportFragmentManager().executePendingTransactions();
            parentData.setFragmentInput(popup);
            popup.setParentData(parentData);
            ((OnInputListener)getContext()).setCurrentClicked(this);
        });
    }
}
