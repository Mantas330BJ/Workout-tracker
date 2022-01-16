package com.example.workoutbasic.variables;

import androidx.annotation.NonNull;

import com.example.workoutbasic.interfaces.variables.TextViewData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatePasser implements TextViewData {
    private Date date;

    public DatePasser(Date date) {
        this.date = date;
    }

    public void setDate(Date date) {
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
}
