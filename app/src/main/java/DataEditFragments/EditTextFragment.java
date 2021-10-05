package DataEditFragments;

import android.view.WindowManager;
import android.widget.EditText;

import java.util.Objects;

public abstract class EditTextFragment extends TextFragments {
    public void setEditTextParams(String text, EditText editText) {
        editText.setText(text);
        editText.requestFocus();
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
