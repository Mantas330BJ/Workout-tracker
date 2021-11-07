package DataEdit.DataEditFragments.Numbers.Floats;

import android.text.InputFilter;
import android.text.InputType;

import DataEdit.DataEditFragments.Numbers.NumberFragment;

public abstract class FloatFragment extends NumberFragment {

    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }
}
