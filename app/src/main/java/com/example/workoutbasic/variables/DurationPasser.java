package com.example.workoutbasic.variables;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Locale;

import com.example.workoutbasic.interfaces.Variables.TextViewData;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DurationPasser implements TextViewData {
    private int seconds;

    public DurationPasser(int seconds) {
        this.seconds = seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getDuration() {
        return seconds;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }
}
