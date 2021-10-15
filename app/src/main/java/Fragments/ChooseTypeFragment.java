package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workoutbasic.R;

import Interfaces.ButtonOptions;
import Utils.FragmentMethods;

public class ChooseTypeFragment extends DialogFragment {
    public static final String NAME_KEY = "name";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_type_fragment, container, false);

        assert getArguments() != null;
        String name = getArguments().getString(NAME_KEY);

        ButtonOptions parent = (ButtonOptions)FragmentMethods.getParentFragment(this);

        Button createEmpty = view.findViewById(R.id.create_empty);
        createEmpty.setOnClickListener(parent.onCreateEmpty(this));

        Button copyPrevious = view.findViewById(R.id.copy_previous);
        copyPrevious.setOnClickListener(parent.onCreatePrevious(this));

        createEmpty.setText(getString(R.string.create_empty, name));
        copyPrevious.setText(getString(R.string.copy_previous, name));

        return view;
    }
}
