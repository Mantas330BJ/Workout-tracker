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

import Interfaces.TextViewData;
import Variables.Dte;

public abstract class NumberFragment extends EditTextFragment {
    protected EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.number_fragment, container, false);
        editText = view.findViewById(R.id.edit_text);
        setFilters();
        setEditTextParams(getText(), editText);
        return view;
    }

    public abstract void setFilters();

    public abstract String getText();
}
