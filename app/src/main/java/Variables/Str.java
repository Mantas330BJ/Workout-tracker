package Variables;

import androidx.annotation.NonNull;

import Interfaces.TextViewData;

public class Str implements TextViewData {
    private String s;

    public Str(String s) {
        this.s = s;
    }

    @NonNull
    public String toString() {
        return s;
    }
}
