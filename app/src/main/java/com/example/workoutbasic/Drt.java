package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Drt extends TextViewData {
    private int seconds;

    Drt(int seconds) {
        this.seconds = seconds;
    }

    public int getDuration() {
        return seconds;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutTimePicker(fragment.getContext());
        WorkoutTimePicker editView = (WorkoutTimePicker)fragment.editView;
        editView.setDuration(seconds);
        fragment.linearLayout.addView(editView.getPickerLayout());
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        WorkoutTimePicker editView = (WorkoutTimePicker)fragment.editView;
        seconds = editView.getMinutes() * 60 + editView.getSeconds();
        fragment.onInputListener.sendInput(this);
    }
}
