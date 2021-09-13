package CustomViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.workoutbasic.OnInputListener;
import Fragments.TextEditPopupFragment;

import Interfaces.WorkoutInput;
import Variables.TextViewData;

@RequiresApi(api = Build.VERSION_CODES.O)

public class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView implements WorkoutInput {
    private final Context context;
    private TextViewData textData; //Used in dialog fragments.

    public WorkoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public WorkoutTextView(Context context) {
        super(context);
        this.context = context;
    }


    public void setBaseParams(TextViewData text) {
        textData = text;
        setSingleLine(true);
        setText(text.toString());
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(5, 5, 5, 5);
    }

    public void setTextEditListener() {
        setOnClickListener((view) -> {
            TextEditPopupFragment popup = new TextEditPopupFragment();
            popup.show(((FragmentActivity)context).getSupportFragmentManager(), "TextEditPopupFragment");
            ((FragmentActivity)context).getSupportFragmentManager().executePendingTransactions();
            textData.setFragmentInput(popup);
            popup.setParentData(textData);

            ((OnInputListener)context).setCurrentClicked(this);
        });
    }

    public void setText(TextViewData textData) {
        this.textData = textData;
        setText(textData.toString());
    }
}

