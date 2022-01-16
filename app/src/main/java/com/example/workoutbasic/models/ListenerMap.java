package com.example.workoutbasic.models;

import com.example.workoutbasic.interfaces.listeners.OnClickListener;

import java.util.Map;

public class ListenerMap {
    private final Map<Integer, OnClickListener> listeners;

    public ListenerMap(Map<Integer, OnClickListener> listeners) {
        this.listeners = listeners;
    }

    public OnClickListener getListener(int id) {
        return listeners.get(id);
    }
}
