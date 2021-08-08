package com.example.workoutbasic;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class TextEditPopupFragment extends DialogFragment {
    private EditText editText;
    public static final String EXTRA_STRING = "extrastr";
    public OnInputListener onInputListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_edit_popup, container, false);

        editText = view.findViewById(R.id.edit_text);
        String extraString = getArguments().getString(EXTRA_STRING);
        editText.setText(extraString);
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        return view;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        String input = editText.getText().toString();
        onInputListener.sendInput(input);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

}
