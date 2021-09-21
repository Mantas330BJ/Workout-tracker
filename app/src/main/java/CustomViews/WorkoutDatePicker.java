package CustomViews;

import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import Interfaces.Editable;
import Variables.TextViewData;

public class WorkoutDatePicker extends DatePicker implements Editable {
    public WorkoutDatePicker(Context context) {
        super(context);
    }

    @Override
    public void displayData(TextViewData parentData) {
        Calendar cal = Calendar.getInstance();
        int year = getYear(), month = getMonth(), day = getDayOfMonth();
        cal.set(year, month, day);
        init(year, month, day, null);
    }

    @Override
    public View getView() {
        return this;
    }
}
