package Utils;

import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class EditTextUtils {

    public static void setEditTextParams(DialogFragment fragment, String text, EditText editText) {
        editText.setText(text);
        editText.requestFocus();
        Window window = Objects.requireNonNull(fragment.getDialog()).getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
