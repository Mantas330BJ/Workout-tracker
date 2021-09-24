package DataEditFragments;

import android.text.InputFilter;
import android.text.InputType;

import Variables.Int;

public class IntegerFragment extends NumberFragment {
    Int parentData;

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
}
