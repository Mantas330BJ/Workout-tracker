package com.example.workoutbasic.utils;

import android.app.Dialog;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class EditTextMethods {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void setEditTextParams(DialogFragment dialog, EditText editText) {
        editText.requestFocus();
        Window window = Objects.requireNonNull(dialog.getDialog()).getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
