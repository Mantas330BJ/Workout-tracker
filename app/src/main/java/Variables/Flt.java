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

import java.util.Locale;
import java.util.Objects;

public class Flt extends TextViewData implements AssociatedPicker {
    private float flt;

    public Flt(float flt) {
        this.flt = flt;
    }

    public float getFlt() {
        return flt;
    }

    @NonNull
    public String toString() {
        int precision = 0;
        float tflt = flt, epsilon = (float) 1e-3;
        while (Math.abs((int)tflt - tflt) > epsilon) {
            tflt *= 10;
            ++precision;
        }

        String format = "%." + precision + "f";
        return String.format(Locale.getDefault(), format, flt);
    }

    //Almost total copy paste from Str
    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.requestFocus();
        Objects.requireNonNull(fragment.getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        flt = Float.parseFloat(Objects.requireNonNull(((WorkoutEditText) fragment.editView).getText()).toString());
        fragment.onInputListener.sendInput(this);
    }

    @Override
    public EditableLambda getPicker() {
        return WorkoutEditText::new;
    }

    @Override
    public void setFilters(Editable editView) {
        int maxLength = 8;
        ((WorkoutEditText)editView).setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ((WorkoutEditText)editView).setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
    }
}
