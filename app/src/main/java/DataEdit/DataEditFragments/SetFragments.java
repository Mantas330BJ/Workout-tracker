package DataEdit.DataEditFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import Datas.SetData;
import ViewModels.SetDataViewModel;

public abstract class SetFragments extends DialogFragment {
    public static final String METHOD_ID = "methodid";

    protected SetDataViewModel viewModel;
    protected SetData setData;
    protected int methodIndex;

    public abstract void createView(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        methodIndex = requireArguments().getInt(METHOD_ID);

        viewModel = new ViewModelProvider(requireActivity()).get(SetDataViewModel.class);
        viewModel.getSelected().observe(requireActivity(), data -> {
            setData = data;
            createView(view);
        });
    }
}
