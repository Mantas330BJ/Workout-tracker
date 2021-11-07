package Utils;

import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class EditTextMethods {

    public static void setEditTextParams(DialogFragment dialog, String text, EditText editText) {
        editText.setText(text);
        editText.requestFocus();
        Window window = Objects.requireNonNull(dialog.getDialog()).getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
