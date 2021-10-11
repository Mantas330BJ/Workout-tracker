package DataEditFragments.Numbers;

import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.InputType;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import DataEditFragments.Numbers.NumberFragment;
import Interfaces.StringNumber;
import Interfaces.TextViewData;
import Variables.DoublePasser;
import ViewModels.SharedViewModel;

public class FloatFragment extends NumberFragment {

    public FloatFragment(TextViewData parentData) {
        super(parentData);
    }


    @Override
    public void setFilters() {
        int maxLength = 8;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }

}
