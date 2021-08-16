package com.example.workoutbasic;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dte extends TextViewData {
    private Date date;

    Dte(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        fragment.editView = new WorkoutDatePicker(fragment.getContext());
        WorkoutDatePicker editView = (WorkoutDatePicker)fragment.editView;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day = calendar.get(Calendar.DAY_OF_MONTH);
        editView.init(year, month, day, null); //Keep mind to month 0-11
        fragment.linearLayout.addView(editView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        Calendar cal = Calendar.getInstance();
        WorkoutDatePicker editView = (WorkoutDatePicker)fragment.editView;
        int year = editView.getYear(), month = editView.getMonth(), day = editView.getDayOfMonth();
        cal.set(year, month, day);
        date = cal.getTime();
        callOnChange();
        fragment.onInputListener.sendInput(this);
    }
}
