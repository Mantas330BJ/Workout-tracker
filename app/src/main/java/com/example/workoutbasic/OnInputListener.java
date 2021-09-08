package com.example.workoutbasic;

import android.view.View;

public interface OnInputListener {
    void sendInput(TextViewData input);
    void setCurrentClicked(WorkoutInput currentClicked);
}
