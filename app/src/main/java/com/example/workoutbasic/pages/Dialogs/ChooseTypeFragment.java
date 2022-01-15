package com.example.workoutbasic.pages.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.workoutbasic.R;
import com.example.workoutbasic.databinding.ChooseTypeFragmentBinding;

import com.example.workoutbasic.interfaces.ButtonOptions;
import com.example.workoutbasic.utils.FragmentMethods;

public class ChooseTypeFragment extends DialogFragment {
    public static final String NAME_KEY = "name";
    private String name; //TODO: add bindable
    public ButtonOptions parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ChooseTypeFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.choose_type_fragment, container, false);
        binding.setFrag(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        name = getArguments().getString(NAME_KEY);
        parent = (ButtonOptions)FragmentMethods.getParentFragment(this);
    }
}
