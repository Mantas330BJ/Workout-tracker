package Variables;

import androidx.annotation.NonNull;

import Interfaces.InfoData;
import Interfaces.TextViewData;

public class StringPasser implements TextViewData, InfoData {
    private String s;

    public StringPasser(String s) {
        this.s = s;
    }

    public void setStr(String s) {
        this.s = s;
    }

    @NonNull
    public String toString() {
        return s;
    }
}
