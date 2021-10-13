package DataEdit.TextViews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;


import Activities.DatabaseActivity;
import Interfaces.TextViewData;
import Interfaces.TextViewInput;
import ViewModels.SharedViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public abstract class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView implements TextViewInput {
    private final Context context;
    protected TextViewData textData; //Used in dialog fragments.

    public WorkoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public WorkoutTextView(Context context) {
        super(context);
        this.context = context;
    }

    abstract public void createFragment();

    public void setTextClickListener() {
        setOnClickListener((view) -> {
            createFragment();
            ((FragmentActivity)context).getSupportFragmentManager().executePendingTransactions();

            SharedViewModel viewModel = ((DatabaseActivity)context).getModel();
            viewModel.select(textData);
            viewModel.getSelected().observe((LifecycleOwner)context, text ->
                    super.setText(text.toString()));

        });
    }

    public void setText(TextViewData textData) {
        this.textData = textData;
        super.setText(textData.toString());
    }
}

