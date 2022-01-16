package com.example.workoutbasic.dataedit.textviews;

import static com.example.workoutbasic.utils.FragmentMethods.unwrap;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.workoutbasic.R;
import com.example.workoutbasic.interfaces.input.TextViewInput;
import com.example.workoutbasic.interfaces.variables.InputDatas;
import com.example.workoutbasic.interfaces.variables.TextViewData;
import com.example.workoutbasic.viewmodels.SharedViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public abstract class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView implements TextViewInput {
    private Context context;
    protected TextViewData textData; //Used in dialog fragments.
    protected NavController navController;


    protected WorkoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }

    protected abstract void createFragment();

    public void setTextClickListener() {
        setOnClickListener(view -> {
            SharedViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SharedViewModel.class);
            viewModel.select(textData);
            viewModel.getSelected().observe((LifecycleOwner)context, text -> {
                    setText(text.toString());
                    if (!viewModel.firstTime)
                        viewModel.getSelected().removeObservers((LifecycleOwner) context);
                    viewModel.firstTime = !viewModel.firstTime;
                }
            ); //TODO: remove sharedviewmodel stuff
            createFragment();
        });
    }

    @BindingAdapter("parentData")
    public static void setParentData(WorkoutTextView workoutTextView, InputDatas parentData) {
        workoutTextView.textData = (TextViewData) parentData;
        workoutTextView.setText(parentData.toString());
    }
}

