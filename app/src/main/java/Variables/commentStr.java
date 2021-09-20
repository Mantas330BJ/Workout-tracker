package Variables;

import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.Objects;

import CustomViews.WorkoutEditText;
import Fragments.TextEditPopupFragment;
import Interfaces.InfoData;

public class commentStr implements InfoData {
    private String s;

    public commentStr(String s) {
        this.s = s;
    }

    public void setStr(String s) {
        this.s = s;
    }

    @NonNull
    @Override
    public String toString() {
        return s;
    }
}
