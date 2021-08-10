package com.example.workoutbasic;

import androidx.annotation.NonNull;

public class Int extends TextViewData {
    private int val;

    public Int(int val) {
        this.val = val;
    }

    @NonNull
    public String toString() {
        return Integer.toString(val);
    }
}
