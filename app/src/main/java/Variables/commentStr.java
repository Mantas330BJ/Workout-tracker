package Variables;

import androidx.annotation.NonNull;

import Interfaces.InfoData;
import Interfaces.TextViewData;

public class commentStr implements InfoData, TextViewData {
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
