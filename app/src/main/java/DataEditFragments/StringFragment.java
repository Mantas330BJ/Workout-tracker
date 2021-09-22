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

import java.util.Objects;

import CustomViews.WorkoutEditText;
import Variables.Str;

public class StringFragment extends TextFragments { //Float, Int
    private Str parentData;
    private EditText editText;

    public StringFragment(Str parentData) {
        this.parentData = parentData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.string_fragment, container, false);
        editText = view.findViewById(R.id.edit_text); //Called from derived variables with edit text.
        editText.requestFocus();
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        editText.setText(parentData.toString());
        return view;
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        String updatedInfo = editText.getText().toString();
        getOnInputListener().sendInput(updatedInfo); //Send strings??
    }

}
