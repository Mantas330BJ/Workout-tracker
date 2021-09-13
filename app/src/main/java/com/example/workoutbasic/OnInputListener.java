package com.example.workoutbasic;

import Interfaces.WorkoutInput;
import Variables.TextViewData;

public interface OnInputListener {
    void sendInput(TextViewData input);
    void setCurrentClicked(WorkoutInput currentClicked);
}
