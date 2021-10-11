package DataEditFragments.Numbers;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;

import DataEditFragments.Text.EditTextFragment;
import Interfaces.StringNumber;
import Interfaces.TextViewData;
import ViewModels.SharedViewModel;

public abstract class NumberFragment extends EditTextFragment {
    protected EditText editText;
    private SharedViewModel model;

    public NumberFragment(TextViewData parentData) {
        super(parentData);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_fragment, container, false);
        editText = view.findViewById(R.id.edit_text);
        setFilters();
        setEditTextParams(getText(), editText);
        return view;
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
