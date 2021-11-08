package DataEdit.TextViews;

import static Utils.FragmentMethods.unwrap;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.SetFragments;
import Interfaces.Input.TextViewInput;

@RequiresApi(api = Build.VERSION_CODES.O)

public abstract class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView implements TextViewInput {
    protected NavController navController;

    protected int propertyIdx;

    public WorkoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(SetFragments.METHOD_ID, propertyIdx);
        return bundle;
    }

    abstract public void createFragment();

    public void setTextClickListener() {
        setOnClickListener((view) -> {
            /*
            SharedViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SharedViewModel.class);
            viewModel.select(textData);
            viewModel.getSelected().observe((LifecycleOwner)context, text -> {
                    setText(text.toString());
                    if (!viewModel.firstTime)
                        viewModel.getSelected().removeObservers((LifecycleOwner) context);
                    viewModel.firstTime = !viewModel.firstTime;
                }
            );
             */
            createFragment();
        });
    }

    public void setPropertyIdx(int propertyIdx) {
        this.propertyIdx = propertyIdx;
    }
}

