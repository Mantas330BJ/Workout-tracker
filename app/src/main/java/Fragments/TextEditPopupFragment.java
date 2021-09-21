package Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.workoutbasic.OnInputListener;
import com.example.workoutbasic.R;

import java.util.Objects;

import CustomViews.WorkoutEditText;
import Interfaces.Editable;
import Variables.TextViewData;

public class TextEditPopupFragment extends DialogFragment {
    public Editable editView; //Workout Edit Text, Workout Time Picker, Workout Date Picker
    public LinearLayout linearLayout;
    public OnInputListener onInputListener;
    private TextViewData parentData;

    public TextEditPopupFragment(TextViewData parentData) {
        this.parentData = parentData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        linearLayout = view.findViewById(R.id.linear_layout); //Called from derived variables with edit text.
        editView = parentData.getPicker().getEditable(requireContext());
        parentData.setFilters(editView);
        editView.displayData(parentData);
        linearLayout.addView(editView.getView());
        return view;
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setFragmentOnDismiss(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void setParentData(TextViewData parentData) {
        this.parentData = parentData;
    }

}
