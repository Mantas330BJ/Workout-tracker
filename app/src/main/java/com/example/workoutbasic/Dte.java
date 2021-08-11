package com.example.workoutbasic;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dte extends TextViewData {
    private Date date;

    Dte(Date date) {
        this.date = date;
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

    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {

    }
}
