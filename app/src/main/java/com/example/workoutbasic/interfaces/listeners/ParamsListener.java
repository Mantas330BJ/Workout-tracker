package com.example.workoutbasic.interfaces.listeners;

import android.view.View;

import java.util.Map;

public interface ParamsListener {
    void consume(Map<String, Integer> params, View view);
}
