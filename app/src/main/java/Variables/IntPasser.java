package Variables;

import androidx.annotation.NonNull;

import Interfaces.StringNumber;
import Interfaces.Variables.TextViewData;

public class IntPasser implements TextViewData, StringNumber {
    private int val;

    public IntPasser(int val) {
        this.val = val;
    }

    public void setInt(int val) {
        this.val = val;
    }

    public void setFromString(String s) {
        this.val = Integer.parseInt(s);
    }

    public int getVal() {
        return val;
    }

    @NonNull
    public String toString() {
        return Integer.toString(val);
    }
}
