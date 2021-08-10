package com.example.workoutbasic;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Flt extends TextViewData {
    private float flt;

    Flt(float flt) {
        this.flt = flt;
    }

    @NonNull
    public String toString() {
        if ((int)(flt) == flt)
            return String.format(Locale.getDefault(), "%d", (int)flt);
        return String.format(Locale.getDefault(), "%.1f", flt);
    }
}
