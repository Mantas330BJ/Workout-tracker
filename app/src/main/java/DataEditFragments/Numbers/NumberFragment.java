package DataEditFragments.Numbers;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.workoutbasic.R;

import DataEditFragments.Text.EditTextFragment;

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
