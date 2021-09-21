package Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.workoutbasic.OnInputListener;
import com.example.workoutbasic.R;

import java.util.Objects;

import CustomViews.WorkoutEditText;
import Interfaces.Editable;
import Variables.TextViewData;
import Variables.commentStr;

public class CommentEditFragment extends DialogFragment {
    private Editable editView;
    private final commentStr parentData;

    public CommentEditFragment(commentStr parentData) {
        this.parentData = parentData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.linear_layout);
        editView = new WorkoutEditText(requireContext()); //parentData.getRequiredFragment
        ((WorkoutEditText)editView).setText(parentData.toString());
        ((WorkoutEditText)editView).requestFocus();
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        linearLayout.addView((WorkoutEditText)editView);
        return view;
    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        super.onDismiss(dialog);
        parentData.setStr((Objects.requireNonNull(((WorkoutEditText) editView).getText())).toString());
    }
}
