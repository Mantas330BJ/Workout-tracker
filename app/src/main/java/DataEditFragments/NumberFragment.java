package DataEditFragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import java.util.Calendar;
import java.util.Objects;

import Variables.Dte;

public abstract class NumberFragment extends TextFragments {
    protected EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_fragment, container, false);
        editText = view.findViewById(R.id.edit_text); //Called from derived variables with edit text.
        editText.requestFocus();
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setFilters();
        editText.setText(getText());
        return view;
    }


    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        String updatedInfo = editText.getText().toString();
        getOnInputListener().sendInput(updatedInfo);
    }

    public abstract void setFilters();

    public abstract String getText();
}
