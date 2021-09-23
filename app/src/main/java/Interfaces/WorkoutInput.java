package Interfaces;

import android.view.View;

public interface WorkoutInput {
    void setText(CharSequence text);
    void setOnLongClickListener(View.OnLongClickListener onLongClickListener);
    void setOnClickListener(View.OnClickListener onClickListener);
}