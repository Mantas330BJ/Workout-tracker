package Variables;

import androidx.annotation.NonNull;

import Interfaces.Variables.InputDatas;
import Interfaces.Variables.TextViewData;

public class StringPasser implements TextViewData, InputDatas {
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
