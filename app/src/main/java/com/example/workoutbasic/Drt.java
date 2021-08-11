package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.Period;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Drt extends TextViewData {
    private Duration duration;

    Drt(Duration duration) {
        this.duration = duration;
    }

    public String toString() {
        long seconds = duration.getSeconds();
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {

    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {

    }
}
