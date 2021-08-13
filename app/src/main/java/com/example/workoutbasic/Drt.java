package com.example.workoutbasic;

import android.content.Context;
import android.os.Build;
import android.view.WindowManager;

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

    public Duration getDuration() {
        return duration;
    }

    public String toString() {
        long seconds = duration.getSeconds();
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutTimePicker(fragment.getContext());
        WorkoutTimePicker editView = (WorkoutTimePicker)fragment.editView;
        editView.setDuration(duration);
        fragment.linearLayout.addView(editView.getPickerLayout());
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        WorkoutTimePicker editView = (WorkoutTimePicker)fragment.editView;
        duration = Duration.ofSeconds(editView.getMinutes() * 60L + editView.getSeconds());
        fragment.onInputListener.sendInput(this);
    }
}
