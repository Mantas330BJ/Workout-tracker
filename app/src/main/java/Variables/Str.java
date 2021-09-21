package Variables;

import android.view.WindowManager;

import androidx.annotation.NonNull;

import Fragments.TextEditPopupFragment;
import CustomViews.WorkoutEditText;
import Interfaces.AssociatedPicker;
import Interfaces.Editable;
import Interfaces.EditableLambda;

import java.util.Objects;

public class Str extends TextViewData implements AssociatedPicker {
    private String s;

    public Str(String s) {
        this.s = s;
    }

    @NonNull
    public String toString() {
        return s;
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
        WorkoutEditText editView = (WorkoutEditText)fragment.editView;
        editView.requestFocus();
        Objects.requireNonNull(fragment.getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        s = Objects.requireNonNull(((WorkoutEditText) fragment.editView).getText()).toString();
        fragment.onInputListener.sendInput(this);
    }

    @Override
    public EditableLambda getPicker() {
        return WorkoutEditText::new;
    }

    @Override
    public void setFilters(Editable editView) {}
}
