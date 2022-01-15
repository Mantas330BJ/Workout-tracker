package com.example.workoutbasic.variables;

import androidx.annotation.NonNull;

import com.example.workoutbasic.interfaces.StringNumber;
import com.example.workoutbasic.interfaces.Variables.TextViewData;

public class IntPasser implements TextViewData, StringNumber {
    private int val;

    public IntPasser(int val) {
        this.val = val;
    }

    public void setInt(int val) {
        this.val = val;
    }

    public void setFromString(String s) {
        this.val = s.isEmpty() ? 0 : Integer.parseInt(s);
    }

    public int getVal() {
        return val;
    }

    @NonNull
    public String toString() {
        return Integer.toString(val);
    }
}
