package com.example.workoutbasic.interfaces.listeners;

import java.util.Map;

public interface DoubleListenerMap {
    Map<Integer, IntConsumer> apply(int exercisePosition);
}
