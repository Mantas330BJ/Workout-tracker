package DataEditFragments;

import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;

import androidx.annotation.NonNull;

import Variables.Flt;

public class FloatFragment extends NumberFragment {
    private Flt parentData;

    public FloatFragment(Flt parentData) {
        this.parentData = parentData;
    }

    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }

    @Override
    public String getText() {
        return parentData.toString();
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setFromString(editText.getText().toString());
        getOnInputListener().sendInput(parentData.toString());
    }
}
