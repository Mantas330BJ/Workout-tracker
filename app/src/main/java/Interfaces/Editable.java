package Interfaces;

import android.view.View;

import Variables.TextViewData;

public interface Editable {
    void displayData(TextViewData parentData);
    View getView();
}
