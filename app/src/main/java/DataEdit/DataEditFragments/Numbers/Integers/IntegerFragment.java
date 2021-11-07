package DataEdit.DataEditFragments.Numbers.Integers;

import android.text.InputFilter;
import android.text.InputType;
import android.util.Pair;

import DataEdit.DataEditFragments.Numbers.NumberFragment;
import Utils.Formatter;
import Utils.NumberSetter;

public abstract class IntegerFragment extends NumberFragment {

    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }
}

