package com.example.workoutbasic;

import Interfaces.WorkoutInput;

public interface OnInputListener {
    void sendInput(String input);
    void setCurrentClicked(WorkoutInput currentClicked);
}
