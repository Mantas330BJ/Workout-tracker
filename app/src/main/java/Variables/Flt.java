package Variables;

import androidx.annotation.NonNull;

import Interfaces.TextViewData;

import java.util.Locale;

public class Flt implements TextViewData {
    private float flt;

    public Flt(float flt) {
        this.flt = flt;
    }

    public float getFlt() {
        return flt;
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
