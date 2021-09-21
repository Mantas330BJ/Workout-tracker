package Variables;

import androidx.annotation.NonNull;

import Fragments.TextEditPopupFragment;
import Interfaces.AssociatedPicker;
import Interfaces.Filter;
import Interfaces.InfoData;

public abstract class TextViewData implements InfoData, AssociatedPicker, Filter {

    @NonNull
    public abstract String toString();
    public abstract void setFragmentInput(TextEditPopupFragment fragment);
    public abstract void setFragmentOnDismiss(TextEditPopupFragment fragment);
}
