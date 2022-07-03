package com.example.workoutbasic.interfaces.listeners;

import android.view.View;

public interface Listenable {
    View.OnClickListener getOnClickListener();
    View.OnLongClickListener getOnLongClickListener();
}
