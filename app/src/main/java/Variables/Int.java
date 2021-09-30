package Variables;

import androidx.annotation.NonNull;

import Interfaces.StringNumber;
import Interfaces.TextViewData;

public class Int implements TextViewData, StringNumber {
    private int val;

    public Int(int val) {
        this.val = val;
    }

    public void setVal(int val) {
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
