package com.example.workoutbasic.interfaces.listeners;

import com.example.workoutbasic.models.ListenerMap;

public interface DoubleListenerMap {
    ListenerMap apply(DoubleClickListener listener, int exercisePosition);
}
