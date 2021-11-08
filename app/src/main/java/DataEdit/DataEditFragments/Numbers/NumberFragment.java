package DataEdit.DataEditFragments.Numbers;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.SetFragments;
import Utils.EditTextUtils;
import Utils.Formatter;

public abstract class NumberFragment extends SetFragments {
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
        EditTextUtils.setEditTextParams(this, (String) setData.getGetter(methodIndex), editText);
    }

    public void onDismiss(@NonNull final DialogInterface dialog) {
        setData.getSetter(editText.getText().toString(), methodIndex);
        super.onDismiss(dialog);
    }

    public abstract void setFilters();
}
