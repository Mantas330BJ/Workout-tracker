package Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.workoutbasic.R;

import Activities.EditWorkoutActivity;

public class ChooseTypeFragment extends DialogFragment {
    private final String name;

    public ChooseTypeFragment(String name) {
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_type_fragment, container, false);

        Button createEmpty = view.findViewById(R.id.create_empty);
        Button copyPrevious = view.findViewById(R.id.copy_previous);

        createEmpty.setText(getString(R.string.create_empty, name));
        copyPrevious.setText(getString(R.string.copy_previous, name));

        return view;
    }
}
