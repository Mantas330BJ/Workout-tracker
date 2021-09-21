package Variables;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import Fragments.TextEditPopupFragment;
import CustomViews.WorkoutTimePicker;
import Interfaces.AssociatedPicker;
import Interfaces.Editable;
import Interfaces.EditableLambda;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Drt extends TextViewData {
    private int seconds;

    public Drt(int seconds) {
        this.seconds = seconds;
    }

    public int getDuration() {
        return seconds;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }

    public EditableLambda getPicker() {
        return WorkoutTimePicker::new;
    }

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
    }

    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        WorkoutTimePicker editView = (WorkoutTimePicker)fragment.editView;
        seconds = editView.getMinutes() * 60 + editView.getSeconds();
        fragment.onInputListener.sendInput(this);
    }

    @Override
    public void setFilters(Editable editView) {
    }
}
