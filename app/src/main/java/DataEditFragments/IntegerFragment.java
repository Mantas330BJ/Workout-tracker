package DataEditFragments;

import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;

import androidx.annotation.NonNull;

import Variables.Int;

public class IntegerFragment extends NumberFragment {
    private Int parentData;

    public IntegerFragment(Int parentData) {
        this.parentData = parentData;
    }

    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setFromString(editText.getText().toString());
        getOnInputListener().sendInput(parentData.toString());
    }
}
