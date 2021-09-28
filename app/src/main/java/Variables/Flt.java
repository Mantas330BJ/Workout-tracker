package Variables;

import androidx.annotation.NonNull;

import Interfaces.StringNumber;
import Interfaces.TextViewData;

import java.util.Locale;

public class Flt implements TextViewData, StringNumber {
    private float flt;

    public Flt(float flt) {
        this.flt = flt;
    }

    public float getFlt() {
        return flt;
    }

    public void setFlt(float flt) {
        this.flt = flt;
    }

    public void setFromString(String s) {
        this.flt = Float.parseFloat(s);
    }

    @NonNull
    public String toString() {
        int precision = 0;
        float tflt = flt, epsilon = (float) 1e-3;
        while (Math.abs((int)tflt - tflt) > epsilon) {
            tflt *= 10;
            ++precision;
        }

        String format = "%." + precision + "f";
        return String.format(Locale.getDefault(), format, flt);
    }
}
