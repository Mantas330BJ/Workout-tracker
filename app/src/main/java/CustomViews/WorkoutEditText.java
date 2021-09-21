package CustomViews;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import Interfaces.Editable;
import Variables.TextViewData;

public class WorkoutEditText extends androidx.appcompat.widget.AppCompatEditText implements Editable {
    public WorkoutEditText(@NonNull Context context) {
        super(context);
        setDefaultParameters();
    }

    public void setDefaultParameters() {
        setEms(20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        params.setMargins(10, 10 ,10 ,10);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
    }

    @Override
    public void displayData(TextViewData parentData) {
        setText(parentData.toString());
    }

    @Override
    public View getView() {
        return this;
    }
}
