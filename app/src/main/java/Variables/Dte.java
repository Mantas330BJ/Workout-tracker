package Variables;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import Fragments.TextEditPopupFragment;
import CustomViews.WorkoutDatePicker;
import Interfaces.AssociatedPicker;
import Interfaces.Editable;
import Interfaces.EditableLambda;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dte extends TextViewData {
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

    @Override
    public void setFragmentInput(TextEditPopupFragment fragment) {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setFragmentOnDismiss(TextEditPopupFragment fragment) {
        Calendar cal = Calendar.getInstance();
        WorkoutDatePicker editView = (WorkoutDatePicker)fragment.editView;
        int year = editView.getYear(), month = editView.getMonth(), day = editView.getDayOfMonth();
        cal.set(year, month, day);
        date = cal.getTime();
        fragment.onInputListener.sendInput(this);
    }

    @Override
    public EditableLambda getPicker() {
        return WorkoutDatePicker::new;
    }

    @Override
    public void setFilters(Editable editView) {}
}
