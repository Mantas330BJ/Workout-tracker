package com.example.workoutbasic.variables;

import androidx.annotation.NonNull;

import com.example.workoutbasic.interfaces.StringNumber;
import com.example.workoutbasic.interfaces.variables.TextViewData;

import java.util.Locale;

public class DoublePasser implements TextViewData, StringNumber {
    private double dbl;

    public DoublePasser(double dbl) {
        this.dbl = dbl;
    }

    public double getDouble() {
        return dbl;
    }

    public void setDouble(double dbl) {
        this.dbl = dbl;
    }

    public void setFromString(String s) {
        this.dbl = s.isEmpty() ? 0 : Double.parseDouble(s);
    }

    @NonNull
    public String toString() {
        int precision = 0;
        double tflt = dbl, epsilon = 0.001;
        while (Math.abs(Math.round(tflt) - tflt) > epsilon) {
            tflt *= 10;
            ++precision;
        }

        String format = "%." + precision + "f";
        return String.format(Locale.getDefault(), format, dbl);
    }
}
