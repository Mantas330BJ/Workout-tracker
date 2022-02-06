package com.example.workoutbasic.dataedit.dataeditfragments.text;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.utils.EditTextMethods;
import com.example.workoutbasic.viewmodels.SetViewModel;

import java.util.Objects;

public class TextFragment extends DialogFragment {
    private EditText editText;
    private GetterSetter<String> getterSetter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.string_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetViewModel viewModel = new ViewModelProvider(requireActivity()).get(SetViewModel.class);
        viewModel.getSelectedString().observe(requireActivity(), modifier -> {
            getterSetter = modifier;
            createView(view);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void createView(View view) {
        editText = view.findViewById(R.id.edit_text);
        EditTextMethods.setEditTextParams(this, editText);
        editText.setText(getterSetter.get());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        getterSetter.set(editText.getText().toString());
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow()
                .setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
