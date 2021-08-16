package com.example.workoutbasic;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TextEditPopupFragment extends DialogFragment {
    public Editable editView;
    public LinearLayout linearLayout;
    public OnInputListener onInputListener;
    private TextViewData parentData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_edit_popup, container, false);
        linearLayout = view.findViewById(R.id.linear_layout);
        return view;
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setFragmentOnDismiss(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void setParentData(TextViewData parentData) {
        this.parentData = parentData;
    }

}
