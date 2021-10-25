package DataEdit.TextViews;

import static Utils.FragmentMethods.unwrap;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import com.example.workoutbasic.R;

import DataEdit.DataEditFragments.TextFragments;
import Interfaces.Input.Inputs;
import Interfaces.Variables.InputDatas;
import Interfaces.Variables.TextViewData;
import Interfaces.Input.TextViewInput;
import Utils.FragmentMethods;
import ViewModels.SharedViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public abstract class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView implements TextViewInput {
    private Context context;
    protected TextViewData textData; //Used in dialog fragments.
    protected NavController navController;



    public WorkoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        navController = Navigation.findNavController(unwrap(context), R.id.nav_host_fragment);
    }

    abstract public void createFragment();

    public void setTextClickListener() {
        setOnClickListener((view) -> {
            createFragment();

            FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
            NavHostFragment navHostFragment = (NavHostFragment)fm.findFragmentById(R.id.nav_host_fragment);
            navHostFragment.getChildFragmentManager().executePendingTransactions();

            TextFragments calledFragment = (TextFragments) FragmentMethods.getParentFragment(context, 1);
            setViewModel(calledFragment);
        });
    }

    public void setViewModel(TextFragments calledFragment) {
        SharedViewModel viewModel = new ViewModelProvider(calledFragment).get(SharedViewModel.class);
        viewModel.select(textData);
        viewModel.getSelected().observe((LifecycleOwner)context, text ->
                super.setText(text.toString()));
    }

    @BindingAdapter("parentData")
    public static void setParentData(WorkoutTextView workoutTextView, InputDatas parentData) {
        workoutTextView.textData = (TextViewData) parentData;
        workoutTextView.setText(parentData.toString());
    }
}

