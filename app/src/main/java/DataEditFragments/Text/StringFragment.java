package DataEditFragments.Text;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;

import java.util.Objects;

import Interfaces.TextViewData;
import Variables.StringPasser;
import ViewModels.SharedViewModel;

public class StringFragment extends EditTextFragment { //Float, Int
    protected EditText editText;

    public StringFragment(TextViewData parentData) {
        super(parentData);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.string_fragment, container, false);
        editText = view.findViewById(R.id.edit_text);
        setEditTextParams(parentData.toString(), editText);
        return view;
    }

    public void setNewData() {
        ((StringPasser)parentData).setStr(editText.getText().toString());
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        setNewData();
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
