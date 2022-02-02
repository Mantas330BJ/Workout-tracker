package com.example.workoutbasic.interfaces.listeners;

public interface NestedListenerPasser {
    BiIntConsumer getDoubleClickListener();
    PositionLongClickListener getOnLongClickListener();
}
