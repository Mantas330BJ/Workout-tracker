package DataEdit.DataEditFragments.Numbers;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.TextFragments;
import Interfaces.StringNumber;
import Utils.EditTextMethods;

public abstract class NumberFragment extends TextFragments {
    protected EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_fragment, container, false);
    }

    @Override
    public void createView(View view) {
        editText = view.findViewById(R.id.edit_text);
        setFilters();
        EditTextMethods.setEditTextParams(this, getText(), editText);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        ((StringNumber)parentData).setFromString(editText.getText().toString());
        super.onDismiss(dialog);
    }

    public String getText() {
        return parentData.toString();
    }

    public abstract void setFilters();
}
