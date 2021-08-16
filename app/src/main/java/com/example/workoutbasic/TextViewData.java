package com.example.workoutbasic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

abstract class TextViewData {

    private final ArrayList<ChangeListener> listeners = new ArrayList<>(Arrays.asList(new ChangeListener[3])); //Listeners count

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

    @NonNull
    public abstract String toString();
    public abstract void setFragmentInput(TextEditPopupFragment fragment);
    public abstract void setFragmentOnDismiss(TextEditPopupFragment fragment);
}
