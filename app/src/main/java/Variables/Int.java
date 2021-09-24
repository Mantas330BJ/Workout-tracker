package Variables;

import androidx.annotation.NonNull;

import Interfaces.TextViewData;

public class Int implements TextViewData {
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
}
