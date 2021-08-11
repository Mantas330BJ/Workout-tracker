package com.example.workoutbasic;

import android.content.Context;

abstract class TextViewData {
    public abstract String toString();
    public abstract void setFragmentInput(TextEditPopupFragment fragment);
    public abstract void setFragmentOnDismiss(TextEditPopupFragment fragment);
}
