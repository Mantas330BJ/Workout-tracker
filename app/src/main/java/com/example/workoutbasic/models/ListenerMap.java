package com.example.workoutbasic.models;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.workoutbasic.interfaces.Listeners.OnClickListener;

import java.util.Map;
import java.util.Optional;

public class ListenerMap {
    private final Map<Integer, OnClickListener> listeners;

    public ListenerMap(Map<Integer, OnClickListener> listeners) {
        this.listeners = listeners;
    }

    public OnClickListener getListener(int id) {
        return listeners.get(id);
    }
}
