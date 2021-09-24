package DataEditFragments;

import android.text.InputFilter;
import android.text.InputType;

import CustomViews.WorkoutEditText;
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


}
