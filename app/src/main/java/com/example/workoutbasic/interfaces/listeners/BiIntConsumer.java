package com.example.workoutbasic.interfaces.listeners;

import android.view.View;

public interface BiIntConsumer {
    void consume(int exercisePosition, int setPosition, View view);
}
