package DataEdit.TextViews;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import com.example.workoutbasic.R;

import Activities.DatabaseActivity;
import DataEdit.DataEditFragments.TextFragments;
import Interfaces.TextViewData;
import Interfaces.TextViewInput;
import ViewModels.SharedViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)

public abstract class WorkoutTextView extends androidx.appcompat.widget.AppCompatTextView implements TextViewInput {
    private Context context;
    protected TextViewData textData; //Used in dialog fragments.
    protected NavController navController;

    public WorkoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
    }

    abstract public void createFragment();

    public void setTextClickListener() {
        setOnClickListener((view) -> {
            createFragment();
            FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();

            NavHostFragment navHostFragment = (NavHostFragment)fm.findFragmentById(R.id.nav_host_fragment);
            navHostFragment.getChildFragmentManager().executePendingTransactions();

            TextFragments calledFragment = (TextFragments) navHostFragment.getChildFragmentManager().getFragments().get(1);
            setViewModel(calledFragment);
        });
    }

    public void setViewModel(TextFragments calledFragment) {
        SharedViewModel viewModel = new ViewModelProvider(calledFragment).get(SharedViewModel.class);
        viewModel.select(textData);
        viewModel.getSelected().observe((LifecycleOwner)context, text ->
                super.setText(text.toString()));
    }

    public void setText(TextViewData textData) {
        this.textData = textData;
        super.setText(textData.toString());
    }
}

