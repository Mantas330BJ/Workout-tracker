package DataEditFragments.Text;

import android.view.WindowManager;
import android.widget.EditText;

import java.util.Objects;

import DataEditFragments.TextFragments;
import Interfaces.TextViewData;

public abstract class EditTextFragment extends TextFragments {
    public EditTextFragment(TextViewData parentData) {
        super(parentData);
    }

    public void setEditTextParams(String text, EditText editText) {
        editText.setText(text);
        editText.requestFocus();
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
