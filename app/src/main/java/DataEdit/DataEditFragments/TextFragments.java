package DataEdit.DataEditFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import Activities.DatabaseActivity;
import Interfaces.TextViewData;
import ViewModels.SharedViewModel;

public abstract class TextFragments extends DialogFragment {
    protected TextViewData parentData;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    public abstract void createView(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseActivity parent = (DatabaseActivity) requireContext();
        parent.setModel(new ViewModelProvider(this).get(SharedViewModel.class));
        parent.getModel().getSelected().observe(this, data -> {
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
        ((DatabaseActivity)requireContext()).getModel().select(parentData);
    }
}
