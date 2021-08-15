package com.example.workoutbasic;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

abstract class TextViewData {

    private ArrayList<ChangeListener> listeners = new ArrayList<>(Arrays.asList(new ChangeListener[3])); //Listeners count

    public void setListener(ChangeListener listener, int place) {
        listeners.set(place, listener);
    }

    public void callOnChange() {
        for (ChangeListener listener : listeners) {
            if (listener != null) {
                listener.onChange();
            }
        }
    }

    public abstract String toString();
    public abstract void setFragmentInput(TextEditPopupFragment fragment);
    public abstract void setFragmentOnDismiss(TextEditPopupFragment fragment);
}
