package Variables;

import android.text.InputFilter;
import android.text.InputType;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import Fragments.TextEditPopupFragment;
import CustomViews.WorkoutEditText;
import Interfaces.AssociatedPicker;
import Interfaces.Editable;
import Interfaces.EditableLambda;
import Interfaces.Filter;

import java.util.Objects;

public class Int extends TextViewData implements AssociatedPicker, Filter {
    private int val;

    public Int(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @NonNull
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.requestFocus();
        Objects.requireNonNull(fragment.getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        val = Integer.parseInt(Objects.requireNonNull(((WorkoutEditText) fragment.editView).getText()).toString());
        fragment.onInputListener.sendInput(this);
    }

    public void setFilters(Editable editView) {
        int maxLength = 8;
        ((WorkoutEditText)editView).setInputType(InputType.TYPE_CLASS_NUMBER);
        ((WorkoutEditText)editView).setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }

    @Override
    public EditableLambda getPicker() {
        return WorkoutEditText::new;
    }
}
