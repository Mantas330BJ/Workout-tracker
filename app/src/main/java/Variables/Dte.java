package Variables;

import androidx.annotation.NonNull;

import Interfaces.TextViewData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Dte implements TextViewData {
    private Date date;

    public Dte(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
