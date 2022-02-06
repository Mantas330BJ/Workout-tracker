package com.example.workoutbasic.dataedit.dataeditfragments.numbers;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;
import com.example.workoutbasic.models.GetterSetter;
import com.example.workoutbasic.utils.EditTextMethods;
import com.example.workoutbasic.utils.Parser;
import com.example.workoutbasic.utils.StringConverter;
import com.example.workoutbasic.viewmodels.SetViewModel;

public class DoubleFragment extends DialogFragment {
    private GetterSetter<Double> getterSetter;
    private EditText editText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetViewModel viewModel = new ViewModelProvider(requireActivity()).get(SetViewModel.class);
        viewModel.getSelectedDouble().observe(requireActivity(), modifier -> {
            getterSetter = modifier;
            createView(view);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void createView(View view) {
        int maxLength = 8;
        editText = view.findViewById(R.id.edit_text);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        EditTextMethods.setEditTextParams(this, editText);
        editText.setText(StringConverter.convertDouble(getterSetter.get()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        String text = editText.getText().toString();
        getterSetter.set(Parser.parseDouble(text));
        super.onDismiss(dialog);
    }
}
