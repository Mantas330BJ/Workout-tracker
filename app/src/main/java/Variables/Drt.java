package Variables;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Locale;

import Interfaces.TextViewData;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Drt implements TextViewData {
    private int seconds;

    public Drt(int seconds) {
        this.seconds = seconds;
    }

    public int getDuration() {
        return seconds;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "%d:%02d", seconds / 60, seconds % 60);
    }
}
