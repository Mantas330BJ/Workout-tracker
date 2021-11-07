package DataEdit.DataEditFragments.Text;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.R;

import java.util.Objects;

import DataEdit.DataEditFragments.SetFragments;
import Datas.ExerciseData;
import Utils.EditTextUtils;
import ViewModels.ExerciseDataViewModel;

public class StringFragment extends SetFragments {
    protected EditText editText;
    private ExerciseData exerciseData;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExerciseDataViewModel viewModel = new ViewModelProvider(requireActivity()).get(ExerciseDataViewModel.class);
        viewModel.getSelected().observe(requireActivity(), data -> {
            exerciseData = data;
            editText = view.findViewById(R.id.edit_text);
            EditTextUtils.setEditTextParams(this, exerciseData.getExercise(), editText);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.string_fragment, container, false);
    }

    @Override
    public void createView(View view) {

    }

    @Override
    public void onDismiss(@NonNull final DialogInterface dialog) {
        exerciseData.setExercise(editText.getText().toString());
        super.onDismiss(dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
