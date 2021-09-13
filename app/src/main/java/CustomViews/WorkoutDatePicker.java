package CustomViews;

import android.content.Context;
import android.widget.DatePicker;

import Interfaces.Editable;

public class WorkoutDatePicker extends DatePicker implements Editable {
    public WorkoutDatePicker(Context context) {
        super(context);
    }
}
