package com.example.workoutbasic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

abstract class TextViewData {

    @NonNull
    public abstract String toString();
    public abstract void setFragmentInput(TextEditPopupFragment fragment);
    public abstract void setFragmentOnDismiss(TextEditPopupFragment fragment);
}
