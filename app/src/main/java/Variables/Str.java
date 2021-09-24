package Variables;

import android.view.WindowManager;

import androidx.annotation.NonNull;

import Fragments.TextEditPopupFragment;
import CustomViews.WorkoutEditText;
import Interfaces.AssociatedPicker;
import Interfaces.Editable;
import Interfaces.EditableLambda;
import Interfaces.TextViewData;

import java.util.Objects;

public class Str implements TextViewData {
    private String s;

    public Str(String s) {
        this.s = s;
    }

    @NonNull
    public String toString() {
        return s;
    }
}
