package com.example.workoutbasic;

import Interfaces.TextViewInput;
import Interfaces.WorkoutInput;

public interface OnInputListener {
    void sendInput(String input);
    void setCurrentClicked(TextViewInput currentClicked);
}
