package DataEdit.DataEditFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutbasic.R;

import Activities.DatabaseActivity;
import Interfaces.TextViewData;
import ViewModels.SharedViewModel;

public abstract class TextFragments extends DialogFragment {
    protected TextViewData parentData;
    protected SharedViewModel viewModel;

    public abstract void createView(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getSelected().observe(this, data -> {
            parentData = data;
            createView(view);
        });
    }

    public void dismissWithoutSettingText(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        viewModel.select(parentData);
    }
}
