package DataEditFragments.Numbers;

import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;

import androidx.annotation.NonNull;

import DataEditFragments.Numbers.NumberFragment;
import Interfaces.StringNumber;
import Interfaces.TextViewData;
import Variables.IntPasser;

public class IntegerFragment extends NumberFragment {

    public IntegerFragment(TextViewData parentData) {
        super(parentData);
    }

    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }

}
