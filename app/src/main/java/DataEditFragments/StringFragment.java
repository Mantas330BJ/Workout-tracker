package DataEditFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.workoutbasic.R;

import java.util.Objects;

import Variables.StringPasser;

public class StringFragment extends EditTextFragment { //Float, Int
    protected final StringPasser parentData;
    protected EditText editText;

    public StringFragment(StringPasser parentData) {
        this.parentData = parentData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.string_fragment, container, false);
        editText = view.findViewById(R.id.edit_text);
        setEditTextParams(parentData.toString(), editText);
        return view;
    }

    public void dismissWithoutSettingText(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setStr(editText.getText().toString());
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        dismissWithoutSettingText(dialog);
        getOnInputListener().sendInput(parentData.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
