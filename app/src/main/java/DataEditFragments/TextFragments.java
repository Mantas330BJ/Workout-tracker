package DataEditFragments;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
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
        ((DatabaseActivity)context).setModel(
                new ViewModelProvider(this).get(SharedViewModel.class));
    }

    public TextFragments(TextViewData parentData) {
        this.parentData = parentData;
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
