package DataEditFragments;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.workoutbasic.R;

import java.util.Objects;

public abstract class EditTextFragment extends TextFragments {
    public EditText setEditTextParams(String text, EditText editText) {
        editText.setText(text);
        editText.requestFocus();
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return editText;
    }
}
