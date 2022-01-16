package com.example.workoutbasic.variables;

import androidx.annotation.NonNull;

import com.example.workoutbasic.interfaces.variables.InputDatas;
import com.example.workoutbasic.interfaces.variables.TextViewData;

public class StringPasser implements TextViewData, InputDatas {
    private String s;

    public StringPasser(String s) {
        this.s = s;
    }

    public void setStr(String s) {
        this.s = s;
    }

    @NonNull
    public String toString() {
        return s;
    }
}
