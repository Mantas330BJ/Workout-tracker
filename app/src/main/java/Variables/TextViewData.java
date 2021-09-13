package Variables;

import androidx.annotation.NonNull;

import Fragments.TextEditPopupFragment;

public abstract class TextViewData {

    @NonNull
    public abstract String toString();
    public abstract void setFragmentInput(TextEditPopupFragment fragment);
    public abstract void setFragmentOnDismiss(TextEditPopupFragment fragment);
}
